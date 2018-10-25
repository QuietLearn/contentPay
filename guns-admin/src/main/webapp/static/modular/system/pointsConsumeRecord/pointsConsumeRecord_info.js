/**
 * 初始化积分流水记录详情对话框
 */
var PointsConsumeRecordInfoDlg = {
    pointsConsumeRecordInfoData : {}
};

/**
 * 清除数据
 */
PointsConsumeRecordInfoDlg.clearData = function() {
    this.pointsConsumeRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PointsConsumeRecordInfoDlg.set = function(key, val) {
    this.pointsConsumeRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PointsConsumeRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PointsConsumeRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.PointsConsumeRecord.layerIndex);
}

/**
 * 收集数据
 */
PointsConsumeRecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('points')
    .set('memberId')
    .set('videoId')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
PointsConsumeRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pointsConsumeRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.PointsConsumeRecord.table.refresh();
        PointsConsumeRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pointsConsumeRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PointsConsumeRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pointsConsumeRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.PointsConsumeRecord.table.refresh();
        PointsConsumeRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pointsConsumeRecordInfoData);
    ajax.start();
}

$(function() {

});
