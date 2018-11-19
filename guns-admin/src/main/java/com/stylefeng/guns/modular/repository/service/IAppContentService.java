package com.stylefeng.guns.modular.repository.service;

import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.AppContent;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyj
 * @since 2018-11-19
 */
public interface IAppContentService extends IService<AppContent> {

    Result searchAppContent(String appId, String appVer, String channelId,String type);
}
