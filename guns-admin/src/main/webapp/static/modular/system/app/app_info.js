/**
 * 初始化应用管理详情对话框
 */
var AppInfoDlg = {
    appInfoData : {}
};

/**
 * 清除数据
 */
AppInfoDlg.clearData = function() {
    this.appInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppInfoDlg.set = function(key, val) {
    this.appInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AppInfoDlg.close = function() {
    parent.layer.close(window.parent.App.layerIndex);
}

/**
 * 收集数据
 */
AppInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appId')
    .set('appName')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
AppInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/app/add", function(data){
        Feng.success("添加成功!");
        window.parent.App.table.refresh();
        AppInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AppInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/app/update", function(data){
        Feng.success("修改成功!");
        window.parent.App.table.refresh();
        AppInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appInfoData);
    ajax.start();
}

$(function() {

});
