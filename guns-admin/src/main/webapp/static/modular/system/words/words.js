/**
 * 关键字管理初始化
 */
var Words = {
    id: "WordsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Words.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '关键字名字', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '删除', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Words.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Words.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加关键字
 */
Words.openAddWords = function () {
    var index = layer.open({
        type: 2,
        title: '添加关键字',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/words/words_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看关键字详情
 */
Words.openWordsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '关键字详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/words/words_update/' + Words.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除关键字
 */
Words.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/words/delete", function (data) {
            Feng.success("删除成功!");
            Words.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wordsId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询关键字列表
 */
Words.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Words.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Words.initColumn();
    var table = new BSTable(Words.id, "/words/list", defaultColunms);
    table.setPaginationType("client");
    Words.table = table.init();
});
