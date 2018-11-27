/**
 * 发布视频管理初始化
 */
var UslVideoRepository = {
    id: "UslVideoRepositoryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UslVideoRepository.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '所属视频分类', field: 'categoryId', visible: true, align: 'center', valign: 'middle'},
            {title: '原封面图片地址id
', field: 'pictureId', visible: true, align: 'center', valign: 'middle'},
            {title: '封面图片', field: 'coverImage', visible: true, align: 'center', valign: 'middle'},
            {title: '视频标签', field: 'labelIds', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'introduction', visible: true, align: 'center', valign: 'middle'},
            {title: '播放次数', field: 'playsNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '视频地址', field: 'videoAddress', visible: true, align: 'center', valign: 'middle'},
            {title: '国家id', field: 'countryId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'times', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '排序字段', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '浏览次数', field: 'browse', visible: true, align: 'center', valign: 'middle'},
            {title: '所属用戶', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '收藏次数', field: 'collectionCount', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UslVideoRepository.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UslVideoRepository.seItem = selected[0];
        return true;
    }
};

/**
 * 批量删除
 */

function deleteUslVideoRepositoryList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#UslVideoRepositoryTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteUslVideoRepositorys(ids);
};


/**
 * 点击添加发布视频
 */
UslVideoRepository.openAddUslVideoRepository = function () {
    var index = layer.open({
        type: 2,
        title: '添加发布视频',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/uslVideoRepository/uslVideoRepository_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看发布视频详情
 */
UslVideoRepository.openUslVideoRepositoryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '发布视频详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/uslVideoRepository/uslVideoRepository_update/' + UslVideoRepository.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除发布视频
 */
UslVideoRepository.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/uslVideoRepository/delete", function (data) {
            Feng.success("删除成功!");
            UslVideoRepository.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("uslVideoRepositoryId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除发布视频List
 */
function deleteUslVideoRepositorys(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/uslVideoRepository/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                UslVideoRepository.table.refresh();
            }, error:function (data) {
                alert(data.msg);
                UslVideoRepository.table.refresh();
            }
        });
    }
};



/**
 * 查询发布视频列表
 */
UslVideoRepository.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    UslVideoRepository.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UslVideoRepository.initColumn();
    var table = new BSTable(UslVideoRepository.id, "/uslVideoRepository/list", defaultColunms);
    table.setPaginationType("client");
    UslVideoRepository.table = table.init();
});
