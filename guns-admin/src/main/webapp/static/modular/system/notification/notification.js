/**
 * 管理初始化
 */
var Notification = {
    id: "NotificationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Notification.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '类型(1000 系统消息 1001 热门活动 1002 app异常bug消息)', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '活动地址', field: 'activityUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'picAddress', visible: true, align: 'center', valign: 'middle'},
            {title: '逻辑删除 0是 1否', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'creater', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Notification.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Notification.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Notification.openAddNotification = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/notification/notification_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Notification.openNotificationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/notification/notification_update/' + Notification.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Notification.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/notification/delete", function (data) {
            Feng.success("删除成功!");
            Notification.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("notificationId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Notification.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Notification.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Notification.initColumn();
    var table = new BSTable(Notification.id, "/notification/list", defaultColunms);
    table.setPaginationType("client");
    Notification.table = table.init();
});
