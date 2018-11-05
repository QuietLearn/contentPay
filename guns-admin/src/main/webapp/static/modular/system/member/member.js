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

        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},

        {title: '应用', field: 'appName', visible: true, align: 'center', valign: 'middle'},
        {title: '应用id', field: 'appId', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本ver', field: 'appVer', visible: false, align: 'center', valign: 'middle'},
        {title: '应用版本', field: 'appVerName', visible: true, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channel', visible: false, align: 'center', valign: 'middle'},
        {title: '渠道号', field: 'channelName', visible: true, align: 'center', valign: 'middle'},

        {title: '用户名', field: 'username', visible: true, align: 'center', valign: 'middle',sortable: true},

        {title: '昵称', field: 'nickname', visible: false, align: 'center', valign: 'middle'},
        {title: '明文密码', field: 'password', visible: false, align: 'center', valign: 'middle'},
        {title: 'md5加密密码', field: 'md5Password', visible: false, align: 'center', valign: 'middle'},

        {title: '性别', field: 'gender', visible: false, align: 'center', valign: 'middle'},
        {title: '性别', field: 'genderName', visible: true, align: 'center', valign: 'middle'},

        {title: '头像', field: 'picAddress', visible: true, align: 'center', valign: 'middle'},
        {title: '手机', field: 'mobile', visible: true, align: 'center', valign: 'middle'},

        {title: 'qq', field: 'qq', visible: false, align: 'center', valign: 'middle'},
        {title: '微信', field: 'weixin', visible: false, align: 'center', valign: 'middle'},

        {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},

         {title: '会员类型 表id', field: 'memberTypeId', visible: false, align: 'center', valign: 'middle'},

        {title: '会员类型名称', field: 'memberTypeName', visible: true, align: 'center', valign: 'middle'},

        {title: '会员等级', field: 'vipLevel', visible: false, align: 'center', valign: 'middle'},
         {title: '购买时间', field: 'buyTime', visible: false, align: 'center', valign: 'middle'},
         {title: '结束时间', field: 'endTime', visible: false, align: 'center', valign: 'middle'},
         {title: '有效时间', field: 'aging', visible: true, false: 'center', valign: 'middle'},

        {title: '积分', field: 'points', visible: true, align: 'center', valign: 'middle'},

        {title: '用户等级', field: 'userLevel', visible: false, align: 'center', valign: 'middle'},
        {title: '用户经验', field: 'experience', visible: false, align: 'center', valign: 'middle'},
        {title: '收藏夹ids ，分隔', field: 'favoritesIds', visible: false, align: 'center', valign: 'middle'},
        {title: '最后登录时间', field: 'lastLogin', visible: false, align: 'center', valign: 'middle'},
        {title: '最后登录ip', field: 'lastLoginip', visible: false, align: 'center', valign: 'middle'},

        {title: '注册时间', field: 'registerTime', visible: false, align: 'center', valign: 'middle'},
         {title: '注册ip', field: 'registerIp', visible: false, align: 'center', valign: 'middle'},
         {title: 'uuid唯一值', field: 'uuidToken', visible: false, align: 'center', valign: 'middle'},

        {title: '总消费额', field: 'totalMoney', visible: false, align: 'center', valign: 'middle'},
        {title: '是否锁定 0 是 １否', field: 'isLock', visible: false, align: 'center', valign: 'middle'},
        {title: '逻辑删除 0 是 １否', field: 'isDel', visible: false, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'gmtCreated', visible: false, align: 'center', valign: 'middle'},
        {title: '更改时间', field: 'gmtModified', visible: false, align: 'center', valign: 'middle'}
        ,{title: '', visible: true, icon:'fa-edit',align: 'center', valign: 'middle'}
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

//批量删除
function deleteUserList() {
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
    deleteUser(ids);
};


/**
 * 点击添加会员管理
 */
Member.openAddMember = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/member/member_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员管理详情
 */
Member.openMemberDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/member/member_update/' + Member.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员管理
 */
Member.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/member/delete", function (data) {
            Feng.success("删除成功!");
            Member.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("memberId",this.seItem.id);
        ajax.start();
    }
};


//删除
function deleteUser(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: Feng.ctxPath +"/member/delete_list",
            type: "post",
            data: {
                ids: ids
            },
            success: function (data) {
                alert(data.message);
                //重新加载记录
                //重新加载数据
                //Feng.success("删除成功!");
                Member.table.refresh();
                /* $("#user").bootstrapTable('refresh', {url: '/user/getUserList.do'});*/
            }, error:function (data) {
                alert(data.msg);
                Member.table.refresh();
            }
        });
    }
};


/**
 * 查询会员管理列表
 */
Member.search = function () {
    var queryData = {};
    queryData['username'] = $("#username").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['appId'] = $("#appId").val();
    Member.table.refresh({query: queryData});
};

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
