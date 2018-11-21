/**
 * 应用内容版本管理初始化
 */
var AppContent = {
    id: "AppContentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AppContent.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '自动编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'app id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
            {title: '版本号', field: 'appVer', visible: true, align: 'center', valign: 'middle'},
            {title: '发行渠道', field: 'channelId', visible: true, align: 'center', valign: 'middle'},
            {title: '应用名', field: 'name', visible: true, align: 'center', valign: 'middle'},

        {title: '内容', visible: true, align: 'center', valign: 'middle',formatter:hrefFormatter },

            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 下拉菜单
 * @returns {string}
 */
function operateFormatter() {
    return [
        '<div class="btn-group">\n' +
        '    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">原始\n' +
        '        <span class="caret"></span>\n' +
        '    </button>\n' +
        '    <ul class="dropdown-menu" style="z-index:9999999;height:100px;overflow:scroll" role="menu">\n' +
        '        <li>\n' +
        '            <a href="#">功能</a>\n' +
        '        </li>\n' +
        '        <li>\n' +
        '            <a href="#">另一个功能</a>\n' +
        '        </li>\n' +
        '        <li>\n' +
        '            <a href="#">其他</a>\n' +
        '        </li>\n' +
        '        <li class="divider"></li>\n' +
        '        <li>\n' +
        '            <a href="#">分离的链接</a>\n' +
        '        </li>\n' +
        '    </ul>\n' +
        '</div>',]
        .join('');
};
/**
 * 下拉选择框
 * @param value
 * @param row
 * @param index
 * @returns {string}
 * selected='selected'
 */
function operateFormatter1(value, row, index) {
    return [
        '<select class=\'selectpicker show-tick form-control\'>' +
        '<option value=\'Item 1\' ><a href=\'#\' >图集内容</a></option>' +
        '<option value=\'Item 2\' ><a href=\'#\' >视频内容</a></option>' +
        '<option value=\'Item 2\' ><a href=\'\\modelAppSource\' >模特内容</a></option>' +
        '</select>',]
        .join('');
};

function hrefFormatter(value, row, index) {

    return [
        "<div id=\"tabs\">" +
        "<a onclick=\"viewPhotos('"+row.id+"')\">图集内容</a> &nbsp;" +
        "<a onclick=\"viewVideo('"+row.id+"')\">视频内容</a> <br /> " +
        "<a onclick=\"viewModel('"+row.id+"')\">模特内容</a>" +
        "</div>",]
        .join('');
};


function viewPhotos(rowId){
    this.href = "/photosAppSource?appContentId="+rowId;
    window.open(this.href,"_self");
};

function viewVideo(rowId){
    this.href = "/videoAppSource?appContentId="+rowId;
    window.open(this.href,"_self");
};

function viewModel(rowId){
    this.href = "/modelAppSource?appContentId="+rowId;
    window.open(this.href,"_self");
};

$(function () {
    $('#tabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    })
});


/**
 * 检查是否选中
 */
AppContent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AppContent.seItem = selected[0];
        return true;
    }
};

/**
 * 批量删除
 */

function deleteAppContentList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#AppContentTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteAppContents(ids);
};


/**
 * 点击添加应用内容版本
 */
AppContent.openAddAppContent = function () {
    var index = layer.open({
        type: 2,
        title: '添加应用内容版本',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/appContent/appContent_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看应用内容版本详情
 */
AppContent.openAppContentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '应用内容版本详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/appContent/appContent_update/' + AppContent.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除应用内容版本
 */
AppContent.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/appContent/delete", function (data) {
            Feng.success("删除成功!");
            AppContent.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("appContentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除应用内容版本List
 */
function deleteAppContents(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/appContent/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                AppContent.table.refresh();
            }, error:function (data) {
                alert(data.msg);
                AppContent.table.refresh();
            }
        });
    }
};



/**
 * 查询应用内容版本列表
 */
AppContent.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AppContent.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AppContent.initColumn();
    var table = new BSTable(AppContent.id, "/appContent/list", defaultColunms);
    table.setPaginationType("client");
    AppContent.table = table.init();
});

