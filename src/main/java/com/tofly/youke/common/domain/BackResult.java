package com.tofly.youke.common.domain;

import java.io.Serializable;


/**
 * Description:返回结果集 <br/>
 *
 * @author salman
 * @version 0.0.1
 * @date 2014年3月8日 下午8:21:57
 * @since JDK 1.6
 *
 * History:2014年3月8日 salman 初始创建
 */
public class BackResult<T> implements Serializable{
    private static final long serialVersionUID = 2943322785092934571L;
    
    //返回代码
    private String code;
	//异常信息
    private String exceptions;
    //返回数据
	private T data;
	
	public String getCode() {
		return code;
	}
	public T getData() {
		return data;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setData(T data) {
		this.data = data;
	}
    public String getExceptions() {
        return exceptions;
    }
    public void setExceptions(String exceptions) {
        this.exceptions = exceptions;
    }
	
}