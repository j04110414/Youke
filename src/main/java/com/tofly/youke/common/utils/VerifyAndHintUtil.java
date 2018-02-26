package com.tofly.youke.common.utils;


import com.tofly.youke.common.domain.BackResult;
import com.tofly.youke.common.domain.ERROR_TYPE;
import com.tofly.youke.common.domain.RESPONSE_CODE;
import com.tofly.youke.common.exception.UserHintException;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Desc: Controller层工具<br/>
 *
 * @Author: Summer
 * @date: 2016/6/25 9:54 <p> History:2016/6/25 Summer 初始创建
 */
public class VerifyAndHintUtil {
    /**
     * 校验返回异常与否
     */
    public static <T> T getResult(BackResult<T> result) throws Exception {
        return getResult(result, "");
    }

    /**
     * 校验返回异常与否
     */
    public static <T> T getResult(BackResult<T> result, String errorInfo) throws Exception {
        RESPONSE_CODE responseCode = RESPONSE_CODE.getResponseEnumByCode(result.getCode());
        if (ERROR_TYPE.NOT_ERROR.compareTo(responseCode.type) == 0) {
            return result.getData();
        } else if (ERROR_TYPE.USER_HINT.compareTo(responseCode.type) == 0) {
            // 可以不向客户掩盖异常
            throw new UserHintException(responseCode, result.getExceptions());
        } else {
            if (StringUtils.isEmpty(errorInfo)) {
                errorInfo = result.getExceptions();
            }
            throw new Exception(errorInfo);
        }
    }

    /**
     * 表达式校验未通过提示, 仅提供响应代号
     */
    public static void inspectFalseHint(boolean expression, RESPONSE_CODE code) throws UserHintException {
        inspectFalseHint(expression, code, code.desc);
    }

    /**
     * 表达式校验未通过提示, 仅提供响应提示
     */
    public static void inspectFalseHint(boolean expression, String hint) throws UserHintException {
        inspectFalseHint(expression, RESPONSE_CODE.BACK_CODE_FAIL, hint);
    }

    /**
     * 表达式校验未通过提示
     */
    public static void inspectFalseHint(boolean expression, RESPONSE_CODE code, String hint) throws UserHintException {
        if (!expression) {
            throw new UserHintException(code, hint);
        }
    }

    /**
     * 表达式校验通过提示, 自定义提示
     */
    public static void inspectTrueHint(boolean expression, String hint) throws UserHintException {
        inspectTrueHint(expression, RESPONSE_CODE.BACK_CODE_FAIL, hint);
    }

    /**
     * 表达式校验通过提示, 自定义响应编码
     */
    public static void inspectTrueHint(boolean expression, RESPONSE_CODE code) throws UserHintException {
        inspectTrueHint(expression, RESPONSE_CODE.BACK_CODE_FAIL, code.desc);
    }

    /**
     * 表达式校验通过提示
     */
    public static void inspectTrueHint(boolean expression, RESPONSE_CODE code, String hint) throws UserHintException {
        if (expression) {
            throw new UserHintException(code, hint);
        }
    }

    /**
     * 参数空值验证, 返回7005异常
     */
    public static void checkParamsNull(Object obj, String errorInfo) throws Exception {
        checkNull(obj, errorInfo, RESPONSE_CODE.BACK_CODE_ELEMENT_MISS);
    }

    /**
     * 普通空值验证, 返回7000异常, 默认提示
     */
    public static void checkNull(Object obj) throws Exception {
        checkNull(obj, "");
    }

    /**
     * 普通空值验证, 返回7000异常
     */
    public static void checkNull(Object obj, String errorInfo) throws Exception {
        checkNull(obj, errorInfo, RESPONSE_CODE.BACK_CODE_FAIL);
    }

    /**
     * 空值验证
     */
    public static void checkNull(Object obj, RESPONSE_CODE code) throws Exception {
        checkNull(obj, code.desc, code);
    }

    /**
     * 空值验证
     */
    public static void checkNull(Object obj, String errorInfo, RESPONSE_CODE code) throws Exception {
        boolean isNullStr = StringUtils.isEmpty(obj);
        boolean isNullCol = (obj instanceof Collection && CollectionUtils.isEmpty((Collection<?>) obj));
        boolean isNullMap = (obj instanceof Map && CollectionUtils.isEmpty((Map) obj));
        if (isNullStr || isNullCol || isNullMap) {
            throw new UserHintException(code, StringUtils.isEmpty(errorInfo) ? "空值错误" : errorInfo);
        }
    }
}
