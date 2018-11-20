/**
 * 视频应用内容管理初始化
 */
var VideoAppSource = {
    id: "VideoAppSourceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VideoAppSource.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '自动编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '应用内容id', field: 'appContentId', visible: true, align: 'center', valign: 'middle'},
            {title: '视频id', field: 'videoId', visible: true, align: 'center', valign: 'middle'},
            {title: '权限', field: 'authority', visible: true, align: 'center', valign: 'middle'},
            {title: '逻辑删除', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'},

        {title: '所属视频分类', field: 'categoryId', visible: true, align: 'center', valign: 'middle'},
        {title: '原封面图片地址id', field: 'pictureId', visible: false, align: 'center', valign: 'middle'},
        {title: '封面图片', field: 'coverImage', visible: true, align: 'center', valign: 'middle'},
        {title: '视频标签', field: 'labelIds', visible: true, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '描述', field: 'introduction', visible: true, align: 'center', valign: 'middle'},
        {title: '播放次数', field: 'playsNumber', visible: true, align: 'center', valign: 'middle'},
        {title: '视频地址Id', field: 'videoAddressid', visible: true, align: 'center', valign: 'middle'},
        {title: '国家id', field: 'countryId', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'times', visible: false, align: 'center', valign: 'middle'},
        {title: '排序字段', field: 'sort', visible: false, align: 'center', valign: 'middle'},
        {title: '1:2图片', field: 'onePastTwo', visible: false, align: 'center', valign: 'middle'},
        {title: '4:5图片', field: 'fivePastFour', visible: false, align: 'center', valign: 'middle'},
        {title: 'x：x图片', field: 'xPastx', visible: false, align: 'center', valign: 'middle'},
        {title: '浏览次数', field: 'browse', visible: true, align: 'center', valign: 'middle'},
        {title: '所属模特', field: 'modelId', visible: true, align: 'center', valign: 'middle'},
        {title: '收藏次数', field: 'collectionCount', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VideoAppSource.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        VideoAppSource.seItem = selected[0];
        return true;
    }
};

/**
 * 批量删除
 */

function deleteVideoAppSourceList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#VideoAppSourceTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteVideoAppSources(ids);
};


/**
 * 点击添加视频应用内容
 */
VideoAppSource.openAddVideoAppSource = function () {
    var index = layer.open({
        type: 2,
        title: '添加视频应用内容',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/videoAppSource/videoAppSource_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看视频应用内容详情
 */
VideoAppSource.openVideoAppSourceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '视频应用内容详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/videoAppSource/videoAppSource_update/' + VideoAppSource.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除视频应用内容
 */
VideoAppSource.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/videoAppSource/delete", function (data) {
            Feng.success("删除成功!");
            VideoAppSource.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("videoAppSourceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除视频应用内容List
 */
function deleteVideoAppSources(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/videoAppSource/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                VideoAppSource.table.refresh();
            }, error:function (data) {
                alert(data.msg);
                VideoAppSource.table.refresh();
            }
        });
    }
};



/**
 * 查询视频应用内容列表
 */
VideoAppSource.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    VideoAppSource.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = VideoAppSource.initColumn();
    var table = new BSTable(VideoAppSource.id, "/videoAppSource/list", defaultColunms);
    table.setPaginationType("server");
    VideoAppSource.table = table.init();
});
