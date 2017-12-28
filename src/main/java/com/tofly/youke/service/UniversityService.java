package com.tofly.youke.service;

import com.tofly.youke.common.AbstractService;
import com.tofly.youke.common.IMapper;
import com.tofly.youke.common.domain.KeyValueModel;
import com.tofly.youke.common.exception.ServiceException;
import com.tofly.youke.domain.po.University;
import com.tofly.youke.persistence.UniversityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lyrics on 2017-11-17.
 */
@Service
public class UniversityService extends AbstractService<University> {

    @Autowired
    private UniversityMapper universityMapper;

    @Override
    protected IMapper getMapper() {
        return universityMapper;
    }

    public University findUniversity(String id) {
        return universityMapper.selectByPrimaryKey(id);
    }

    public List<KeyValueModel> getUniversityKeyValue(University university) throws ServiceException {
        return universityMapper.getUniversityKeyValue(university);
    }
}
