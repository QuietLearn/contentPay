/**
 * 初始化积分价格详情对话框
 */
var PointsGradeInfoDlg = {
    pointsGradeInfoData : {}
};

/**
 * 清除数据
 */
PointsGradeInfoDlg.clearData = function() {
    this.pointsGradeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PointsGradeInfoDlg.set = function(key, val) {
    this.pointsGradeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PointsGradeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PointsGradeInfoDlg.close = function() {
    parent.layer.close(window.parent.PointsGrade.layerIndex);
}

/**
 * 收集数据
 */
PointsGradeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('points')
    .set('money')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
PointsGradeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pointsGrade/add", function(data){
        Feng.success("添加成功!");
        window.parent.PointsGrade.table.refresh();
        PointsGradeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pointsGradeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PointsGradeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pointsGrade/update", function(data){
        Feng.success("修改成功!");
        window.parent.PointsGrade.table.refresh();
        PointsGradeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pointsGradeInfoData);
    ajax.start();
}

$(function() {

});
