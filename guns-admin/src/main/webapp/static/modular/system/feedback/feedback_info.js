/**
 * 初始化用户反馈详情对话框
 */
var FeedbackInfoDlg = {
    feedbackInfoData : {}
};

/**
 * 清除数据
 */
FeedbackInfoDlg.clearData = function() {
    this.feedbackInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FeedbackInfoDlg.set = function(key, val) {
    this.feedbackInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FeedbackInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FeedbackInfoDlg.close = function() {
    parent.layer.close(window.parent.Feedback.layerIndex);
}

/**
 * 收集数据
 */
FeedbackInfoDlg.collectData = function() {
    this
    .set('id')
    .set('info')
    .set('memberId')
    .set('memberName')
    .set('feedbackType')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified')
    .set('appId')
    .set('appVer')
    .set('channel');
}

/**
 * 提交添加
 */
FeedbackInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/feedback/add", function(data){
        Feng.success("添加成功!");
        window.parent.Feedback.table.refresh();
        FeedbackInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.feedbackInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FeedbackInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/feedback/update", function(data){
        Feng.success("修改成功!");
        window.parent.Feedback.table.refresh();
        FeedbackInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.feedbackInfoData);
    ajax.start();
}

$(function() {

});
