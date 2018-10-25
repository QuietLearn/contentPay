package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Data;
import com.stylefeng.guns.modular.system.model.Video;
import com.stylefeng.guns.modular.system.dao.VideoMapper;
import com.stylefeng.guns.modular.system.service.IDataService;
import com.stylefeng.guns.modular.system.service.IVideoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-24
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private IDataService dataService;

    @Autowired
    private VideoMapper videoMapper;

    public Result confirmVideoPrice(int vid){
        Video video = videoMapper.selectByVid(vid);
        if (video.getvMoney()==0){
            return Result.createBySuccessMessage("视频免费,欢迎观看");
        }
        return Result.createBySuccessMessage(MessageFormat.format("请付{0}金币观看",video.getvMoney()));
    }



    public String syncVideo(){
        /*this.selectObjs()*/
        Wrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("v_id");
        List<Integer> videoIdList = ( List<Integer>)(List)this.selectObjs(wrapper);
        List<Integer> dataIdList = dataService.selectDataIds();

        videoMapper.syncDeleteVideoIds(dataIdList);
       /* List<Integer> deleteIds = ListUtils.subtract(videoIdList, dataIdList);
        if (CollectionUtils.isNotEmpty(deleteIds)){
            this.deleteBatchIds(deleteIds);
        }*/

        List<Integer> insertIds = ListUtils.subtract(dataIdList, videoIdList);
        List<Data> insertDataList = null;
        if (CollectionUtils.isNotEmpty(insertIds)){
            insertDataList = dataService.selectBatchVideoIds(insertIds);
            List<Video> videoList = Lists.newArrayList();
            for (Data data:insertDataList) {
                Video video = new Video();
                video.setGmtCreated(new Date());
                video.setGmtModified(new Date());
                BeanUtils.copyProperties(data,video);
                videoList.add(video);
            }
            boolean isInsertBatch = this.insertBatch(videoList,500);
            if (isInsertBatch){
                return "同步视频数据成功";
            }
            return "同步视频数据失败";
        }

        return "同步视频数据成功";
    }


}
