/**
 * 初始化图集应用内容详情对话框
 */
var PhotosAppSourceInfoDlg = {
    photosAppSourceInfoData : {}
};

/**
 * 清除数据
 */
PhotosAppSourceInfoDlg.clearData = function() {
    this.photosAppSourceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PhotosAppSourceInfoDlg.set = function(key, val) {
    this.photosAppSourceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PhotosAppSourceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PhotosAppSourceInfoDlg.close = function() {
    parent.layer.close(window.parent.PhotosAppSource.layerIndex);
}

/**
 * 收集数据
 */
PhotosAppSourceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appContentId')
    .set('picturesCategoryId')
    .set('authority')
    .set('isdel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
PhotosAppSourceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/photosAppSource/add", function(data){
        Feng.success("添加成功!");
        window.parent.PhotosAppSource.table.refresh();
        PhotosAppSourceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.photosAppSourceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PhotosAppSourceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/photosAppSource/update", function(data){
        Feng.success("修改成功!");
        window.parent.PhotosAppSource.table.refresh();
        PhotosAppSourceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.photosAppSourceInfoData);
    ajax.start();
}

$(function() {

});
