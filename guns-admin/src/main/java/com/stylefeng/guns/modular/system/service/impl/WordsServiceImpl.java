package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.Words;
import com.stylefeng.guns.modular.system.dao.WordsMapper;
import com.stylefeng.guns.modular.system.service.IWordsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangyang
 * @since 2018-10-14
 */
@Service
public class WordsServiceImpl extends ServiceImpl<WordsMapper, Words> implements IWordsService {

}
