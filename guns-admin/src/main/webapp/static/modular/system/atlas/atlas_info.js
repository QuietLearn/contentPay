/**
 * 初始化图集管理详情对话框
 */
var AtlasInfoDlg = {
    atlasInfoData : {}
};

/**
 * 清除数据
 */
AtlasInfoDlg.clearData = function() {
    this.atlasInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AtlasInfoDlg.set = function(key, val) {
    this.atlasInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AtlasInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AtlasInfoDlg.close = function() {
    parent.layer.close(window.parent.Atlas.layerIndex);
}

/**
 * 收集数据
 */
AtlasInfoDlg.collectData = function() {
    this
    .set('id')
    .set('accountId')
    .set('picId')
    .set('title')
    .set('description')
    .set('payPoints')
    .set('tagsIds')
    .set('coverPic')
    .set('typeId')
    .set('number')
    .set('isDel')
    .set('picaddress')
    .set('gmtcreated')
    .set('gmtmodified');
}

/**
 * 提交添加
 */
AtlasInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/atlas/add", function(data){
        Feng.success("添加成功!");
        window.parent.Atlas.table.refresh();
        AtlasInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.atlasInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AtlasInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/atlas/update", function(data){
        Feng.success("修改成功!");
        window.parent.Atlas.table.refresh();
        AtlasInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.atlasInfoData);
    ajax.start();
}

$(function() {

});
