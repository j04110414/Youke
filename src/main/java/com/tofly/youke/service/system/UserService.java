package com.tofly.youke.service.system;

import com.tofly.youke.common.AbstractService;
import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.SysUser;
import com.tofly.youke.persistence.SysUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lyrics
 * @date 2017-12-21
 */
@Service
public class UserService extends AbstractService<SysUser> {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    protected IMapper<SysUser> getMapper() {
        return sysUserMapper;
    }

    public SysUser findSysUserBySelective(SysUser sysUser) {
        return sysUserMapper.findSysUserBySelective(sysUser);
    }

}
