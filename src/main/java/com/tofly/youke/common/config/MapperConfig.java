package com.tofly.youke.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lyrics on 2017-11-17.
 */
@Configuration
@MapperScan("com.tofly.youke.persistence")
public class MapperConfig {
}
