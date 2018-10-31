/**
 * 激活统计管理初始化
 */
var Active = {
    id: "ActiveTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Active.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'app id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
            {title: 'app 版本号', field: 'appVer', visible: true, align: 'center', valign: 'middle'},
            {title: ' 渠道号', field: 'channelId', visible: true, align: 'center', valign: 'middle'},
            {title: '运营商', field: 'operator', visible: true, align: 'center', valign: 'middle'},
            {title: '省份地区', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: 'IMSI', field: 'imsi', visible: true, align: 'center', valign: 'middle'},
            {title: 'IMEI', field: 'imei', visible: true, align: 'center', valign: 'middle'},
            {title: '短信中心号码', field: 'smsc', visible: false, align: 'center', valign: 'middle'},
            {title: '是否删除 0 未删除 1 删除', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '0  开启   1  激活  2  手动开启', field: 'activeType', visible: true, align: 'center', valign: 'middle'},
            {title: '手机型号', field: 'phoneType', visible: true, align: 'center', valign: 'middle'},
            {title: '手机品牌', field: 'phoneBrand', visible: true, align: 'center', valign: 'middle'},
            {title: '手机系统', field: 'phoneSystem', visible: true, align: 'center', valign: 'middle'},
            {title: '分辨率', field: 'dpi', visible: true, align: 'center', valign: 'middle'},
            {title: 'uuid', field: 'uuid', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '手机识别码', field: 'iccid', visible: true, align: 'center', valign: 'middle'},
            // 1 安卓 2ios
            {title: '埋点类型', field: 'pointType', visible: true, align: 'center', valign: 'middle'},
            {title: '网络类型', field: 'wifiType', visible: true, align: 'center', valign: 'middle'},
            {title: '广告标识', field: 'idfa', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'openUdid', visible: false, align: 'center', valign: 'middle'},
            {title: '1 代表来自头条', field: 'isToutiao', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Active.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Active.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加激活统计
 */
Active.openAddActive = function () {
    var index = layer.open({
        type: 2,
        title: '添加激活统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/active/active_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看激活统计详情
 */
Active.openActiveDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '激活统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/active/active_update/' + Active.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除激活统计
 */
Active.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/active/delete", function (data) {
            Feng.success("删除成功!");
            Active.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("activeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询激活统计列表
 */
Active.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Active.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Active.initColumn();
    var table = new BSTable(Active.id, "/active/list", defaultColunms);
    table.setPaginationType("client");
    Active.table = table.init();
});
