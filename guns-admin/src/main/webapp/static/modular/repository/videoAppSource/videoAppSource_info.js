/**
 * 初始化视频应用内容详情对话框
 */
var VideoAppSourceInfoDlg = {
    videoAppSourceInfoData : {}
};

/**
 * 清除数据
 */
VideoAppSourceInfoDlg.clearData = function() {
    this.videoAppSourceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoAppSourceInfoDlg.set = function(key, val) {
    this.videoAppSourceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoAppSourceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VideoAppSourceInfoDlg.close = function() {
    parent.layer.close(window.parent.VideoAppSource.layerIndex);
}

/**
 * 收集数据
 */
VideoAppSourceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appContentId')
    .set('videoId')
    .set('authority')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
VideoAppSourceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoAppSource/add", function(data){
        Feng.success("添加成功!");
        window.parent.VideoAppSource.table.refresh();
        VideoAppSourceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoAppSourceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VideoAppSourceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoAppSource/update", function(data){
        Feng.success("修改成功!");
        window.parent.VideoAppSource.table.refresh();
        VideoAppSourceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoAppSourceInfoData);
    ajax.start();
}

$(function() {

});
