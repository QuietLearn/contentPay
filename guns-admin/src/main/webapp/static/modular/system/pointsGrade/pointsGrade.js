/**
 * 积分价格管理初始化
 */
var PointsGrade = {
    id: "PointsGradeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PointsGrade.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '积分（购买分级60，100，210，540，1100，2250）', field: 'points', visible: true, align: 'center', valign: 'middle'},
            {title: '购买金额', field: 'money', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtCreated', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PointsGrade.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PointsGrade.seItem = selected[0];
        return true;
    }
};

/**
 * 批量删除
 */

function deletePointsGradeList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#PointsGradeTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deletePointsGrades(ids);
};


/**
 * 点击添加积分价格
 */
PointsGrade.openAddPointsGrade = function () {
    var index = layer.open({
        type: 2,
        title: '添加积分价格',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/pointsGrade/pointsGrade_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看积分价格详情
 */
PointsGrade.openPointsGradeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '积分价格详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/pointsGrade/pointsGrade_update/' + PointsGrade.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除积分价格
 */
PointsGrade.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/pointsGrade/delete", function (data) {
            Feng.success("删除成功!");
            PointsGrade.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("pointsGradeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除积分价格List
 */
function deletePointsGrades(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/pointsGrade/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                PointsGrade.table.refresh();
            }, error:function (data) {
                alert(data.msg);
                PointsGrade.table.refresh();
            }
        });
    }
};



/**
 * 查询积分价格列表
 */
PointsGrade.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PointsGrade.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PointsGrade.initColumn();
    var table = new BSTable(PointsGrade.id, "/pointsGrade/list", defaultColunms);
    table.setPaginationType("client");
    PointsGrade.table = table.init();
});
