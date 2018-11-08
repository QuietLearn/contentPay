/**
 * 会员管理管理初始化
 */
var Member = {
    id: "MemberTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    //checkbox : true,
    layerIndex: -1

};

/**
 * 初始化表格的列
 */
Member.initColumn = function () {
    return [

        {field: 'selectItem', checkbox: true},

        {title: '自动编号', field: 'id', visible: true, align: 'center', valign: 'middle',},

        {title: '应用id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
        {title: '应用版本', field: 'appVer', visible: false, align: 'center', valign: 'middle'},
        {title: '分发渠道', field: 'channel', visible: false, align: 'center', valign: 'middle'},

        {title: '用户名', field: 'username', visible: true, align: 'center', valign: 'middle',sortable: true},

        {title: '性别', field: 'genderName', visible: true, align: 'center', valign: 'middle'},
        {title: '手机', field: 'mobile', visible: true, align: 'center', valign: 'middle'},

        {title: '会员类型', field: 'memberTypeName', visible: true, align: 'center', valign: 'middle'},

        {title: '总消费额', field: 'totalMoney', visible: true, align: 'center', valign: 'middle'},
        {title: '用户状态', field: 'lockStatus', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
Member.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Member.seItem = selected[0];
        return true;
    }
};

//批量选择
function checkUserList() {
    //获取所有被选中的记录
    // + this.id
    var rows = $('#MemberTable').bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);

    var opbrief = $(this).parent("div").parent("div").find("textarea").val();


    $("#memberId").val(ids);

    $("#myModal").modal('hide');

    //return ids;
};

/**
 * 查询会员管理列表
 */
Member.search = function () {
    var queryData = {};
    queryData['username'] = $("#username").val();
    queryData['memberTypeId'] = $("#memberTypeId").val();
    queryData['appId'] = $("#appId").val();
    Member.table.refresh({query: queryData});
};


function selectUserList(){
    $
    memberId
}

$(function () {
    var defaultColunms = Member.initColumn();
    var table = new BSTable(Member.id, "/member/list", defaultColunms);
    table.setPaginationType("server");
    Member.table = table.init();
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

