/**
 * 模特应用内容管理初始化
 */
var ModelAppSource = {
    id: "ModelAppSourceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ModelAppSource.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '应用内容id', field: 'appContentId', visible: true, align: 'center', valign: 'middle'},
            {title: '模特id', field: 'modelId', visible: true, align: 'center', valign: 'middle'},
            {title: '权限', field: 'authority', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ModelAppSource.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ModelAppSource.seItem = selected[0];
        return true;
    }
};

/**
 * 批量删除
 */

function deleteModelAppSourceList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#ModelAppSourceTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteModelAppSources(ids);
};


/**
 * 点击添加模特应用内容
 */
ModelAppSource.openAddModelAppSource = function () {
    var index = layer.open({
        type: 2,
        title: '添加模特应用内容',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/modelAppSource/modelAppSource_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看模特应用内容详情
 */
ModelAppSource.openModelAppSourceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '模特应用内容详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/modelAppSource/modelAppSource_update/' + ModelAppSource.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除模特应用内容
 */
ModelAppSource.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/modelAppSource/delete", function (data) {
            Feng.success("删除成功!");
            ModelAppSource.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("modelAppSourceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除模特应用内容List
 */
function deleteModelAppSources(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/modelAppSource/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                ModelAppSource.table.refresh();
            }, error:function (data) {
                alert(data.msg);
                ModelAppSource.table.refresh();
            }
        });
    }
};



/**
 * 查询模特应用内容列表
 */
ModelAppSource.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ModelAppSource.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ModelAppSource.initColumn();
    var table = new BSTable(ModelAppSource.id, "/modelAppSource/list", defaultColunms);
    table.setPaginationType("client");
    ModelAppSource.table = table.init();
});
