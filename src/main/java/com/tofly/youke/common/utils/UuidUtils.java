/**
 * File Name:UuidUtils.java
 * Copyright (c) 2014, doubibi All Rights Reserved.
 */
package com.tofly.youke.common.utils;

import java.util.UUID;

/**
 * Description: 生成UUID<br/>
 *
 * @author salman
 * @version 0.0.1
 * @date 2014年3月7日 下午7:22:03
 * @since JDK 1.6
 *
 * History:2014年3月7日 salman 初始创建
 */
public class UuidUtils {
	
	/**
	 * build:(生成不含-的UUID). <br/>
	 *
	 * @author salman
	 * @return String
	 */
	public static String build(){
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	} 
}
