/**
 * 初始化图片管理详情对话框
 */
var PictureInfoDlg = {
    pictureInfoData : {}
};

/**
 * 清除数据
 */
PictureInfoDlg.clearData = function() {
    this.pictureInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PictureInfoDlg.set = function(key, val) {
    this.pictureInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PictureInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PictureInfoDlg.close = function() {
    parent.layer.close(window.parent.Picture.layerIndex);
}

/**
 * 收集数据
 */
PictureInfoDlg.collectData = function() {
    this
    .set('id')
    .set('accountId')
    .set('title')
    .set('isDel')
    .set('description')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
PictureInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/picture/add", function(data){
        Feng.success("添加成功!");
        window.parent.Picture.table.refresh();
        PictureInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pictureInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PictureInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/picture/update", function(data){
        Feng.success("修改成功!");
        window.parent.Picture.table.refresh();
        PictureInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pictureInfoData);
    ajax.start();
}

$(function() {

});
