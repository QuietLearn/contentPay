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
        {field: 'selectItem', checkbox: true},
            {title: '主键', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '类型code', field: 'type', visible: false, align: 'center', valign: 'middle'},
          {title: '类型', field: 'notiType', visible: false, align: 'center', valign: 'middle'},

            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},

        {title: '推送用户', field: 'memberId', visible: true, align: 'center', valign: 'middle'},

        {title: '版本', field: 'isOfficialName', visible: true, align: 'center', valign: 'middle'},

            {title: '活动地址', field: 'activityUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'picAddress', visible: false, align: 'center', valign: 'middle'},
            {title: '逻辑删除 0是 1否', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'},
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

//批量删除
function deleteNotificationList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#NotificationTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteNotifications(ids);
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

//删除
function deleteNotifications(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/notification/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                Notification.table.refresh();
                /* $("#user").bootstrapTable('refresh', {url: '/user/getUserList.do'});*/
            }, error:function (data) {
                alert(data.msg);
                Notification.table.refresh();
            }
        });
    }
};

/**
 * 查询列表
 */
Notification.search = function () {
    var queryData = {};
    queryData['notiType'] = $("#notiType").val();
    queryData['isOfficial'] = $("#isOfficial").val();
    Notification.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Notification.initColumn();
    var table = new BSTable(Notification.id, "/notification/list", defaultColunms);
    table.setPaginationType("server");
    Notification.table = table.init();
});
