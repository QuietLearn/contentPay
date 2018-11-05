package com.stylefeng.guns.modular.system.service.impl;

import com.google.common.collect.Lists;
import com.stylefeng.guns.core.common.constant.state.AllConst;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.util.mobile.MobileInfoUtil;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.dao.BuriedPointMapper;
import com.stylefeng.guns.modular.system.service.IBuriedPointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 埋点 服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-29
 */
@Service
public class BuriedPointServiceImpl extends ServiceImpl<BuriedPointMapper, BuriedPoint> implements IBuriedPointService {


    //后端接口
    public long deletePointList(String ids) {
        String[] ss = ids.split(",");
        long count= 0;
        for (String s : ss) {
            this.deleteById(Integer.parseInt(s));
            count++;
        }
        return count;
    }


    //前端接口
    @Override
    public Result insertAssemBuriedPoint(BuriedPoint buriedPoint){
        BuriedPoint insertBuriedPoint = assemBuriedPoint(buriedPoint);
        boolean insert = this.insert(insertBuriedPoint);
        if (!insert ){
            return Result.createByErrorMessage("埋点失败");
        }

        return Result.createBySuccessMessage("埋点成功");
    }

    public BuriedPoint assemBuriedPoint(BuriedPoint buriedPoint){
        BuriedPoint insertBuriedPoint = new BuriedPoint();
        BeanUtils.copyProperties(buriedPoint,insertBuriedPoint);
        String mobileAreaInfo = MobileInfoUtil.getMobileAreaInfo(buriedPoint.getMobile());
        insertBuriedPoint.setAddress(mobileAreaInfo);
        insertBuriedPoint.setOperator(MobileInfoUtil.getMobileOperatorInfo(buriedPoint.getMobile()));

        insertBuriedPoint.setPointMessage(AllConst.PointMessageEnum.valueOf(buriedPoint.getPointId()));
        insertBuriedPoint.setGmtCreated(new Date());
        insertBuriedPoint.setGmtModified(new Date());
        return insertBuriedPoint;
    }


    public List<ZTreeNode> tree(){
        return this.baseMapper.tree();
    }

    /*public Map<Integer,String> listMessageOrCode() {

            List<Integer> pointIdList = Lists.newArrayList();
            for( AllConst.PointMessageEnum pointMessageEnum:AllConst.PointMessageEnum.values()) {
                Integer code = pointMessageEnum.getCode();
                pointIdList.add(code);
            }

            List<String> pointTypeList = Lists.newArrayList();
            for( AllConst.PointMessageEnum pointMessageEnum:AllConst.PointMessageEnum.values()) {
                String message = pointMessageEnum.getMessage();
                pointTypeList.add(message);
            }
            return pointTypeList;

    }*/


}
