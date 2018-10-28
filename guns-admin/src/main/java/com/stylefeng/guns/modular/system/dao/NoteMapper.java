package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Note;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hyj
 * @since 2018-10-28
 */
public interface NoteMapper extends BaseMapper<Note> {
    int selectSendMobileNoteNum(String mobile);
}
