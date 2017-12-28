package com.tofly.youke.common.domain;

import java.io.Serializable;

/**
 * Desc: <br/>
 *
 * @author: Summer
 * @version: 1.0
 * @date: 2016/11/8 17:40
 * @since: JDK 1.8
 * <p>
 * History:2016/11/8 Summer 初始创建
 */
public class KeyValueModel implements Serializable {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
