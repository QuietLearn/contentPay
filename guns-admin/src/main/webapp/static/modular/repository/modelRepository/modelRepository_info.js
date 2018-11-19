/**
 * 初始化模特资源库详情对话框
 */
var ModelRepositoryInfoDlg = {
    modelRepositoryInfoData : {}
};

/**
 * 清除数据
 */
ModelRepositoryInfoDlg.clearData = function() {
    this.modelRepositoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ModelRepositoryInfoDlg.set = function(key, val) {
    this.modelRepositoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ModelRepositoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ModelRepositoryInfoDlg.close = function() {
    parent.layer.close(window.parent.ModelRepository.layerIndex);
}

/**
 * 收集数据
 */
ModelRepositoryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('userName')
    .set('password')
    .set('pwdMD5')
    .set('role')
    .set('picAddress')
    .set('description')
    .set('signature')
    .set('notes')
    .set('sort')
    .set('isDel')
    .set('nickName')
    .set('height')
    .set('measurements')
    .set('countriesid')
    .set('age')
    .set('characteristics')
    .set('birth')
    .set('focusCount')
    .set('isChat')
    .set('mobile')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
ModelRepositoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/modelRepository/add", function(data){
        Feng.success("添加成功!");
        window.parent.ModelRepository.table.refresh();
        ModelRepositoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.modelRepositoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ModelRepositoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/modelRepository/update", function(data){
        Feng.success("修改成功!");
        window.parent.ModelRepository.table.refresh();
        ModelRepositoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.modelRepositoryInfoData);
    ajax.start();
}

$(function() {

});
