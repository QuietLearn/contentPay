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
            {title: '应用id', field: 'appId', visible: false, align: 'center', valign: 'middle'},
        {title: '应用名', field: 'appName', visible: true, align: 'center', valign: 'middle'},
        {title: '应用版本ver', field: 'appVer', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本', field: 'appVerName', visible: true, align: 'center', valign: 'middle'},
            {title: '登录状态-更新版本', field: 'updateAppver', visible: false, align: 'center', valign: 'middle'},
        {title: '登录状态-更新版本', field: 'updateAppverName', visible: true, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channel', visible: false, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channelName', visible: true, align: 'center', valign: 'middle'},
            {title: '会员id', field: 'memberid', visible: false, align: 'center', valign: 'middle'},
        {title: '用户', field: 'memberName', visible: true, align: 'center', valign: 'middle'},

            {title: '登录时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否执行成功', field: 'succeed', visible: false, align: 'center', valign: 'middle'},
            {title: '具体消息', field: 'message', visible: false, align: 'center', valign: 'middle'},
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
    queryData['appId'] = $("#appId  ").val();
    queryData['userId'] = $("#userId").val();
    MemberLoginLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MemberLoginLog.initColumn();
    var table = new BSTable(MemberLoginLog.id, "/memberLoginLog/list", defaultColunms);
    table.setPaginationType("server");
    MemberLoginLog.table = table.init();
});

$(function () {
    $.ajax({
        url: "/app/list",    //后台webservice里的方法名称
        contentType: "application/json; charset=utf-8",
        type: "get",
        async : true ,
        dataType: "json",
        success: function (data) {
            var optionstring = "";
            for (var j = 0; j < data.length;j++) {
                optionstring += "<option value=\"" + data[j].appId + "\" >" +data[j].appName + "</option>";
                $("#appId").html("<option value='0'>全部</option> "+optionstring);
            }
        },
        error: function (msg) {
            alert("出错了！");
        }
    });

});