package com.tofly.youke.common;

import com.tofly.youke.common.domain.BaseDomain;
import com.tofly.youke.common.domain.KeyValueModel;

import java.util.List;
import java.util.Map;

/**
 * Desc: <br/>
 *
 * @author: Summer
 * @date: 2016/6/7 10:45
 * <p>
 * History:2016/6/7 Summer 初始创建
 */
public interface IMapper<T> {

    void insert(T record);

    /**
     * 批量插入
     * @param records
     * @return
     */
    void insertBatch(List<T> records);

    void insertSelective(T record);

    T selectByPrimaryKey(String id);

    void updateByPrimaryKeySelective(T record);

    void updateByPrimaryKey(T record);

    void deleteByPrimaryKey(String id);

    List<KeyValueModel> getKeyValues(Map<String, String> params);

    <R extends T> List<R> findByObjParams(T params);
    
    <R extends T> List<R> findByParams(Map<String, String> params);

    void deleteByForeignKey(String foreignKeyId);

    void filingByForeignKey(String foreignKeyId);

    /**
     * 根据外键查询
     * @param foreignKeyId
     * @return
     */
    <R extends T> List<R> selectByForeignKey(String foreignKeyId);

    /**
     * 获取所有数据
     * @param <T>
     * @return
     */
    <T extends BaseDomain> List<T> findAll();

    /**
     * 根据关键值获取数量
     * @param key
     * @return
     */
    int getCountByKey(String key);

    /**
     * 根据条件计数
     * @param params
     * @return
     */
    int countByParams(Map<String, String> params);
}
