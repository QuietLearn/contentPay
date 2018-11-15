/**
 * 初始化详情对话框
 */
var NotificationInfoDlg = {
    notificationInfoData : {}
};

/**
 * 清除数据
 */
NotificationInfoDlg.clearData = function() {
    this.notificationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NotificationInfoDlg.set = function(key, val) {
    this.notificationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NotificationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NotificationInfoDlg.close = function() {
    parent.layer.close(window.parent.Notification.layerIndex);
}

/**
 * 收集数据
 */
NotificationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('title')
    .set('type')
    .set('memberId')
    .set('content')
    .set('isOfficial')
    .set('activityUrl')
    .set('picAddress')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified')
    .set('creater');
}

/**
 * 提交添加
 */
NotificationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/notification/add", function(data){
        Feng.success("添加成功!");
        window.parent.Notification.table.refresh();
        NotificationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.notificationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NotificationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/notification/update", function(data){
        Feng.success("修改成功!");
        window.parent.Notification.table.refresh();
        NotificationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.notificationInfoData);
    ajax.start();
}

$(function() {
    $("#isOfficial").val($("#isOfficialValue").val());
});
