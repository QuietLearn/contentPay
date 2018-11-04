/**
 * 初始化应用下载渠道详情对话框
 */
var ChannelInfoDlg = {
    channelInfoData : {}
};

/**
 * 清除数据
 */
ChannelInfoDlg.clearData = function() {
    this.channelInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChannelInfoDlg.set = function(key, val) {
    this.channelInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChannelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ChannelInfoDlg.close = function() {
    parent.layer.close(window.parent.Channel.layerIndex);
}

/**
 * 收集数据
 */
ChannelInfoDlg.collectData = function() {
    this
    .set('id')
    .set('channel')
    .set('name')
    .set('isDel')
    .set('gmtCreated')
    .set('gmtModified');
}

/**
 * 提交添加
 */
ChannelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/channel/add", function(data){
        Feng.success("添加成功!");
        window.parent.Channel.table.refresh();
        ChannelInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.channelInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ChannelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/channel/update", function(data){
        Feng.success("修改成功!");
        window.parent.Channel.table.refresh();
        ChannelInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.channelInfoData);
    ajax.start();
}

$(function() {

});
