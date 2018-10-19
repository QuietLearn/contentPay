package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.system.model.Note;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-10-19
 */
public interface NoteMapper extends BaseMapper<Note> {

    int selectSendMobileNoteNum(String mobile);
}
