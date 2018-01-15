package com.tofly.youke.persistence;

import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.Student;

public interface StudentMapper extends IMapper {

    void updateStuDelFlag(String id);
}