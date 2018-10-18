/**
 * 初始化视频管理详情对话框
 */
var DataInfoDlg = {
    dataInfoData : {}
};

/**
 * 清除数据
 */
DataInfoDlg.clearData = function() {
    this.dataInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DataInfoDlg.set = function(key, val) {
    this.dataInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DataInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DataInfoDlg.close = function() {
    parent.layer.close(window.parent.Data.layerIndex);
}

/**
 * 收集数据
 */
DataInfoDlg.collectData = function() {
    this
    .set('vId')
    .set('tid')
    .set('vName')
    .set('vState')
    .set('vPic')
    .set('vSpic')
    .set('vGpic')
    .set('vHit')
    .set('vMoney')
    .set('vRank')
    .set('vDigg')
    .set('vTread')
    .set('vCommend')
    .set('vWrong')
    .set('vIsmake')
    .set('vActor')
    .set('vColor')
    .set('vPublishyear')
    .set('vPublisharea')
    .set('vAddtime')
    .set('vTopic')
    .set('vNote')
    .set('vTags')
    .set('vLetter')
    .set('vIsunion')
    .set('vRecycled')
    .set('vDirector')
    .set('vEnname')
    .set('vLang')
    .set('vScore')
    .set('vScorenum')
    .set('vExtratype')
    .set('vJq')
    .set('vNickname')
    .set('vReweek')
    .set('vDouban')
    .set('vMtime')
    .set('vImdb')
    .set('vTvs')
    .set('vCompany')
    .set('vDayhit')
    .set('vWeekhit')
    .set('vMonthhit')
    .set('vDaytime')
    .set('vWeektime')
    .set('vMonthtime')
    .set('vReqVip')
    .set('vLen')
    .set('vTotal')
    .set('vVer')
    .set('vPsd')
    .set('vLongtxt');
}

/**
 * 提交添加
 */
DataInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/data/add", function(data){
        Feng.success("添加成功!");
        window.parent.Data.table.refresh();
        DataInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dataInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DataInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/data/update", function(data){
        Feng.success("修改成功!");
        window.parent.Data.table.refresh();
        DataInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dataInfoData);
    ajax.start();
}

$(function() {

});
