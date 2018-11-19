/**
 * 初始化应用内容版本详情对话框
 */
var AppContentInfoDlg = {
    appContentInfoData : {}
};

/**
 * 清除数据
 */
AppContentInfoDlg.clearData = function() {
    this.appContentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppContentInfoDlg.set = function(key, val) {
    this.appContentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppContentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AppContentInfoDlg.close = function() {
    parent.layer.close(window.parent.AppContent.layerIndex);
}

/**
 * 收集数据
 */
AppContentInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appId')
    .set('appVer')
    .set('channelId')
    .set('name')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
AppContentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/appContent/add", function(data){
        Feng.success("添加成功!");
        window.parent.AppContent.table.refresh();
        AppContentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appContentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AppContentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/appContent/update", function(data){
        Feng.success("修改成功!");
        window.parent.AppContent.table.refresh();
        AppContentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appContentInfoData);
    ajax.start();
}

$(function() {

});
