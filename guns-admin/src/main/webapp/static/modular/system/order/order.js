/**
 * 订单管理管理初始化
 */
var Order = {
    id: "OrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Order.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '自动编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '订单号', field: 'orderNo', visible: true, align: 'center', valign: 'middle'},
            {title: '支付渠道', field: 'channelId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberId', visible: true, align: 'center', valign: 'middle'},
            {title: '实际付款金额', field: 'payment', visible: true, align: 'center', valign: 'middle'},
            {title: '支付类型', field: 'paymentType', visible: true, align: 'center', valign: 'middle'},
            {title: '订单状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '购买商品类型 ', field: 'buyType', visible: true, align: 'center', valign: 'middle'},
            {title: 'appId', field: 'appId', visible: true, align: 'center', valign: 'middle'},
            {title: '机型 ', field: 'payClient', visible: true, align: 'center', valign: 'middle'},
            {title: '支付时间', field: 'paymentTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Order.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Order.seItem = selected[0];
        return true;
    }
};

/**
 * 批量删除
 */

function deleteOrderList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#OrderTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteOrders(ids);
};


/**
 * 点击添加订单管理
 */
Order.openAddOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/order/order_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看订单管理详情
 */
Order.openOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '订单管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/order/order_update/' + Order.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除订单管理
 */
Order.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/order/delete", function (data) {
            Feng.success("删除成功!");
            Order.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("orderId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除订单管理List
 */
function deleteOrders(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/order/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                Order.table.refresh();
            }, error:function (data) {
                alert(data.msg);
                Order.table.refresh();
            }
        });
    }
};



/**
 * 查询订单管理列表
 */
Order.search = function () {
    var queryData = {};
    queryData['orderNo'] = $("#orderNo").val();
    Order.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Order.initColumn();
    var table = new BSTable(Order.id, "/order/list", defaultColunms);
    table.setPaginationType("client");
    Order.table = table.init();
});
