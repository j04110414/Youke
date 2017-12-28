package com.tofly.youke.service.system;

import com.tofly.youke.common.AbstractService;
import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.SysRole;
import com.tofly.youke.persistence.SysRoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lyrics on 2017-12-21.
 */
@Service
public class RoleService extends AbstractService<SysRole> {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    protected IMapper<SysRole> getMapper() {
        return sysRoleMapper;
    }

    public List<SysRole> selectByUserId(String id) {
        return null;
    }
}
