package com.tofly.youke.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;

/**
 * Created by cremin on 2017/8/4.
 */
@Configuration
public class ThymeleafLayoutDialectConfiguration {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect(new GroupingStrategy());
    }

}
