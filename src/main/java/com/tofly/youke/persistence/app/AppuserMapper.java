package com.tofly.youke.persistence.app;

import com.tofly.youke.common.IMapper;
import com.tofly.youke.domain.po.Appuser;

import java.util.List;

/**
 * @author lyrics
 */
public interface AppuserMapper extends IMapper {

    Appuser findAppuser(Appuser appuser);
}