/**
 * 用户反馈管理初始化
 */
var Feedback = {
    id: "FeedbackTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Feedback.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '反馈信息', field: 'info', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'feedbackType', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除 0 是 1否', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'},
            {title: '应用id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
            {title: '应用版本', field: 'appVer', visible: true, align: 'center', valign: 'middle'},
            {title: '渠道号', field: 'channel', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Feedback.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Feedback.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户反馈
 */
Feedback.openAddFeedback = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户反馈',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/feedback/feedback_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户反馈详情
 */
Feedback.openFeedbackDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户反馈详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/feedback/feedback_update/' + Feedback.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户反馈
 */
Feedback.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/feedback/delete", function (data) {
            Feng.success("删除成功!");
            Feedback.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("feedbackId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户反馈列表
 */
Feedback.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Feedback.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Feedback.initColumn();
    var table = new BSTable(Feedback.id, "/feedback/list", defaultColunms);
    table.setPaginationType("client");
    Feedback.table = table.init();
});
