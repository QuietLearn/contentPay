/**
 * 应用管理管理初始化
 */
var App = {
    id: "AppTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
App.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'app_id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
            {title: '应用名', field: 'appName', visible: true, align: 'center', valign: 'middle'},
            {title: '逻辑删除 0是 1否', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
App.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        App.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加应用管理
 */
App.openAddApp = function () {
    var index = layer.open({
        type: 2,
        title: '添加应用管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/app/app_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看应用管理详情
 */
App.openAppDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '应用管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/app/app_update/' + App.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除应用管理
 */
App.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/app/delete", function (data) {
            Feng.success("删除成功!");
            App.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("appId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询应用管理列表
 */
App.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    App.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = App.initColumn();
    var table = new BSTable(App.id, "/app/list", defaultColunms);
    table.setPaginationType("client");
    App.table = table.init();
});
