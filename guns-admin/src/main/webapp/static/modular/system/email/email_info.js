/**
 * 初始化邮箱激活详情对话框
 */
var EmailInfoDlg = {
    emailInfoData : {}
};

/**
 * 清除数据
 */
EmailInfoDlg.clearData = function() {
    this.emailInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EmailInfoDlg.set = function(key, val) {
    this.emailInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EmailInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EmailInfoDlg.close = function() {
    parent.layer.close(window.parent.Email.layerIndex);
}

/**
 * 收集数据
 */
EmailInfoDlg.collectData = function() {
    this
    .set('id')
    .set('email')
    .set('appId')
    .set('active')
    .set('memberId')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
EmailInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/email/add", function(data){
        Feng.success("添加成功!");
        window.parent.Email.table.refresh();
        EmailInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.emailInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EmailInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/email/update", function(data){
        Feng.success("修改成功!");
        window.parent.Email.table.refresh();
        EmailInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.emailInfoData);
    ajax.start();
}

$(function() {

});
