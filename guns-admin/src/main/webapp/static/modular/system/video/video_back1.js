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
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '视频id', field: 'vId', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '视频类型id', field: 'tid', visible: false, align: 'center', valign: 'middle'},
        {title: '视频类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},

            {title: '视频名', field: 'vName', visible: true, align: 'center', valign: 'middle'},
            {title: '视频图片', field: 'vPic', visible: true, align: 'center', valign: 'middle'},
            {title: '发行日期', field: 'vPublishyear', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '添加时间', field: 'vAddtime', visible: true, align: 'center', valign: 'middle'},
            {title: '消耗金币', field: 'vMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '是否需要会员 ', field: 'vReqVip', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'}
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
    var ajax = new $ax(Feng.ctxPath + "/video/sync_video", function (data) {
        Feng.success("同步视频数据成功!");
        Video.table.refresh();
    }, function (data) {
        Feng.error("同步视频数据失败!" + data.responseJSON.message + "!");
    });
    // ajax.set("videoId",this.seItem.id);
    ajax.start();
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
 * 查询表单提交参数对象
 * @returns {{}}
 */
Video.formParams = function() {
    var queryData = {};

    queryData['videoName'] = $("#videoName").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();

    return queryData;
};

/**
 * 查询视频价格列表
 */
Video.search = function () {
    var queryData = {};
    //queryData['condition'] = $("#condition").val();
    Video.table.refresh({query: Video.formParams()});
};

$(function () {
    var defaultColunms = Video.initColumn();
    var table = new BSTable(Video.id, "/video/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Video.formParams());
    Video.table = table.init();
});
