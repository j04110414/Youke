package com.tofly.youke.persistence;

import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.SysResource;

import java.util.List;

/**
 * @author lyrics
 */
public interface SysResourceMapper extends IMapper<SysResource> {

    List<SysResource> selectByUserId(String id);
}