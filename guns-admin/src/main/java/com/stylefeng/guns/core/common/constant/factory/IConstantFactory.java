package com.stylefeng.guns.core.common.constant.factory;

import com.stylefeng.guns.core.common.constant.cache.Cache;
import com.stylefeng.guns.core.common.constant.cache.CacheKey;
import com.stylefeng.guns.modular.system.model.Dict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 常量生产工厂的接口
 *
 * @author fengshuonan
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Integer userId);

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Integer userId);

    /**
     * 通过角色ids获取角色名称
     */
    @Cacheable(value = Cache.CONSTANT,key = "'"+ CacheKey.ROLES_NAME+"'+#roleIds")
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    @Cacheable(value = Cache.CONSTANT,key = "'"+ CacheKey.SINGLE_ROLE_NAME+"'+#roleId")
    String getSingleRoleName(Integer roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    @Cacheable(value = Cache.CONSTANT,key = "'"+ CacheKey.SINGLE_ROLE_TIP+"'+#roleId")
    String getSingleRoleTip(Integer roleId);

    /**
     * 获取部门名称
     */
    @Cacheable(value = Cache.CONSTANT,key = "'"+ CacheKey.DEPT_NAME+"'+#deptId")
    String getDeptName(Integer deptId);

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuIds);

    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);

    /**
     * 获取菜单名称通过编号
     */
    String getMenuNameByCode(String code);

    /**
     * 获取字典名称
     */
    String getDictName(Integer dictId);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Integer dictId);

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

    /**
     * 获取菜单状态
     */
    String getMenuStatusName(Integer status);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Integer id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

    /**
     * 获取子部门id
     */
    List<Integer> getSubDeptId(Integer deptid);

    /**
     * 获取所有父部门id
     */
    List<Integer> getParentDeptIds(Integer deptid);

    /**
     * 获取视频类型名
     * @param typeId
     * @return
     */
    String getTypeName(Integer typeId );

    /**
     * 获取app名
     * @param appId
     * @return
     */
    String getAppName(Integer appId);

    /**
     * 获取版本名
     * @param appVer
     * @return
     */
    String getAppVerName(String appVer);

    /**
     * 获取渠道号名
     * @param channel
     * @return
     */
    String getChannelName(String channel);

    /**
     * 获取 会员名
     * @param memberId
     * @return
     */

    String getMemberName(Integer memberId);

    /**
     * 获取反馈类型名
     * @param feedbackType
     * @return
     */
    String getFeedbackTypeName(String feedbackType);

    String getVideoName(String videoId);

    /**
     * 获取注册用户状态
     * @param status
     * @return
     */
    String getMemberStatusName(Integer status);


    /**
     * 获取推送消息是否正式 中文名
     * @param isOfficial
     * @return
     */
    String getNotifyOfficialRemark(Integer isOfficial);
}
