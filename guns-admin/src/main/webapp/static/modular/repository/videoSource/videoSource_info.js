/**
 * 初始化视频来源详情对话框
 */
var VideoSourceInfoDlg = {
    videoSourceInfoData : {}
};

/**
 * 清除数据
 */
VideoSourceInfoDlg.clearData = function() {
    this.videoSourceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoSourceInfoDlg.set = function(key, val) {
    this.videoSourceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoSourceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VideoSourceInfoDlg.close = function() {
    parent.layer.close(window.parent.VideoSource.layerIndex);
}

/**
 * 收集数据
 */
VideoSourceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
VideoSourceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoSource/add", function(data){
        Feng.success("添加成功!");
        window.parent.VideoSource.table.refresh();
        VideoSourceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoSourceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VideoSourceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoSource/update", function(data){
        Feng.success("修改成功!");
        window.parent.VideoSource.table.refresh();
        VideoSourceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoSourceInfoData);
    ajax.start();
}

$(function() {

});
