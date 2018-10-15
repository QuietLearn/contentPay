/**
 * 章节管理管理初始化
 */
var Chapter = {
    id: "ChapterTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Chapter.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '小说编号', field: 'novelId', visible: true, align: 'center', valign: 'middle'},
            {title: '章节编号', field: 'number', visible: true, align: 'center', valign: 'middle'},
            {title: '章节名称', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '章节内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '删除', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Chapter.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Chapter.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加章节管理
 */
Chapter.openAddChapter = function () {
    var index = layer.open({
        type: 2,
        title: '添加章节管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/chapter/chapter_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看章节管理详情
 */
Chapter.openChapterDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '章节管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/chapter/chapter_update/' + Chapter.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除章节管理
 */
Chapter.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/chapter/delete", function (data) {
            Feng.success("删除成功!");
            Chapter.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("chapterId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询章节管理列表
 */
Chapter.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Chapter.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Chapter.initColumn();
    var table = new BSTable(Chapter.id, "/chapter/list", defaultColunms);
    table.setPaginationType("client");
    Chapter.table = table.init();
});
