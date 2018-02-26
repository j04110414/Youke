package com.tofly.youke.common.exception;


import com.tofly.youke.common.domain.RESPONSE_CODE;

/**
 * Desc: 报送用户提示<br/>
 *
 * @author: Summer
 * @date: 2016/7/5 15:55
 * <p>
 * History:2016/7/5 Summer 初始创建
 */
public class UserHintException extends Exception {

    private static final long serialVersionUID = 4921318664037644813L;

    /**
     * 错误代号
     */
    private String code;

    public UserHintException() {
        super();
        this.code = RESPONSE_CODE.BACK_CODE_FAIL.value;
    }

    /**
     * 默认代号的用户提示异常
     * @param message
     */
    public UserHintException(String message) {
        this(RESPONSE_CODE.BACK_CODE_FAIL, message);
    }

    /**
     * 带编号的用户提示
     * @param hintCode
     */
    public UserHintException(RESPONSE_CODE hintCode) {
        super(hintCode.desc);
        this.code = hintCode.value;
    }

    /**
     * 带编号的用户提示，可自定义异常信息
     * @param hintCode
     * @param message
     */
    public UserHintException(RESPONSE_CODE hintCode, String message) {
        super(message);
        this.code = hintCode.value;
    }

    public UserHintException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserHintException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }
}
