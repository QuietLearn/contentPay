/**
 * 初始化视频价格详情对话框
 */
var VideoInfoDlg = {
    videoInfoData : {}
};

/**
 * 清除数据
 */
VideoInfoDlg.clearData = function() {
    this.videoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoInfoDlg.set = function(key, val) {
    this.videoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VideoInfoDlg.close = function() {
    parent.layer.close(window.parent.Video.layerIndex);
}

/**
 * 收集数据
 */
VideoInfoDlg.collectData = function() {
    this
    .set('vId')
    .set('tId')
    .set('vName')
    .set('vPic')
    .set('vPublishyear')
    .set('vAddtime')
    .set('vMoney')
    .set('vReqVip')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
VideoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/video/add", function(data){
        Feng.success("添加成功!");
        window.parent.Video.table.refresh();
        VideoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VideoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/video/update", function(data){
        Feng.success("修改成功!");
        window.parent.Video.table.refresh();
        VideoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoInfoData);
    ajax.start();
}

$(function() {

});
