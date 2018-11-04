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
        {field: 'selectItem', radio: true},
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
 * 查询埋点统计列表
 */
BuriedPoint.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['appId'] = $("#appId").val();
    BuriedPoint.table.refresh({query: queryData});
};

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