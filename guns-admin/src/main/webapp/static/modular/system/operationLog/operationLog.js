/**
 * 业务日志管理初始化
 */
var OperationLog = {
    id: "OperationLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OperationLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '日志类型', field: 'logtype', visible: true, align: 'center', valign: 'middle'},
            {title: '1 后台 2 前台', field: 'errorsite', visible: true, align: 'center', valign: 'middle'},
            {title: '日志名称', field: 'logname', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'userid', visible: true, align: 'center', valign: 'middle'},
            {title: '类名称', field: 'classname', visible: true, align: 'center', valign: 'middle'},
            {title: '方法名称', field: 'method', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否成功', field: 'succeed', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'message', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
OperationLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OperationLog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加业务日志
 */
OperationLog.openAddOperationLog = function () {
    var index = layer.open({
        type: 2,
        title: '添加业务日志',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/operationLog/operationLog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看业务日志详情
 */
OperationLog.openOperationLogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '业务日志详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/operationLog/operationLog_update/' + OperationLog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除业务日志
 */
OperationLog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/operationLog/delete", function (data) {
            Feng.success("删除成功!");
            OperationLog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("operationLogId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询业务日志列表
 */
OperationLog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    OperationLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OperationLog.initColumn();
    var table = new BSTable(OperationLog.id, "/operationLog/list", defaultColunms);
    table.setPaginationType("client");
    OperationLog.table = table.init();
});
