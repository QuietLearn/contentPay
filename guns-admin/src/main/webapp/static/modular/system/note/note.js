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
        {field: 'selectItem', checkbox: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '短信码', field: 'message', visible: true, align: 'center', valign: 'middle'},
            {title: '短信有效时间(分)', field: 'aging', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '发送手机', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '短信类型', field: 'type', visible: false, align: 'center', valign: 'middle'},
        {title: '短信用途', field: 'messageTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '发送时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtUpdated', visible: false, align: 'center', valign: 'middle'},
        {title: '应用id', field: 'appId', visible: true, align: 'center', valign: 'middle'},

        {title: '应用名', field: 'appName', visible: false, align: 'center', valign: 'middle'},

        {title: '应用版本ver', field: 'appVer', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本', field: 'appVerName', visible: false, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channel', visible: false, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channelName', visible: false, align: 'center', valign: 'middle'},
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

//批量删除
function deleteNoteList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#NoteTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteNotes(ids);
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

//删除
function deleteNotes(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/note/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                Note.table.refresh();
                /* $("#user").bootstrapTable('refresh', {url: '/user/getUserList.do'});*/
            }, error:function (data) {
                alert(data.msg);
                Note.table.refresh();
            }
        });
    }
};


/**
 * 查询短信管理列表
 */
Note.search = function () {
    var queryData = {};
    queryData['mobile'] = $("#mobile").val();
    queryData['appId'] = $("#appId").val();
    Note.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Note.initColumn();
    var table = new BSTable(Note.id, "/note/list", defaultColunms);
    table.setPaginationType("server");
    Note.table = table.init();
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