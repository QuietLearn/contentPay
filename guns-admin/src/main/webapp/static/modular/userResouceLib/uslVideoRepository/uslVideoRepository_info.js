/**
 * 初始化发布视频详情对话框
 */
var UslVideoRepositoryInfoDlg = {
    uslVideoRepositoryInfoData : {}
};

/**
 * 清除数据
 */
UslVideoRepositoryInfoDlg.clearData = function() {
    this.uslVideoRepositoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UslVideoRepositoryInfoDlg.set = function(key, val) {
    this.uslVideoRepositoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UslVideoRepositoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UslVideoRepositoryInfoDlg.close = function() {
    parent.layer.close(window.parent.UslVideoRepository.layerIndex);
}

/**
 * 收集数据
 */
UslVideoRepositoryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('categoryId')
    .set('pictureId')
    .set('coverImage')
    .set('labelIds')
    .set('title')
    .set('introduction')
    .set('playsNumber')
    .set('videoAddress')
    .set('countryId')
    .set('times')
    .set('isDel')
    .set('sort')
    .set('browse')
    .set('userId')
    .set('collectionCount')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
UslVideoRepositoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/uslVideoRepository/add", function(data){
        Feng.success("添加成功!");
        window.parent.UslVideoRepository.table.refresh();
        UslVideoRepositoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.uslVideoRepositoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UslVideoRepositoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/uslVideoRepository/update", function(data){
        Feng.success("修改成功!");
        window.parent.UslVideoRepository.table.refresh();
        UslVideoRepositoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.uslVideoRepositoryInfoData);
    ajax.start();
}

$(function() {

});
