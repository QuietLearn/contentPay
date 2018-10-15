/**
 * 初始化支付积分详情对话框
 */
var PayPointsInfoDlg = {
    payPointsInfoData : {}
};

/**
 * 清除数据
 */
PayPointsInfoDlg.clearData = function() {
    this.payPointsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayPointsInfoDlg.set = function(key, val) {
    this.payPointsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayPointsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PayPointsInfoDlg.close = function() {
    parent.layer.close(window.parent.PayPoints.layerIndex);
}

/**
 * 收集数据
 */
PayPointsInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appid')
    .set('accoutid')
    .set('type')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
PayPointsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payPoints/add", function(data){
        Feng.success("添加成功!");
        window.parent.PayPoints.table.refresh();
        PayPointsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payPointsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PayPointsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payPoints/update", function(data){
        Feng.success("修改成功!");
        window.parent.PayPoints.table.refresh();
        PayPointsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payPointsInfoData);
    ajax.start();
}

$(function() {

});
