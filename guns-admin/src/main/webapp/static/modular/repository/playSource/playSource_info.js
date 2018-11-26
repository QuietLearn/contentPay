/**
 * 初始化播放源详情对话框
 */
var PlaySourceInfoDlg = {
    playSourceInfoData : {}
};

/**
 * 清除数据
 */
PlaySourceInfoDlg.clearData = function() {
    this.playSourceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlaySourceInfoDlg.set = function(key, val) {
    this.playSourceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlaySourceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PlaySourceInfoDlg.close = function() {
    parent.layer.close(window.parent.PlaySource.layerIndex);
}

/**
 * 收集数据
 */
PlaySourceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appVideoid')
    .set('videoSourceid')
    .set('videoAddress')
    .set('isCheck')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
PlaySourceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/playSource/add", function(data){
        Feng.success("添加成功!");
        window.parent.PlaySource.table.refresh();
        PlaySourceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.playSourceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PlaySourceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/playSource/update", function(data){
        Feng.success("修改成功!");
        window.parent.PlaySource.table.refresh();
        PlaySourceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.playSourceInfoData);
    ajax.start();
}

$(function() {

});
