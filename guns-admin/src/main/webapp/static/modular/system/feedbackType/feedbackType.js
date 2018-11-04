/**
 * 反馈类型字典管理初始化
 */
var FeedbackType = {
    id: "FeedbackTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
FeedbackType.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '反馈码', field: 'typeCode', visible: true, align: 'center', valign: 'middle'},
            {title: '反馈类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
FeedbackType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        FeedbackType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加反馈类型字典
 */
FeedbackType.openAddFeedbackType = function () {
    var index = layer.open({
        type: 2,
        title: '添加反馈类型字典',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/feedbackType/feedbackType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看反馈类型字典详情
 */
FeedbackType.openFeedbackTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '反馈类型字典详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/feedbackType/feedbackType_update/' + FeedbackType.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除反馈类型字典
 */
FeedbackType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/feedbackType/delete", function (data) {
            Feng.success("删除成功!");
            FeedbackType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("feedbackTypeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询反馈类型字典列表
 */
FeedbackType.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    FeedbackType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = FeedbackType.initColumn();
    var table = new BSTable(FeedbackType.id, "/feedbackType/list", defaultColunms);
    table.setPaginationType("client");
    FeedbackType.table = table.init();
});

