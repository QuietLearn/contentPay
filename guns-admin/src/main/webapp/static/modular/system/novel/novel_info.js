/**
 * 初始化小说管理详情对话框
 */
var NovelInfoDlg = {
    novelInfoData : {}
};

/**
 * 清除数据
 */
NovelInfoDlg.clearData = function() {
    this.novelInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NovelInfoDlg.set = function(key, val) {
    this.novelInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NovelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NovelInfoDlg.close = function() {
    parent.layer.close(window.parent.Novel.layerIndex);
}

/**
 * 收集数据
 */
NovelInfoDlg.collectData = function() {
    this
    .set('id')
    .set('accountId')
    .set('title')
    .set('picAddress')
    .set('type')
    .set('author')
    .set('refreshTime')
    .set('status')
    .set('payPoints')
    .set('description')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
NovelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/novel/add", function(data){
        Feng.success("添加成功!");
        window.parent.Novel.table.refresh();
        NovelInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.novelInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NovelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/novel/update", function(data){
        Feng.success("修改成功!");
        window.parent.Novel.table.refresh();
        NovelInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.novelInfoData);
    ajax.start();
}

$(function() {

});
