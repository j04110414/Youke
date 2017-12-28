package com.tofly.youke.common.domain;



import com.tofly.youke.common.utils.DateUtils;
import com.tofly.youke.common.utils.UuidUtils;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: Domain基础类<br/>
 *
 * @author Summer
 * @date 2014年3月7日 下午3:43:14
 */
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 1109692436613378495L;

    public static final Map<String, String> DEL_FLAG_MAP = new HashMap<>();

    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETED = "1";

    static {
        DEL_FLAG_MAP.put(DEL_FLAG_NORMAL, "正常");
        DEL_FLAG_MAP.put(DEL_FLAG_DELETED, "已删除");
    }

    /**
     * 主键
     */
    protected String id;

    /**
     * 创建人
     */
    protected String creator;
    /**
     * 创建人姓名
     */
    protected String creatorName;
    /**
     * 操作者
     */
    protected String updater;
    /**
     * 操作者姓名
     */
    protected String updaterName;
    /**
     * 保存时间
     */
    protected String createTime;
    /**
     * 更新时间
     */
    protected String updateTime;
    /**
     * 状态
     */
    protected String status;


    /** 删除标识 */
    private String delFlag;

    /**
     * 生成id及创建时间
     */
    public void generateId() {
        setId(UuidUtils.build());
        setCreateTime(LocalDateTime.now().toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        if (StringUtils.hasLength(createTime)) {
            try {
                createTime = DateUtils.getDateTimeAny(DateUtils.parseDate(createTime), DateUtils.DATE_TIME_PATTERN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        if (StringUtils.hasLength(updateTime)) {
            try {
                updateTime = DateUtils.getDateTimeAny(DateUtils.parseDate(createTime), DateUtils.DATE_TIME_PATTERN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return updateTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
