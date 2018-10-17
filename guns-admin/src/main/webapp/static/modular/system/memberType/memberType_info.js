/**
 * 初始化会员购买类型详情对话框
 */
var MemberTypeInfoDlg = {
    memberTypeInfoData : {}
};

/**
 * 清除数据
 */
MemberTypeInfoDlg.clearData = function() {
    this.memberTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberTypeInfoDlg.set = function(key, val) {
    this.memberTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.MemberType.layerIndex);
}

/**
 * 收集数据
 */
MemberTypeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('serverCode')
    .set('serverName')
    .set('price')
    .set('aging')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
MemberTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberType/add", function(data){
        Feng.success("添加成功!");
        window.parent.MemberType.table.refresh();
        MemberTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberType/update", function(data){
        Feng.success("修改成功!");
        window.parent.MemberType.table.refresh();
        MemberTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberTypeInfoData);
    ajax.start();
}

$(function() {

});
