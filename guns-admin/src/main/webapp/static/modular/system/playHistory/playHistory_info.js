/**
 * 初始化用户播放历史详情对话框
 */
var PlayHistoryInfoDlg = {
    playHistoryInfoData : {}
};

/**
 * 清除数据
 */
PlayHistoryInfoDlg.clearData = function() {
    this.playHistoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlayHistoryInfoDlg.set = function(key, val) {
    this.playHistoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlayHistoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PlayHistoryInfoDlg.close = function() {
    parent.layer.close(window.parent.PlayHistory.layerIndex);
}

/**
 * 收集数据
 */
PlayHistoryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('memberId')
    .set('memberUsername')
    .set('videoId')
    .set('videoName')
    .set('videoNote')
    .set('videoPic')
    .set('videoActor')
    .set('isDel')
    .set('playTime')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
PlayHistoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/playHistory/add", function(data){
        Feng.success("添加成功!");
        window.parent.PlayHistory.table.refresh();
        PlayHistoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.playHistoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PlayHistoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/playHistory/update", function(data){
        Feng.success("修改成功!");
        window.parent.PlayHistory.table.refresh();
        PlayHistoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.playHistoryInfoData);
    ajax.start();
}

$(function() {

});
