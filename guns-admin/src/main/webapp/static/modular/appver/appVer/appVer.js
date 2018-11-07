/**
 * 应用版本管理初始化
 */
var AppVer = {
    id: "AppVerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AppVer.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '自动编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '版本号', field: 'appVer', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'label', visible: true, align: 'center', valign: 'middle'},
            {title: '逻辑删除', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AppVer.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AppVer.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加应用版本
 */
AppVer.openAddAppVer = function () {
    var index = layer.open({
        type: 2,
        title: '添加应用版本',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/appVer/appVer_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看应用版本详情
 */
AppVer.openAppVerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '应用版本详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/appVer/appVer_update/' + AppVer.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除应用版本
 */
AppVer.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/appVer/delete", function (data) {
            Feng.success("删除成功!");
            AppVer.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("appVerId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询应用版本列表
 */
AppVer.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AppVer.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AppVer.initColumn();
    var table = new BSTable(AppVer.id, "/appVer/list", defaultColunms);
    table.setPaginationType("client");
    AppVer.table = table.init();
});
