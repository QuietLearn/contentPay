/**
 * 初始化模特应用内容详情对话框
 */
var ModelAppSourceInfoDlg = {
    modelAppSourceInfoData : {}
};

/**
 * 清除数据
 */
ModelAppSourceInfoDlg.clearData = function() {
    this.modelAppSourceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ModelAppSourceInfoDlg.set = function(key, val) {
    this.modelAppSourceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ModelAppSourceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ModelAppSourceInfoDlg.close = function() {
    parent.layer.close(window.parent.ModelAppSource.layerIndex);
}

/**
 * 收集数据
 */
ModelAppSourceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appContentId')
    .set('modelId')
    .set('authority')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
ModelAppSourceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/modelAppSource/add", function(data){
        Feng.success("添加成功!");
        window.parent.ModelAppSource.table.refresh();
        ModelAppSourceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.modelAppSourceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ModelAppSourceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/modelAppSource/update", function(data){
        Feng.success("修改成功!");
        window.parent.ModelAppSource.table.refresh();
        ModelAppSourceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.modelAppSourceInfoData);
    ajax.start();
}

$(function() {

});
