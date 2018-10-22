/**
 * 视频管理管理初始化
 */
var Data = {
    id: "DataTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Data.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'vId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'tid', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vState', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vPic', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vSpic', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vGpic', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vHit', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vRank', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vDigg', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vTread', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vCommend', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vWrong', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vIsmake', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vActor', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vColor', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vPublishyear', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vPublisharea', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vAddtime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vTopic', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vNote', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vTags', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vLetter', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vIsunion', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vRecycled', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vDirector', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vEnname', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vLang', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vScore', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vScorenum', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vExtratype', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vJq', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vNickname', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vReweek', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vDouban', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vMtime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vImdb', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vTvs', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vCompany', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vDayhit', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vWeekhit', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vMonthhit', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vDaytime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vWeektime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vMonthtime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否需要vip 0是 1否', field: 'vReqVip', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vLen', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vTotal', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vVer', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vPsd', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'vLongtxt', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Data.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Data.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加视频管理
 */
Data.openAddData = function () {
    var index = layer.open({
        type: 2,
        title: '添加视频管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/data/data_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看视频管理详情
 */
Data.openDataDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '视频管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/data/data_update/' + Data.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除视频管理
 */
Data.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/data/delete", function (data) {
            Feng.success("删除成功!");
            Data.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dataId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询视频管理列表
 */
Data.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Data.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Data.initColumn();
    var table = new BSTable(Data.id, "/data/list", defaultColunms);
    table.setPaginationType("server");
    Data.table = table.init();
});
