package com.tofly.youke.service.system;

import com.tofly.youke.common.AbstractService;
import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.SysResource;
import com.tofly.youke.persistence.SysResourceMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author lyrics
 * @date 2017-12-21
 */
@Service
public class ResourceService extends AbstractService<SysResource> {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    protected IMapper<SysResource> getMapper() {
        return sysResourceMapper;
    }

    public List<SysResource> selectByUserId(String id) {
        return sysResourceMapper.selectByUserId(id);
    }
}
