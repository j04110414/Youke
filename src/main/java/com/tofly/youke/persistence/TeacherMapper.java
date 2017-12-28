package com.tofly.youke.persistence;

import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.Teacher;

public interface TeacherMapper extends IMapper {
    int deleteByPrimaryKey(Integer teacherId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}