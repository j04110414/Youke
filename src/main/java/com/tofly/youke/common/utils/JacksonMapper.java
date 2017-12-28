/**
 * File Name:JacksonMapper.java
 * Copyright (c) 2015, doubibi All Rights Reserved.
 */
package com.tofly.youke.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Description:JacksonMapper初始化 <br/>
 *
 * @author salman
 * @version 0.0.1
 * @date 2015年8月19日 下午5:26:19
 * @since JDK 1.6
 *
 * History:2015年8月19日 salman 初始创建
 */
public class JacksonMapper {

	private JacksonMapper() {
	}

	/**
	 *
	 * @param longToString true:用来解决非Ajax请求，Js无法识别后台值为long型的问题
	 * @return
	 */
	public final static ObjectMapper getInstance(boolean... longToString) {
		ObjectMapper mapper = new ObjectMapper();

		if(longToString!=null&&longToString.length>0&&longToString[0]){
			SimpleModule simpleModule = new SimpleModule();
			simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
			simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
			mapper.registerModule(simpleModule);
		}

		return mapper;
	}
}
