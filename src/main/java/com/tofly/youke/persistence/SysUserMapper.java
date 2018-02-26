package com.tofly.youke.persistence;

import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.SysUser;

/**
 * @author lyrics
 */
public interface SysUserMapper extends IMapper<SysUser> {

    SysUser findSysUserBySelective(SysUser sysUser);
}