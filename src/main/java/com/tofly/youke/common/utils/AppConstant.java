package com.tofly.youke.common.utils;

/**
 * 系统使用全局变量
 *
 * @author lyrics
 * @date 2018-01-09
 */
public enum AppConstant {

    //-----------------用户类型----------------
    APP_USER_TYPE_TEACHER("type", "1", "教师"),
    APP_USER_TYPE_STUDENT("type", "0", "学生"),

    //----------------用户状态------------------
    STATUS_ACTIVE("status", "0", "有效状态标示"),
    STATUS_IDLE("status", "-1", "无效状态标示"),
    STATUS_REMOVE("status", "-2", "软删除状态标示"),

    //---------------服务端--------------------
    BACK_CODE_SUCCESS("code", "6000", "处理数据成功"),
    BACK_CODE_FAIL("code", "7000", "处理数据失败"),
    BACK_CODE_ILLEGAL("code", "7001", "未经验证客户端"),
    BACK_CODE_CHECKERROR("code", "7002", "客户端与服务端验证失败--唯一码验证错误"),
    BACK_CODE_EXCEPTION("code", "7003", "未知错误"),
    BACK_CODE_ILLEGAL_PARAMS("code", "7004", "参数错误"),

    //----------------APP-------------------
    BACK_APPUSER_NAME_NOT_NULL("code", "8001", "用户名不能为空"),
    BACK_APPUSER_NOTEXIST("code", "8002", "该用户不存在"),
    BACK_APPUSER_PASSWORDERROR("code", "8003", "密码错误，请重新输入"),
    BACK_APPUSER_CHECKERROR("code", "8004", "用户验证失败"),
    BACK_APPUSER_VERIFYCODETIMEOUT("code", "8005", "验证码发送超时"),
    BACK_APPUSER_APPUSERNAMEERROR("code", "8006", "用户名错误"),
    BACK_APPUSER_APPUSEREXIST("code", "8007", "用户名已存在"),
    BACK_APPUSER_PSD_NOT_NULL("code", "8008", "密码不能为空"),
    BACK_APPUSER_LOCKED("code", "8009", "用户冻结"),
    BACK_APPUSER_CHECKEVERIFYRROR("code", "8010", "验证码错误"),
    BACK_STAFF_NOSTORE("code", "8011", "未关联bms门店账户"),
    BACK_SMS_SUCCESS("code", "8100", "验证码发送成功"),;


    /**
     * 自定义枚举构造器
     */
    public String name;
    public String value;
    public String desc;

    AppConstant(String name, String code, String desc) {
        this.name = name;
        this.value = code;
        this.desc = desc;
    }
}
