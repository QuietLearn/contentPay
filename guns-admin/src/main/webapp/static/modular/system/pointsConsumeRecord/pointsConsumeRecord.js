/**
 * 积分流水管理管理初始化
 */
var PointsConsumeRecord = {
    id: "PointsConsumeRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PointsConsumeRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '消费的积分', field: 'points', visible: true, align: 'center', valign: 'middle'},
            {title: '消费的用户', field: 'memberId', visible: false, align: 'center', valign: 'middle'},
        {title: '消费的用户', field: 'mem    berName', visible: true, align: 'center', valign: 'middle'},
            {title: '消费观看的视频', field: 'videoId', visible: false, align: 'center', valign: 'middle'},
        {title: '消费观看的视频', field: 'videoName', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '消费时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'},
        {title: '应用id', field: 'appId', visible: false, align: 'center', valign: 'middle'},

        {title: '应用名', field: 'appName', visible: true, align: 'center', valign: 'middle',sortable: true},
        {title: '应用版本ver', field: 'appVer', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本', field: 'appVerName', visible: true, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channel', visible: false, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channelName', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
PointsConsumeRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PointsConsumeRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加积分流水管理
 */
PointsConsumeRecord.openAddPointsConsumeRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加积分流水管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/pointsConsumeRecord/pointsConsumeRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看积分流水管理详情
 */
PointsConsumeRecord.openPointsConsumeRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '积分流水管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/pointsConsumeRecord/pointsConsumeRecord_update/' + PointsConsumeRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除积分流水管理
 */
PointsConsumeRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/pointsConsumeRecord/delete", function (data) {
            Feng.success("删除成功!");
            PointsConsumeRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("pointsConsumeRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询积分流水管理列表
 */
PointsConsumeRecord.search = function () {
    var queryData = {};
    queryData['username'] = $("#username").val();
    queryData['videoId'] = $("#videoId").val();
    queryData['appId'] = $("#appId").val();
    PointsConsumeRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PointsConsumeRecord.initColumn();
    var table = new BSTable(PointsConsumeRecord.id, "/pointsConsumeRecord/list", defaultColunms);
    table.setPaginationType("server");
    PointsConsumeRecord.table = table.init();
});
