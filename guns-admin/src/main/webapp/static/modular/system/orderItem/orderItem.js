/**
 * 订单详情管理管理初始化
 */
var OrderItem = {
    id: "OrderItemTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrderItem.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '订单子表id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'memberId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'orderNo', visible: true, align: 'center', valign: 'middle'},
            {title: ''购买商品类型 1 会员　２　自定义商品 3积分商品  4 模豆提取码商品'', field: 'buyType', visible: true, align: 'center', valign: 'middle'},
            {title: '商品id', field: 'productId', visible: true, align: 'center', valign: 'middle'},
            {title: '商品名称', field: 'productName', visible: true, align: 'center', valign: 'middle'},
            {title: '单价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '商品数量', field: 'quantity', visible: true, align: 'center', valign: 'middle'},
            {title: '商品总价,单位是元,保留两位小数', field: 'totalPrice', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
OrderItem.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OrderItem.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加订单详情管理
 */
OrderItem.openAddOrderItem = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单详情管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/orderItem/orderItem_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看订单详情管理详情
 */
OrderItem.openOrderItemDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '订单详情管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/orderItem/orderItem_update/' + OrderItem.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除订单详情管理
 */
OrderItem.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/orderItem/delete", function (data) {
            Feng.success("删除成功!");
            OrderItem.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("orderItemId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询订单详情管理列表
 */
OrderItem.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    OrderItem.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OrderItem.initColumn();
    var table = new BSTable(OrderItem.id, "/orderItem/list", defaultColunms);
    table.setPaginationType("client");
    OrderItem.table = table.init();
});
