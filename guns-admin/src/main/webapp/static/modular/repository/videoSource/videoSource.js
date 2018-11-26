/**
 * 视频来源管理初始化
 */
var VideoSource = {
    id: "VideoSourceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VideoSource.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '自动编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '来源网站', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VideoSource.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        VideoSource.seItem = selected[0];
        return true;
    }
};

/**
 * 批量删除
 */

function deleteVideoSourceList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#VideoSourceTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteVideoSources(ids);
};


/**
 * 点击添加视频来源
 */
VideoSource.openAddVideoSource = function () {
    var index = layer.open({
        type: 2,
        title: '添加视频来源',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/videoSource/videoSource_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看视频来源详情
 */
VideoSource.openVideoSourceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '视频来源详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/videoSource/videoSource_update/' + VideoSource.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除视频来源
 */
VideoSource.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/videoSource/delete", function (data) {
            Feng.success("删除成功!");
            VideoSource.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("videoSourceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除视频来源List
 */
function deleteVideoSources(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/videoSource/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                VideoSource.table.refresh();
            }, error:function (data) {
                alert(data.msg);
                VideoSource.table.refresh();
            }
        });
    }
};



/**
 * 查询视频来源列表
 */
VideoSource.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    VideoSource.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = VideoSource.initColumn();
    var table = new BSTable(VideoSource.id, "/videoSource/list", defaultColunms);
    table.setPaginationType("client");
    VideoSource.table = table.init();
});
