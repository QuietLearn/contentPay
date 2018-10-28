/**
 * 短信管理管理初始化
 */
var Note = {
    id: "NoteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Note.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '信息', field: 'message', visible: true, align: 'center', valign: 'middle'},
            {title: '短信有效时间(分)', field: 'aging', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '发送手机', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '发送时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtUpdated', visible: false, align: 'center', valign: 'middle'},
        {title: '应用id', field: 'appId', visible: false, align: 'center', valign: 'middle'},

        {title: '应用名', field: 'appName', visible: true, align: 'center', valign: 'middle'},

            {title: '应用版本', field: 'appVer', visible: true, align: 'center', valign: 'middle'},
            {title: '渠道号', field: 'channel', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Note.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Note.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加短信管理
 */
Note.openAddNote = function () {
    var index = layer.open({
        type: 2,
        title: '添加短信管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/note/note_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看短信管理详情
 */
Note.openNoteDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '短信管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/note/note_update/' + Note.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除短信管理
 */
Note.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/note/delete", function (data) {
            Feng.success("删除成功!");
            Note.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("noteId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询短信管理列表
 */
Note.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Note.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Note.initColumn();
    var table = new BSTable(Note.id, "/note/list", defaultColunms);
    table.setPaginationType("client");
    Note.table = table.init();
});
