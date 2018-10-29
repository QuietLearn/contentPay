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
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'app  id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
            {title: 'app 版本号', field: 'appVer', visible: true, align: 'center', valign: 'middle'},
            {title: ' 渠道号', field: 'channelId', visible: true, align: 'center', valign: 'middle'},
            {title: '运营商 1移动 2联通 3 电信', field: 'operator', visible: true, align: 'center', valign: 'middle'},
            {title: '省份 地区', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '移动设备识别码', field: 'imsi', visible: true, align: 'center', valign: 'middle'},
            {title: '移动设备身份码', field: 'imei', visible: true, align: 'center', valign: 'middle'},
            {title: '短信中心号码', field: 'smsc', visible: true, align: 'center', valign: 'middle'},
            {title: '100  开启　101 激活　102　弹出计费框　 103 切换到后台 104 切回应用 105 应用退出 106 应用崩溃 107 注册  108 取消注册 109 忘记密码 111 登录  112 登录取消 113 首页  115 频道页   117 列表页 119 详情页 121 播放页', field: 'pointId', visible: true, align: 'center', valign: 'middle'},
            {title: '埋点信息', field: 'pointMessage', visible: true, align: 'center', valign: 'middle'},
            {title: '支付类型 1 ZZF  2 DM 3 LFT', field: 'payType', visible: true, align: 'center', valign: 'middle'},
            {title: '用户唯一识别号码', field: 'uuid', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '网络类型', field: 'wifiType', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号码', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '手机识别码', field: 'iccid', visible: true, align: 'center', valign: 'middle'},
            {title: '支付渠道号', field: 'payChannelid', visible: true, align: 'center', valign: 'middle'},
            {title: '错误信息', field: 'errorMessage', visible: true, align: 'center', valign: 'middle'},
            {title: '//埋点类型  1 安卓 2 ios', field: 'pointType', visible: true, align: 'center', valign: 'middle'},
            {title: '广告标识', field: 'idfa', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'openUdid', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
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
    BuriedPoint.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BuriedPoint.initColumn();
    var table = new BSTable(BuriedPoint.id, "/buriedPoint/list", defaultColunms);
    table.setPaginationType("client");
    BuriedPoint.table = table.init();
});
