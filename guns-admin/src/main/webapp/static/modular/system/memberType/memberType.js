/**
 * 会员购买类型管理初始化
 */
var MemberType = {
    id: "MemberTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MemberType.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: ' 1001 普通用户 1001 包月  1002 包年', field: 'serverCode', visible: true, align: 'center', valign: 'middle'},
            {title: '服务名称', field: 'serverName', visible: true, align: 'center', valign: 'middle'},
            {title: '购买单价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '有效持续时间', field: 'aging', visible: true, align: 'center', valign: 'middle'},
            {title: '逻辑删除', field: 'isDel', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MemberType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MemberType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员购买类型
 */
MemberType.openAddMemberType = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员购买类型',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/memberType/memberType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员购买类型详情
 */
MemberType.openMemberTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员购买类型详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/memberType/memberType_update/' + MemberType.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员购买类型
 */
MemberType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/memberType/delete", function (data) {
            Feng.success("删除成功!");
            MemberType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("memberTypeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员购买类型列表
 */
MemberType.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MemberType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MemberType.initColumn();
    var table = new BSTable(MemberType.id, "/memberType/list", defaultColunms);
    table.setPaginationType("client");
    MemberType.table = table.init();
});
