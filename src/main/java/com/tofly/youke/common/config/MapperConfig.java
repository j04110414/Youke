package com.tofly.youke.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author lyrics
 * @date 2017-11-17
 */
@Configuration
@MapperScan("com.tofly.youke.persistence")
public class MapperConfig {
}
