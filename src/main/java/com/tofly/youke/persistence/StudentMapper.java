package com.tofly.youke.persistence;

import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.Student;

public interface StudentMapper extends IMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}