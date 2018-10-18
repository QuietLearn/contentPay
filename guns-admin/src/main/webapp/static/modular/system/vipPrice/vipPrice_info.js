/**
 * 初始化会员价格管理详情对话框
 */
var VipPriceInfoDlg = {
    vipPriceInfoData : {}
};

/**
 * 清除数据
 */
VipPriceInfoDlg.clearData = function() {
    this.vipPriceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VipPriceInfoDlg.set = function(key, val) {
    this.vipPriceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VipPriceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VipPriceInfoDlg.close = function() {
    parent.layer.close(window.parent.VipPrice.layerIndex);
}

/**
 * 收集数据
 */
VipPriceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('memberTypeCode')
    .set('name')
    .set('price')
    .set('aging')
    .set('gmtCreated')
    .set('gmtUpdated');
}

/**
 * 提交添加
 */
VipPriceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/vipPrice/add", function(data){
        Feng.success("添加成功!");
        window.parent.VipPrice.table.refresh();
        VipPriceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.vipPriceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VipPriceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/vipPrice/update", function(data){
        Feng.success("修改成功!");
        window.parent.VipPrice.table.refresh();
        VipPriceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.vipPriceInfoData);
    ajax.start();
}

$(function() {

});
