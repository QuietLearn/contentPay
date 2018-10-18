/**
 * 会员价格管理管理初始化
 */
var VipPrice = {
    id: "VipPriceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VipPrice.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户类型', field: 'memberTypeCode', visible: true, align: 'center', valign: 'middle'},
            {title: '购买类型名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '价格', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '持续时间', field: 'aging', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtUpdated', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VipPrice.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        VipPrice.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员价格管理
 */
VipPrice.openAddVipPrice = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员价格管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/vipPrice/vipPrice_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员价格管理详情
 */
VipPrice.openVipPriceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员价格管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/vipPrice/vipPrice_update/' + VipPrice.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员价格管理
 */
VipPrice.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/vipPrice/delete", function (data) {
            Feng.success("删除成功!");
            VipPrice.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("vipPriceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员价格管理列表
 */
VipPrice.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    VipPrice.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = VipPrice.initColumn();
    var table = new BSTable(VipPrice.id, "/vipPrice/list", defaultColunms);
    table.setPaginationType("client");
    VipPrice.table = table.init();
});
