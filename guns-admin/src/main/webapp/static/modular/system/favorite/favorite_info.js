/**
 * 初始化用户收藏夹管理详情对话框
 */
var FavoriteInfoDlg = {
    favoriteInfoData : {}
};

/**
 * 清除数据
 */
FavoriteInfoDlg.clearData = function() {
    this.favoriteInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FavoriteInfoDlg.set = function(key, val) {
    this.favoriteInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FavoriteInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FavoriteInfoDlg.close = function() {
    parent.layer.close(window.parent.Favorite.layerIndex);
}

/**
 * 收集数据
 */
FavoriteInfoDlg.collectData = function() {
    this
    .set('id')
    .set('typeId')
    .set('videoId')
    .set('videoName')
    .set('videoNote')
    .set('videoPic')
    .set('videoActor')
    .set('videoDirector')
    .set('videoPublisharea')
    .set('videoPublishyear')
    .set('memberId')
    .set('memberUsername')
    .set('sort')
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
FavoriteInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/favorite/add", function(data){
        Feng.success("添加成功!");
        window.parent.Favorite.table.refresh();
        FavoriteInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.favoriteInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FavoriteInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/favorite/update", function(data){
        Feng.success("修改成功!");
        window.parent.Favorite.table.refresh();
        FavoriteInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.favoriteInfoData);
    ajax.start();
}

$(function() {

});
