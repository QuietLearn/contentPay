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
        {field: 'selectItem', checkbox: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '反馈信息', field: 'info', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'memberName', visible: false, align: 'center', valign: 'middle'},
            {title: '反馈类型', field: 'feedbackType', visible: false, align: 'center', valign: 'middle'},
        {title: '反馈类型', field: 'feedbackTypeName', visible: true, align: 'center',  valign: 'middle'},

            {title: '是否删除 ', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '反馈时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'},

        {title: '应用id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
        {title: '应用', field: 'appName', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本', field: 'appVer', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本备注', field: 'appVerName', visible: false, align: 'center', valign: 'middle'},
        {title: '分发渠道', field: 'channel', visible: false, align: 'center', valign: 'middle'},
        {title: '分发渠道备注', field: 'channelName', visible: false, align: 'center', valign: 'middle'},
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


//批量删除
function deleteFeedbackList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#FeedbackTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteFeedbacks(ids);
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

//删除
function deleteFeedbacks(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/feedback/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                Feedback.table.refresh();
                /* $("#user").bootstrapTable('refresh', {url: '/user/getUserList.do'});*/
            }, error:function (data) {
                alert(data.msg);
                Feedback.table.refresh();
            }
        });
    }
};


/**
 * 查询用户反馈列表
 */
Feedback.search = function () {
    var queryData = {};
    queryData['appId'] = $("#appId  ").val();
    queryData['username'] = $("#username").val();
    queryData['membertypeCode'] = $("#membertypeCode").val();
    Feedback.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Feedback.initColumn();
    var table = new BSTable(Feedback.id, "/feedback/list", defaultColunms);
    table.setPaginationType("server");
    Feedback.table = table.init();
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