/**
 * 初始化图集资源库详情对话框
 */
var PicturesCategoryInfoDlg = {
    picturesCategoryInfoData : {}
};

/**
 * 清除数据
 */
PicturesCategoryInfoDlg.clearData = function() {
    this.picturesCategoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PicturesCategoryInfoDlg.set = function(key, val) {
    this.picturesCategoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PicturesCategoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PicturesCategoryInfoDlg.close = function() {
    parent.layer.close(window.parent.PicturesCategory.layerIndex);
}

/**
 * 收集数据
 */
PicturesCategoryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('categoryId')
    .set('labelIds')
    .set('name')
    .set('picturesNum')
    .set('views')
    .set('modelId')
    .set('thumbupCount')
    .set('shareCount')
    .set('collectionCount')
    .set('picAddress')
    .set('scalePicaddress')
    .set('isDel')
    .set('cover')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
PicturesCategoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/picturesCategory/add", function(data){
        Feng.success("添加成功!");
        window.parent.PicturesCategory.table.refresh();
        PicturesCategoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.picturesCategoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PicturesCategoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/picturesCategory/update", function(data){
        Feng.success("修改成功!");
        window.parent.PicturesCategory.table.refresh();
        PicturesCategoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.picturesCategoryInfoData);
    ajax.start();
}

$(function() {

});
