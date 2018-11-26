/**
 * 播放源管理初始化
 */
var PlaySource = {
    id: "PlaySourceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PlaySource.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '视频id', field: 'appVideoid', visible: true, align: 'center', valign: 'middle'},
            {title: '视频来源id', field: 'videoSourceid', visible: false, align: 'center', valign: 'middle'},
        {title: '视频来源', field: 'videoSourceName', visible: true, align: 'center', valign: 'middle'},

            {title: '视频地址', field: 'videoAddress', visible: true, align: 'center', valign: 'middle'},
            {title: '是否选用 ', field: 'isCheck', visible: false, align: 'center', valign: 'middle'},
            {title: '是否选用 ', field: 'isCheckRemark', visible: true, align: 'center', valign: 'middle'},

            {title: '', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PlaySource.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PlaySource.seItem = selected[0];
        return true;
    }
};

/**
 * 批量删除
 */

function deletePlaySourceList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#PlaySourceTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deletePlaySources(ids);
};


/**
 * 点击添加播放源
 */
PlaySource.openAddPlaySource = function () {
    var index = layer.open({
        type: 2,
        title: '添加播放源',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/playSource/playSource_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看播放源详情
 */
PlaySource.openPlaySourceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '播放源详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/playSource/playSource_update/' + PlaySource.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除播放源
 */
PlaySource.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/playSource/delete", function (data) {
            Feng.success("删除成功!");
            PlaySource.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("playSourceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除播放源List
 */
function deletePlaySources(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/playSource/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                PlaySource.table.refresh();
            }, error:function (data) {
                alert(data.msg);
                PlaySource.table.refresh();
            }
        });
    }
};



/**
 * 查询播放源列表
 */
PlaySource.search = function () {
    var queryData = {};
    queryData['videoSourceId'] = $("#videoSourceId").val();
    PlaySource.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PlaySource.initColumn();
    var table = new BSTable(PlaySource.id, "/playSource/list", defaultColunms);
    table.setPaginationType("server");
    PlaySource.table = table.init();
});
