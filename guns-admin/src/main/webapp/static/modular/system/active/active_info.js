/**
 * 初始化激活统计详情对话框
 */
var ActiveInfoDlg = {
    activeInfoData : {}
};

/**
 * 清除数据
 */
ActiveInfoDlg.clearData = function() {
    this.activeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActiveInfoDlg.set = function(key, val) {
    this.activeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ActiveInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ActiveInfoDlg.close = function() {
    parent.layer.close(window.parent.Active.layerIndex);
}

/**
 * 收集数据
 */
ActiveInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appId')
    .set('appVer')
    .set('channelId')
    .set('operator')
    .set('address')
    .set('imsi')
    .set('imei')
    .set('smsc')
    .set('isDel')
    .set('activeType')
    .set('phoneType')
    .set('phoneBrand')
    .set('phoneSystem')
    .set('dpi')
    .set('uuid')
    .set('mobile')
    .set('iccid')
    .set('pointType')
    .set('wifiType')
    .set('idfa')
    .set('openUdid')
    .set('isToutiao')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
ActiveInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/active/add", function(data){
        Feng.success("添加成功!");
        window.parent.Active.table.refresh();
        ActiveInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.activeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ActiveInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/active/update", function(data){
        Feng.success("修改成功!");
        window.parent.Active.table.refresh();
        ActiveInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.activeInfoData);
    ajax.start();
}

$(function() {

});
