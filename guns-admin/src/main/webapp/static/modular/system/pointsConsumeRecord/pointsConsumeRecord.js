/**
 * 积分流水记录管理初始化
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
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '消费的积分', field: 'points', visible: true, align: 'center', valign: 'middle'},
            {title: '消费的用户', field: 'memberId', visible: true, align: 'center', valign: 'middle'},
            {title: '消费观看的视频', field: 'videoId', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
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
 * 点击添加积分流水记录
 */
PointsConsumeRecord.openAddPointsConsumeRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加积分流水记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/pointsConsumeRecord/pointsConsumeRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看积分流水记录详情
 */
PointsConsumeRecord.openPointsConsumeRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '积分流水记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/pointsConsumeRecord/pointsConsumeRecord_update/' + PointsConsumeRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除积分流水记录
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
 * 查询积分流水记录列表
 */
PointsConsumeRecord.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PointsConsumeRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PointsConsumeRecord.initColumn();
    var table = new BSTable(PointsConsumeRecord.id, "/pointsConsumeRecord/list", defaultColunms);
    table.setPaginationType("client");
    PointsConsumeRecord.table = table.init();
});
