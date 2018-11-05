/**
 * 用户播放历史管理初始化
 */
var PlayHistory = {
    id: "PlayHistoryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PlayHistory.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '封面图片', field: 'videoPic', visible: true, align: 'center', valign: 'middle'},

        {title: '视频类型id', field: 'typeId', visible: false, align: 'center', valign: 'middle'},
        {title: '视频类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '视频id', field: 'videoId', visible: false, align: 'center', valign: 'middle'},
        {title: '视频名', field: 'videoName', visible: true, align: 'center', valign: 'middle'},
        {title: '说明(BD/高清/更新到6/共8)', field: 'videoNote', visible: false, align: 'center', valign: 'middle'},

        {title: '演员', field: 'videoActor', visible: false, align: 'center', valign: 'middle'},
        {title: '导演', field: 'videoDirector', visible: false, align: 'center', valign: 'middle'},
        {title: '发行地区', field: 'videoPublisharea', visible: false, align: 'center', valign: 'middle'},
        {title: '发行年份', field: 'videoPublishyear', visible: false, align: 'center', valign: 'middle'},
        {title: '用户id', field: 'memberId', visible: false, align: 'center', valign: 'middle'},
        {title: '用户', field: 'memberUsername', visible: true, align: 'center', valign: 'middle'},

            {title: '排序', field: 'sort', visible: false, align: 'center', valign: 'middle'},
        {title: '逻辑删除', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
        {title: '收藏时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'},
        {title: '应用id', field: 'appId', visible: false, align: 'center', valign: 'middle'},

        {title: '应用名', field: 'appName', visible: true, align: 'center', valign: 'middle'},

        {title: '应用版本ver', field: 'appVer', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本', field: 'appVerName', visible: true, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channel', visible: false, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channelName', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
PlayHistory.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PlayHistory.seItem = selected[0];
        return true;
    }
};


//批量删除
function deletePlayhistoryList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#PlayHistoryTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deletePlayhistory(ids);
};


/**
 * 点击添加用户播放历史
 */
PlayHistory.openAddPlayHistory = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户播放历史',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/playHistory/playHistory_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户播放历史详情
 */
PlayHistory.openPlayHistoryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户播放历史详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/playHistory/playHistory_update/' + PlayHistory.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户播放历史
 */
PlayHistory.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/playHistory/delete", function (data) {
            Feng.success("删除成功!");
            PlayHistory.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("playHistoryId",this.seItem.id);
        ajax.start();
    }
};

//删除
function deletePlayhistory(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/playHistory/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                PlayHistory.table.refresh();
                /* $("#user").bootstrapTable('refresh', {url: '/user/getUserList.do'});*/
            }, error:function (data) {
                alert(data.msg);
                PlayHistory.table.refresh();
            }
        });
    }
};


/**
 * 查询用户播放历史列表
 */
PlayHistory.search = function () {
    var queryData = {};
    queryData['videoName'] = $("#videoName").val();
    queryData['username'] = $("#username").val();
    queryData['appId'] = $("#appId").val();
    PlayHistory.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PlayHistory.initColumn();
    var table = new BSTable(PlayHistory.id, "/playHistory/list", defaultColunms);
    table.setPaginationType("server");
    PlayHistory.table = table.init();
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