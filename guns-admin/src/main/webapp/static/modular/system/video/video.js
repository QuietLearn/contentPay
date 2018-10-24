/**
 * 视频价格管理初始化
 */
var Video = {
    id: "VideoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Video.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '视频id', field: 'vId', visible: true, align: 'center', valign: 'middle'},
            {title: '视频类型id', field: 'tId', visible: true, align: 'center', valign: 'middle'},
            {title: '视频名', field: 'vName', visible: true, align: 'center', valign: 'middle'},
            {title: '视频图片', field: 'vPic', visible: true, align: 'center', valign: 'middle'},
            {title: '发行日期', field: 'vPublishyear', visible: true, align: 'center', valign: 'middle'},
            {title: '添加时间', field: 'vAddtime', visible: true, align: 'center', valign: 'middle'},
            {title: '消耗金币', field: 'vMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '是否需要会员 0是 1否', field: 'vReqVip', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Video.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Video.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加视频价格
 */
Video.openAddVideo = function () {
    var index = layer.open({
        type: 2,
        title: '添加视频价格',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/video/video_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看视频价格详情
 */
Video.openVideoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '视频价格详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/video/video_update/' + Video.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除视频价格
 */
Video.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/video/delete", function (data) {
            Feng.success("删除成功!");
            Video.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("videoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询视频价格列表
 */
Video.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Video.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Video.initColumn();
    var table = new BSTable(Video.id, "/video/list", defaultColunms);
    table.setPaginationType("server");
    Video.table = table.init();
});