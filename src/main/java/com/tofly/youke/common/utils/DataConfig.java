package com.tofly.youke.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 获取资源数据
 *
 * @author lyrics
 * @date 2018-01-11
 */
public class DataConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataConfig.class);

    private static String RESOURCES_PATH = "resources.properties";
    private static Properties properties = new Properties();

    static {
        load();
    }

    private DataConfig() {
    }

    private static void load() {
        String resourcepath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        String resourcepath = DataConfig.class.getClassLoader().getResource("").getPath();
        try {
            properties.load(new FileInputStream(resourcepath + RESOURCES_PATH));
        } catch (IOException e) {
            logger.error("加载resources.properties数据失败");
        }
    }

    public static String get(String key) {
        return (String) properties.get(key);
    }

}
