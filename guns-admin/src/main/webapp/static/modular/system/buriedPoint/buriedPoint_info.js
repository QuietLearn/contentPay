/**
 * 初始化埋点统计详情对话框
 */
var BuriedPointInfoDlg = {
    buriedPointInfoData : {}
};

/**
 * 清除数据
 */
BuriedPointInfoDlg.clearData = function() {
    this.buriedPointInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BuriedPointInfoDlg.set = function(key, val) {
    this.buriedPointInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BuriedPointInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BuriedPointInfoDlg.close = function() {
    parent.layer.close(window.parent.BuriedPoint.layerIndex);
}

/**
 * 收集数据
 */
BuriedPointInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appId')
    .set('appVer')
    .set('channelId')
    .set('operator')
    .set('address')
    .set('imsi')
    .set('imei')
    .set('pointId')
    .set('pointMessage')
    .set('payType')
    .set('uuid')
    .set('isDel')
    .set('wifiType')
    .set('mobile')
    .set('iccid')
    .set('phoneType')
    .set('errorMessage')
    .set('pointType')
    .set('idfa')
    .set('openUdid')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
BuriedPointInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/buriedPoint/add", function(data){
        Feng.success("添加成功!");
        window.parent.BuriedPoint.table.refresh();
        BuriedPointInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.buriedPointInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BuriedPointInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/buriedPoint/update", function(data){
        Feng.success("修改成功!");
        window.parent.BuriedPoint.table.refresh();
        BuriedPointInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.buriedPointInfoData);
    ajax.start();
}

/**
 * 显示埋点类型选择的树
 *
 * @returns
 */
BuriedPointInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
BuriedPointInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};


function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        BuriedPointInfoDlg.hideDeptSelectTree();
    }
}

BuriedPointInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#pointId").attr("value", treeNode.pointId);
};

$(function () {
    /*Feng.initValidator("userInfoForm", UserInfoDlg.validateFields);*/


    var ztree = new $ZTree("treeDemo", "/buriedPoint/tree");
    ztree.bindOnClick(BuriedPointInfoDlg.onClickDept);
    ztree.init();
    instance = ztree;

   /* 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();*/

});