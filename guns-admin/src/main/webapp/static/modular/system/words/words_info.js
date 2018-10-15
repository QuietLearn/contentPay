/**
 * 初始化关键字详情对话框
 */
var WordsInfoDlg = {
    wordsInfoData : {}
};

/**
 * 清除数据
 */
WordsInfoDlg.clearData = function() {
    this.wordsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WordsInfoDlg.set = function(key, val) {
    this.wordsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WordsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WordsInfoDlg.close = function() {
    parent.layer.close(window.parent.Words.layerIndex);
}

/**
 * 收集数据
 */
WordsInfoDlg.collectData = function() {
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
WordsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/words/add", function(data){
        Feng.success("添加成功!");
        window.parent.Words.table.refresh();
        WordsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wordsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WordsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/words/update", function(data){
        Feng.success("修改成功!");
        window.parent.Words.table.refresh();
        WordsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wordsInfoData);
    ajax.start();
}

$(function() {

});
