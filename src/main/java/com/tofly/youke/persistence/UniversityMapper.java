package com.tofly.youke.persistence;

import com.tofly.youke.common.IMapper;
import com.tofly.youke.common.domain.KeyValueModel;
import com.tofly.youke.domain.po.University;

import java.util.List;

public interface UniversityMapper extends IMapper<University> {

    List<KeyValueModel> getUniversityKeyValue(University university);
}