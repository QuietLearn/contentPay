/**
 * 图集管理管理初始化
 */
var Atlas = {
    id: "AtlasTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Atlas.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户', field: 'accountId', visible: true, align: 'center', valign: 'middle'},
            {title: '图片编号', field: 'picId', visible: true, align: 'center', valign: 'middle'},
            {title: '图片名称', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '支付积分', field: 'payPoints', visible: true, align: 'center', valign: 'middle'},
            {title: '标签类型', field: 'tagsIds', visible: true, align: 'center', valign: 'middle'},
            {title: '封面', field: 'coverPic', visible: true, align: 'center', valign: 'middle'},
            {title: '图集类型', field: 'typeId', visible: true, align: 'center', valign: 'middle'},
            {title: '图集编号', field: 'number', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '图片地址', field: 'picaddress', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtcreated', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtmodified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Atlas.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Atlas.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加图集管理
 */
Atlas.openAddAtlas = function () {
    var index = layer.open({
        type: 2,
        title: '添加图集管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/atlas/atlas_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看图集管理详情
 */
Atlas.openAtlasDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '图集管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/atlas/atlas_update/' + Atlas.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除图集管理
 */
Atlas.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/atlas/delete", function (data) {
            Feng.success("删除成功!");
            Atlas.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("atlasId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询图集管理列表
 */
Atlas.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Atlas.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Atlas.initColumn();
    var table = new BSTable(Atlas.id, "/atlas/list", defaultColunms);
    table.setPaginationType("client");
    Atlas.table = table.init();
});
