package com.tofly.youke.common.domain;

/**
 * Desc: 通用相应枚举<br/>
 *
 * @author: Summer
 * @date: 2016/6/16 16:51
 * <p>
 * History:2016/6/16 Summer 初始创建
 */
public enum RESPONSE_CODE {
    BACK_CODE_SUCCESS("code", "6000", ERROR_TYPE.NOT_ERROR, "处理完成"),
    BACK_CODE_FAIL("code", "7000", ERROR_TYPE.USER_HINT, "服务器数据处理失败"),
    BACK_CODE_EXCEPTION("code", "7003", ERROR_TYPE.USER_NOT_HINT, "服务器逻辑错误"),
    BACK_CODE_ILLEGAL_PARAMS("code", "7004", ERROR_TYPE.USER_HINT, "参数错误"),
    BACK_CODE_ELEMENT_MISS("code", "7005", ERROR_TYPE.USER_HINT, "参素缺失"),
    BACK_CODE_USER_DISABLED("code", "9001", ERROR_TYPE.USER_HINT, "用户已禁用"),
    BACK_CODE_USER_NOT_EXISTS("code", "9002", ERROR_TYPE.USER_HINT, "用户不存在"),

    BACK_CODE_ILLEGAL_REQUEST("code", "8001", ERROR_TYPE.USER_HINT, "请求不合规"),
    BACK_CODE_SECRET_KEY_NOT_EFFECT("code", "8002", ERROR_TYPE.USER_HINT, "请求令牌无效"),
    BACK_CODE_REFRESH_KEY_NOT_EFFECT("code", "8003", ERROR_TYPE.USER_HINT, "刷新令牌已过期"),
    BACK_CODE_REFRESH_KEY_INCORRECT("code", "8004", ERROR_TYPE.USER_HINT, "刷新令牌错误，非法请求"),
    BACK_CODE_RELOGIN("code", "8005", ERROR_TYPE.USER_HINT, "重新登录");

    public String name;
    public String value;
    public ERROR_TYPE type;
    public String desc;

    RESPONSE_CODE(String name, String value, ERROR_TYPE type, String desc) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.desc = desc;
    }

    /**
     * 根据响应码获取相应枚举
     * @param code
     * @return
     */
    public static RESPONSE_CODE getResponseEnumByCode(String code) {
        RESPONSE_CODE result = null;
        for (RESPONSE_CODE item : RESPONSE_CODE.values()) {
            if (item.value.equals(code)) {
                result = item;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException("未识别的相应类型[" + code + "]");
        }
        return result;
    }
}
