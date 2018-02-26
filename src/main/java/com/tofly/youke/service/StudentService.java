package com.tofly.youke.service;

import com.tofly.youke.common.AbstractService;
import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.Student;
import com.tofly.youke.persistence.StudentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lyrics
 * @date 2017-12-22
 */
@Service
public class StudentService extends AbstractService<Student> {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    protected IMapper<Student> getMapper() {
        return studentMapper;
    }


    public void updateStuDelFlag(String id) {
        studentMapper.updateStuDelFlag(id);
    }
}
