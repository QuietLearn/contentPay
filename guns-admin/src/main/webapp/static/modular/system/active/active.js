/**
 * 激活统计管理初始化
 */
var Active = {
    id: "ActiveTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Active.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '自动埋点', field: 'id', visible: false , align: 'center', valign: 'middle'},

        {title: '应用id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
        {title: '应用', field: 'appName', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本', field: 'appVerName', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本备注', field: 'appVer', visible: false, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channel', visible: false, align: 'center', valign: 'middle'},
        {title: '渠道号备注', field: 'channelName', visible: false, align: 'center', valign: 'middle'},

            {title: '运营商', field: 'operator', visible: true, align: 'center', valign: 'middle'},
            {title: '省份地区', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: 'IMSI', field: 'imsi', visible: true, align: 'center', valign: 'middle'},
            {title: 'IMEI', field: 'imei', visible: true, align: 'center', valign: 'middle'},
            {title: '短信中心号码', field: 'smsc', visible: false, align: 'center', valign: 'middle'},
            {title: '是否删除 ', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '0  开启   1  激活  2  手动开启', field: 'activeType', visible: false, align: 'center', valign: 'middle'},
            {title: '手机型号', field: 'phoneType', visible: true, align: 'center', valign: 'middle'},
            {title: '手机品牌', field: 'phoneBrand', visible: true, align: 'center', valign: 'middle'},
            {title: '手机系统', field: 'phoneSystem', visible: true, align: 'center', valign: 'middle'},
            {title: '分辨率', field: 'dpi', visible: true, align: 'center', valign: 'middle'},
            {title: 'uuid', field: 'uuid', visible: false, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '手机识别码', field: 'iccid', visible: true, align: 'center', valign: 'middle'},
            // 1 安卓 2ios
            {title: '埋点类型', field: 'pointType', visible: true, align: 'center', valign: 'middle'},
            {title: '网络类型', field: 'wifiType', visible: true, align: 'center', valign: 'middle'},
            {title: '广告标识', field: 'idfa', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'openUdid', visible: false, align: 'center', valign: 'middle'},
            {title: '1 代表来自头条', field: 'isToutiao', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Active.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Active.seItem = selected[0];
        return true;
    }
};

//批量删除
function deleteActiveList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#ActiveTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteActives(ids);
};

/**
 * 点击添加激活统计
 */
Active.openAddActive = function () {
    var index = layer.open({
        type: 2,
        title: '添加激活统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/active/active_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看激活统计详情
 */
Active.openActiveDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '激活统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/active/active_update/' + Active.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除激活统计
 */
Active.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/active/delete", function (data) {
            Feng.success("删除成功!");
            Active.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("activeId",this.seItem.id);
        ajax.start();
    }
};

//删除
function deleteActives(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/active/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                Active.table.refresh();
                /* $("#user").bootstrapTable('refresh', {url: '/user/getUserList.do'});*/
            }, error:function (data) {
                alert(data.msg);
                Active.table.refresh();
            }
        });
    }
};


/**
 * 查询激活统计列表
 */
Active.search = function () {
    var queryData = {};
    //queryData['province'] = $("#province").val();
    queryData['appId'] = $("#appId").val();
    //queryData['mobile'] = $("#mobile").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    Active.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Active.initColumn();
    var table = new BSTable(Active.id, "/active/list", defaultColunms);
    table.setPaginationType("server");
    Active.table = table.init();
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