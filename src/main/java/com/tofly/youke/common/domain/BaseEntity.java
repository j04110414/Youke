package com.tofly.youke.common.domain;

import java.io.Serializable;

/**
 * Desc: 基础实体<br/>
 *
 * @Author: Summer
 * @date: 2016/8/17 15:16
 * <p>
 * History:2016/8/17 Summer 初始创建
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1284600122181533866L;
    // 主键
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
