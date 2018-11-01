/**
 * 初始化应用版本详情对话框
 */
var AppVerInfoDlg = {
    appVerInfoData : {}
};

/**
 * 清除数据
 */
AppVerInfoDlg.clearData = function() {
    this.appVerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppVerInfoDlg.set = function(key, val) {
    this.appVerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppVerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AppVerInfoDlg.close = function() {
    parent.layer.close(window.parent.AppVer.layerIndex);
}

/**
 * 收集数据
 */
AppVerInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appVer')
    .set('label')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
AppVerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/appVer/add", function(data){
        Feng.success("添加成功!");
        window.parent.AppVer.table.refresh();
        AppVerInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appVerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AppVerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/appVer/update", function(data){
        Feng.success("修改成功!");
        window.parent.AppVer.table.refresh();
        AppVerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appVerInfoData);
    ajax.start();
}

$(function() {

});
