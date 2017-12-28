package com.tofly.youke.common;


import com.tofly.youke.common.domain.BaseDomain;
import com.tofly.youke.common.domain.KeyValueModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Desc: Service层抽象类<br/>
 *
 * @author: Summer
 * @date: 2016/6/8 13:41 <p> History:2016/6/8 Summer 初始创建
 */
public abstract class AbstractService<T extends BaseDomain> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 最大插入数量
     */
    private static final int MAX_INSERT_LEN = 1000;

    /**
     * 实现方定义对应Mapper
     */
    protected abstract IMapper<T> getMapper();

    public void insert(T record) {
        if (StringUtils.isEmpty(record.getCreateTime())) {
            record.setCreateTime(LocalDateTime.now().toString());
        }
        if (StringUtils.isEmpty(record.getDelFlag())) {
            record.setDelFlag("0");
        }
        getMapper().insert(record);
    }

    /**
     * 批量插入
     */
    public void insertBatch(List<T> records) {
        List<T> unit;
        int size = records.size();
        int k;
        // 有限制批量
        for (int i = 0; i < size; i++) {
            k = i;
            i += MAX_INSERT_LEN;
            i = (i > size) ? size : i;
            unit = records.subList(k, i);

            getMapper().insertBatch(unit);
        }
    }

    public void insertSelective(T record) {
        if (StringUtils.isEmpty(record.getCreateTime())) {
            record.setCreateTime(LocalDateTime.now().toString());
        }
        if (StringUtils.isEmpty(record.getDelFlag())) {
            record.setDelFlag("0");
        }
        getMapper().insertSelective(record);
    }

    public T selectByPrimaryKey(String id) {
        return getMapper().selectByPrimaryKey(id);
    }

    public void updateByPrimaryKeySelective(T record) {
        getMapper().updateByPrimaryKeySelective(record);
    }

    public void updateByPrimaryKey(T record) {
        if (StringUtils.isEmpty(record.getDelFlag())) {
            record.setDelFlag("0");
        }
        getMapper().updateByPrimaryKey(record);
    }

    public void deleteByPrimaryKey(String id) {
        getMapper().deleteByPrimaryKey(id);
    }

    public List<KeyValueModel> getKeyValues(Map<String, String> params) {
        return getMapper().getKeyValues(params);
    }

    public <R extends T> List<R> findByObjParams(T params) {
        return getMapper().findByObjParams(params);
    }

    public <R extends T> List<R> findByParams(Map<String, String> params) {
        return getMapper().findByParams(params);
    }

    /**
     * 根据外键删除
     */
    public void deleteByForeignKey(String foreignKeyId) {
        getMapper().deleteByForeignKey(foreignKeyId);
    }

    /**
     * 根据外键归档
     */
    public void filingByForeignKey(String foreignKeyId) {
        getMapper().filingByForeignKey(foreignKeyId);

    }

    /**
     * 根据外键查询
     */
    public <R extends T> List<R> selectByForeignKey(String foreignKeyId) {
        return getMapper().selectByForeignKey(foreignKeyId);
    }

    /**
     * 获取所有数据
     */
    public <R extends T> List<R> findAll() {
        return getMapper().findAll();
    }

    /**
     * 根据关键值获取数量
     */
    public int getCountByKey(String key) {
        return getMapper().getCountByKey(key);
    }

    /**
     * 根据条件计数
     */
    public int countByParams(Map<String, String> params) {
        return getMapper().countByParams(params);
    }

}
