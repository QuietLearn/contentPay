/**
 * 图集应用内容管理初始化
 */
var PhotosAppSource = {
    id: "PhotosAppSourceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PhotosAppSource.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '应用内容id', field: 'appContentId', visible: true, align: 'center', valign: 'middle'},
            {title: '图集资源库id', field: 'picturesCategoryId', visible: true, align: 'center', valign: 'middle'},
            {title: '权限', field: 'authority', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isdel', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PhotosAppSource.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PhotosAppSource.seItem = selected[0];
        return true;
    }
};

/**
 * 批量删除
 */

function deletePhotosAppSourceList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#PhotosAppSourceTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deletePhotosAppSources(ids);
};


/**
 * 点击添加图集应用内容
 */
PhotosAppSource.openAddPhotosAppSource = function () {
    var index = layer.open({
        type: 2,
        title: '添加图集应用内容',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/photosAppSource/photosAppSource_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看图集应用内容详情
 */
PhotosAppSource.openPhotosAppSourceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '图集应用内容详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/photosAppSource/photosAppSource_update/' + PhotosAppSource.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除图集应用内容
 */
PhotosAppSource.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/photosAppSource/delete", function (data) {
            Feng.success("删除成功!");
            PhotosAppSource.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("photosAppSourceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除图集应用内容List
 */
function deletePhotosAppSources(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/photosAppSource/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                PhotosAppSource.table.refresh();
            }, error:function (data) {
                alert(data.msg);
                PhotosAppSource.table.refresh();
            }
        });
    }
};



/**
 * 查询图集应用内容列表
 */
PhotosAppSource.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PhotosAppSource.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PhotosAppSource.initColumn();
    var table = new BSTable(PhotosAppSource.id, "/photosAppSource/list", defaultColunms);
    table.setPaginationType("client");
    PhotosAppSource.table = table.init();
});
