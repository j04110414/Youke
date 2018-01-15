package com.tofly.youke.service;

import com.tofly.youke.common.AbstractService;
import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.Teacher;
import com.tofly.youke.persistence.TeacherMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lyrics
 * @date 2018-01-08
 */
@Service
public class TeacherService extends AbstractService<Teacher> {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    protected IMapper<Teacher> getMapper() {
        return teacherMapper;
    }
}
