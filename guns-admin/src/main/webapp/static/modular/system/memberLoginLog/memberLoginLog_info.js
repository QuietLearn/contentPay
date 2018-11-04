/**
 * 初始化用户登录日志详情对话框
 */
var MemberLoginLogInfoDlg = {
    memberLoginLogInfoData : {}
};

/**
 * 清除数据
 */
MemberLoginLogInfoDlg.clearData = function() {
    this.memberLoginLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberLoginLogInfoDlg.set = function(key, val) {
    this.memberLoginLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberLoginLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberLoginLogInfoDlg.close = function() {
    parent.layer.close(window.parent.MemberLoginLog.layerIndex);
}

/**
 * 收集数据
 */
MemberLoginLogInfoDlg.collectData = function() {
    this
    .set('id')
    .set('logname')
    .set('appId')
    .set('appVer')
    .set('updateAppver')
    .set('channel')
    .set('memberid')
    .set('createtime')
    .set('succeed')
    .set('message')
    .set('ip');
}

/**
 * 提交添加
 */
MemberLoginLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberLoginLog/add", function(data){
        Feng.success("添加成功!");
        window.parent.MemberLoginLog.table.refresh();
        MemberLoginLogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberLoginLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberLoginLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberLoginLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.MemberLoginLog.table.refresh();
        MemberLoginLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberLoginLogInfoData);
    ajax.start();
}

$(function() {

});
