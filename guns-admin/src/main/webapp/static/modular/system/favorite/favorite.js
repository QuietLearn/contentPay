/**
 * 用户收藏夹管理管理初始化
 */
var Favorite = {
    id: "FavoriteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Favorite.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '视频类型id', field: 'typeId', visible: false, align: 'center', valign: 'middle'},
            {title: '视频类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
            {title: '视频id', field: 'videoId', visible: true, align: 'center', valign: 'middle'},
            {title: '视频名', field: 'videoName', visible: true, align: 'center', valign: 'middle'},
            {title: '说明(BD/高清/更新到6/共8)', field: 'videoNote', visible: false, align: 'center', valign: 'middle'},
            {title: '封面图片', field: 'videoPic', visible: true, align: 'center', valign: 'middle'},
            {title: '演员', field: 'videoActor', visible: false, align: 'center', valign: 'middle'},
            {title: '导演', field: 'videoDirector', visible: false, align: 'center', valign: 'middle'},
            {title: '发行地区', field: 'videoPublisharea', visible: true, align: 'center', valign: 'middle'},
            {title: '发行年份', field: 'videoPublishyear', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberId', visible: false, align: 'center', valign: 'middle'},
            {title: '用户昵称', field: 'memberUsername', visible: true, align: 'center', valign: 'middle'},
            {title: '收藏排序', field: 'sort', visible: false, align: 'center', valign: 'middle'},
            {title: '逻辑删除', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
            {title: '收藏时间', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'},
            {title: '应用id', field: 'appId', visible: false, align: 'center', valign: 'middle'},

         {title: '应用名', field: 'appName', visible: true, align: 'center', valign: 'middle'},
            {title: '应用版本', field: 'appVer', visible: true, align: 'center', valign: 'middle'},
            {title: '渠道号', field: 'channel', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Favorite.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Favorite.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户收藏夹管理
 */
Favorite.openAddFavorite = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户收藏夹管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/favorite/favorite_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户收藏夹管理详情
 */
Favorite.openFavoriteDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户收藏夹管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/favorite/favorite_update/' + Favorite.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户收藏夹管理
 */
Favorite.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/favorite/delete", function (data) {
            Feng.success("删除成功!");
            Favorite.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("favoriteId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户收藏夹管理列表
 */
Favorite.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Favorite.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Favorite.initColumn();
    var table = new BSTable(Favorite.id, "/favorite/list", defaultColunms);
    table.setPaginationType("client");
    Favorite.table = table.init();
});
