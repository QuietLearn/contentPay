/**
 * 支付积分管理初始化
 */
var PayPoints = {
    id: "PayPointsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PayPoints.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'appid', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'accoutid', visible: true, align: 'center', valign: 'middle'},
            {title: '积分购买类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PayPoints.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PayPoints.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加支付积分
 */
PayPoints.openAddPayPoints = function () {
    var index = layer.open({
        type: 2,
        title: '添加支付积分',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/payPoints/payPoints_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看支付积分详情
 */
PayPoints.openPayPointsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '支付积分详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/payPoints/payPoints_update/' + PayPoints.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除支付积分
 */
PayPoints.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/payPoints/delete", function (data) {
            Feng.success("删除成功!");
            PayPoints.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("payPointsId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询支付积分列表
 */
PayPoints.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PayPoints.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PayPoints.initColumn();
    var table = new BSTable(PayPoints.id, "/payPoints/list", defaultColunms);
    table.setPaginationType("client");
    PayPoints.table = table.init();
});
