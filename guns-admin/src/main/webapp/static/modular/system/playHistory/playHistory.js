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
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'memberUsername', visible: true, align: 'center', valign: 'middle'},
            {title: '播放视频id', field: 'videoId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'videoName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'videoNote', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'videoPic', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'videoActor', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '播放时间', field: 'playTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
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

/**
 * 查询用户播放历史列表
 */
PlayHistory.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PlayHistory.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PlayHistory.initColumn();
    var table = new BSTable(PlayHistory.id, "/playHistory/list", defaultColunms);
    table.setPaginationType("client");
    PlayHistory.table = table.init();
});