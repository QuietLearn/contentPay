/**
 * 埋点统计管理初始化
 */
var BuriedPoint = {
    id: "BuriedPointTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BuriedPoint.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},

        {title: '应用', field: 'appName', visible: true, align: 'center', valign: 'middle'},
        {title: '应用id', field: 'appId', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本ver', field: 'appVer', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本', field: 'appVerName', visible: true, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channel', visible: false, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channelName', visible: true, align: 'center', valign: 'middle'},

            {title: '运营商', field: 'operator', visible: true, align: 'center', valign: 'middle'},
            {title: '省份地区', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: 'IMSI', field: 'imsi', visible: true, align: 'center', valign: 'middle'},
            {title: 'IMEI', field: 'imei', visible: true, align: 'center', valign: 'middle'},
            {title: '埋点类型', field: 'pointId', visible: true, align: 'center', valign: 'middle'},
            {title: '埋点信息', field: 'pointMessage', visible: true, align: 'center', valign: 'middle'},
            {title: '支付类型 1 ZZF  2 DM 3 LFT', field: 'payType', visible: false, align: 'center', valign: 'middle'},
            {title: '用户唯一识别号码', field: 'uuid', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '网络类型', field: 'wifiType', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号码', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '手机识别码', field: 'iccid', visible: true, align: 'center', valign: 'middle'},
            {title: '手机型号', field: 'phoneType', visible: false, align: 'center', valign: 'middle'},
            {title: '错误信息', field: 'errorMessage', visible: false, align: 'center', valign: 'middle'},
            {title: '埋点类型 ', field: 'pointType', visible: true, align: 'center', valign: 'middle'},
            {title: '广告标识', field: 'idfa', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'openUdid', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BuriedPoint.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BuriedPoint.seItem = selected[0];
        return true;
    }
};

//批量删除
function deletePointList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#BuriedPointTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deletePoints(ids);
};


/**
 * 点击添加埋点统计
 */
BuriedPoint.openAddBuriedPoint = function () {
    var index = layer.open({
        type: 2,
        title: '添加埋点统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/buriedPoint/buriedPoint_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看埋点统计详情
 */
BuriedPoint.openBuriedPointDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '埋点统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/buriedPoint/buriedPoint_update/' + BuriedPoint.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除埋点统计
 */
BuriedPoint.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/buriedPoint/delete", function (data) {
            Feng.success("删除成功!");
            BuriedPoint.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("buriedPointId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 批量删除埋点统计
 */
function deletePoints(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/buriedPoint/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                BuriedPoint.table.refresh();
                /* $("#user").bootstrapTable('refresh', {url: '/user/getUserList.do'});*/
            }, error:function (data) {
                alert(data.msg);
                BuriedPoint.table.refresh();
            }
        });
    }
};


/**
 * 查询埋点统计列表
 */
BuriedPoint.search = function () {
    var queryData = {};
    queryData['pointId'] = $("#pointId").val();
    queryData['appId'] = $("#appId").val();
    BuriedPoint.table.refresh({query: queryData});
};

/**
 * 显示埋点类型选择的树
 *
 * @returns
 */
BuriedPoint.showDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({
        left:cityOffset.left-30 + "px",
        //+ cityOffset.top
        top:  cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
BuriedPoint.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};


function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
        event.target).parents("#menuContent").length > 0)) {
        BuriedPoint.hideDeptSelectTree();
    }
}

BuriedPoint.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#pointId").attr("value", treeNode.pointId);
};

$(function () {
    /*Feng.initValidator("userInfoForm", UserInfoDlg.validateFields);*/


    var ztree = new $ZTree("treeDemo", "/buriedPoint/tree");
    ztree.bindOnClick(BuriedPoint.onClickDept);
    ztree.init();
    instance = ztree;

    /* 初始化头像上传
     var avatarUp = new $WebUpload("avatar");
     avatarUp.setUploadBarId("progressBar");
     avatarUp.init();*/

});


$(function () {
    var defaultColunms = BuriedPoint.initColumn();
    var table = new BSTable(BuriedPoint.id, "/buriedPoint/list", defaultColunms);
    table.setPaginationType("server");
    BuriedPoint.table = table.init();
});


$(function () {
    $.ajax({
        url: "/app/list",    //后台webservice里的方法名称
        contentType: "application/json; charset=utf-8",
        type: "get",
        async : true ,
        dataType: "json",
        success: function (data) {
            var optionstring = "";
            for (var j = 0; j < data.length;j++) {
                optionstring += "<option value=\"" + data[j].appId + "\" >" +data[j].appName + "</option>";
                $("#appId").html("<option value='0'>全部</option> "+optionstring);
            }
        },
        error: function (msg) {
            alert("出错了！");
        }
    });

});