/**
 * 邮箱激活管理初始化
 */
var Email = {
    id: "EmailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Email.initColumn = function () {
    return [
        /*{field: 'selectItem'},*/
            {title: '自动编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: 'appId', field: 'appId', visible: true, align: 'center', valign: 'middle'},
            {title: '激活状态', field: 'active', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Email.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Email.seItem = selected[0];
        return true;
    }
};

/**
 * 批量删除
 */

function deleteEmailList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#EmailTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteEmails(ids);
};


/**
 * 点击添加邮箱激活
 */
Email.openAddEmail = function () {
    var index = layer.open({
        type: 2,
        title: '添加邮箱激活',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/email/email_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看邮箱激活详情
 */
Email.openEmailDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '邮箱激活详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/email/email_update/' + Email.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除邮箱激活
 */
Email.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/email/delete", function (data) {
            Feng.success("删除成功!");
            Email.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("emailId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除邮箱激活List
 */
function deleteEmails(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/email/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                Email.table.refresh();
            }, error:function (data) {
                alert(data.msg);
                Email.table.refresh();
            }
        });
    }
};



/**
 * 查询邮箱激活列表
 */
Email.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Email.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Email.initColumn();
    var table = new BSTable(Email.id, "/email/list", defaultColunms);
    table.setPaginationType("client");
    Email.table = table.init();
});
