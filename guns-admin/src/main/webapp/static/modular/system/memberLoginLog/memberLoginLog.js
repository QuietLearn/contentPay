/**
 * 用户登录日志管理初始化
 */
var MemberLoginLog = {
    id: "MemberLoginLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MemberLoginLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '日志名称', field: 'logname', visible: true, align: 'center', valign: 'middle'},
            {title: '应用id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
            {title: '应用版本', field: 'appVer', visible: true, align: 'center', valign: 'middle'},
            {title: '用户更新app后的版本', field: 'updateAppver', visible: true, align: 'center', valign: 'middle'},
            {title: '渠道号', field: 'channel', visible: true, align: 'center', valign: 'middle'},
            {title: '会员id', field: 'memberid', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否执行成功', field: 'succeed', visible: true, align: 'center', valign: 'middle'},
            {title: '具体消息', field: 'message', visible: true, align: 'center', valign: 'middle'},
            {title: '登录ip', field: 'ip', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MemberLoginLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MemberLoginLog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户登录日志
 */
MemberLoginLog.openAddMemberLoginLog = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户登录日志',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/memberLoginLog/memberLoginLog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户登录日志详情
 */
MemberLoginLog.openMemberLoginLogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户登录日志详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/memberLoginLog/memberLoginLog_update/' + MemberLoginLog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户登录日志
 */
MemberLoginLog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/memberLoginLog/delete", function (data) {
            Feng.success("删除成功!");
            MemberLoginLog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("memberLoginLogId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户登录日志列表
 */
MemberLoginLog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MemberLoginLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MemberLoginLog.initColumn();
    var table = new BSTable(MemberLoginLog.id, "/memberLoginLog/list", defaultColunms);
    table.setPaginationType("client");
    MemberLoginLog.table = table.init();
});
