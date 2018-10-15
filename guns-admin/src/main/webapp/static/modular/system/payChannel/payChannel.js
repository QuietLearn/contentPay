/**
 * 支付渠道管理初始化
 */
var PayChannel = {
    id: "PayChannelTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PayChannel.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '删除', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '支付码', field: 'payCode', visible: true, align: 'center', valign: 'middle'},
            {title: '支付渠道类型', field: 'payType', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PayChannel.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PayChannel.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加支付渠道
 */
PayChannel.openAddPayChannel = function () {
    var index = layer.open({
        type: 2,
        title: '添加支付渠道',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/payChannel/payChannel_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看支付渠道详情
 */
PayChannel.openPayChannelDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '支付渠道详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/payChannel/payChannel_update/' + PayChannel.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除支付渠道
 */
PayChannel.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/payChannel/delete", function (data) {
            Feng.success("删除成功!");
            PayChannel.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("payChannelId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询支付渠道列表
 */
PayChannel.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PayChannel.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PayChannel.initColumn();
    var table = new BSTable(PayChannel.id, "/payChannel/list", defaultColunms);
    table.setPaginationType("client");
    PayChannel.table = table.init();
});
