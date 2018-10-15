/**
 * 初始化支付渠道详情对话框
 */
var PayChannelInfoDlg = {
    payChannelInfoData : {}
};

/**
 * 清除数据
 */
PayChannelInfoDlg.clearData = function() {
    this.payChannelInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayChannelInfoDlg.set = function(key, val) {
    this.payChannelInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayChannelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PayChannelInfoDlg.close = function() {
    parent.layer.close(window.parent.PayChannel.layerIndex);
}

/**
 * 收集数据
 */
PayChannelInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('isDel')
    .set('payCode')
    .set('payType')
    .set('isChoose')
    .set('sort')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
PayChannelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payChannel/add", function(data){
        Feng.success("添加成功!");
        window.parent.PayChannel.table.refresh();
        PayChannelInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payChannelInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PayChannelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payChannel/update", function(data){
        Feng.success("修改成功!");
        window.parent.PayChannel.table.refresh();
        PayChannelInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payChannelInfoData);
    ajax.start();
}

$(function() {

});
