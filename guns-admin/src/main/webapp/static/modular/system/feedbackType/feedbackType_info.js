/**
 * 初始化反馈类型字典详情对话框
 */
var FeedbackTypeInfoDlg = {
    feedbackTypeInfoData : {}
};

/**
 * 清除数据
 */
FeedbackTypeInfoDlg.clearData = function() {
    this.feedbackTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FeedbackTypeInfoDlg.set = function(key, val) {
    this.feedbackTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FeedbackTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FeedbackTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.FeedbackType.layerIndex);
}

/**
 * 收集数据
 */
FeedbackTypeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('typeCode')
    .set('typeName')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
FeedbackTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/feedbackType/add", function(data){
        Feng.success("添加成功!");
        window.parent.FeedbackType.table.refresh();
        FeedbackTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.feedbackTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FeedbackTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/feedbackType/update", function(data){
        Feng.success("修改成功!");
        window.parent.FeedbackType.table.refresh();
        FeedbackTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.feedbackTypeInfoData);
    ajax.start();
}

$(function() {

});
