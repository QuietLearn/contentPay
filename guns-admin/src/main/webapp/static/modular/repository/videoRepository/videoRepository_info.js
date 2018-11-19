/**
 * 初始化视频资源库详情对话框
 */
var VideoRepositoryInfoDlg = {
    videoRepositoryInfoData : {}
};

/**
 * 清除数据
 */
VideoRepositoryInfoDlg.clearData = function() {
    this.videoRepositoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoRepositoryInfoDlg.set = function(key, val) {
    this.videoRepositoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideoRepositoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VideoRepositoryInfoDlg.close = function() {
    parent.layer.close(window.parent.VideoRepository.layerIndex);
}

/**
 * 收集数据
 */
VideoRepositoryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('categoryId')
    .set('pictureId')
    .set('coverImage')
    .set('labelIds')
    .set('title')
    .set('introduction')
    .set('playsNumber')
    .set('videoAddressid')
    .set('countryId')
    .set('times')
    .set('isDel')
    .set('sort')
    .set('onePastTwo')
    .set('fivePastFour')
    .set('xPastx')
    .set('browse')
    .set('modelId')
    .set('collectionCount')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
VideoRepositoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoRepository/add", function(data){
        Feng.success("添加成功!");
        window.parent.VideoRepository.table.refresh();
        VideoRepositoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoRepositoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VideoRepositoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videoRepository/update", function(data){
        Feng.success("修改成功!");
        window.parent.VideoRepository.table.refresh();
        VideoRepositoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videoRepositoryInfoData);
    ajax.start();
}

$(function() {

});
