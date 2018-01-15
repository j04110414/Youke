package com.tofly.youke.common.utils;

import com.alibaba.media.MediaConfiguration;
import com.alibaba.media.Result;
import com.alibaba.media.upload.UploadClient;
import com.alibaba.media.upload.UploadPolicy;
import com.alibaba.media.upload.UploadRequest;
import com.alibaba.media.upload.UploadResponse;
import com.alibaba.media.upload.UploadTokenClient;
import com.alibaba.media.upload.impl.DefaultUploadClient;
import com.alibaba.media.upload.impl.DefaultUploadTokenClient;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import libs.codec.org.apache.commons.codec.digest.DigestUtils;

/**
 * @author lyrics
 * @date 2018-01-13
 */
public class ImgUtils {
    private static final Logger logger = LoggerFactory.getLogger(ImgUtils.class);

    private static String TOKEN;

    /**
     * 文件上传阿里顽兔-文件夹路径
     */
    private static String folderForUpload = "";

    private static final UploadClient client;

    static {
        MediaConfiguration configuration = new MediaConfiguration();
        configuration.setAk("24764758");
        configuration.setSk("c8d5545c91cb227ab495732616929c81");
        configuration.setNamespace("youke");
        UploadTokenClient tokenClient = new DefaultUploadTokenClient(configuration);

        UploadPolicy uploadPolicy = new UploadPolicy();
        uploadPolicy.setInsertOnly(UploadPolicy.INSERT_ONLY_NONE);
        //图片大小不超过3M
        uploadPolicy.setSizeLimit(3145728);
        uploadPolicy.setExpiration(System.currentTimeMillis() + 3600 * 1000);

        TOKEN = tokenClient.getUploadToken(uploadPolicy);

        client = new DefaultUploadClient();
    }

    /**
     * 上传阿里顽兔
     */
    private static Map<String, Object> uploadWantu(String path, String imgKey) {
        UploadRequest uploadRequest = new UploadRequest(TOKEN);
        uploadRequest.setFile(new File(path));
        uploadRequest.setDir(folderForUpload);
        uploadRequest.setName(imgKey);
        Result<UploadResponse> result = client.upload(uploadRequest);

        Map<String, Object> rs = new HashMap<>();
        if (result.isSuccess()) {
            rs.put("id", imgKey);
            rs.put("httpStatus", result.getHttpStatus());
            rs.put("code", result.getCode());
        } else {
            int status = result.getHttpStatus();
            if (status == 400) {
                String code = result.getCode();
                if ("LimitExceeded".equals(code)) {
                    rs.put("httpStatus", "400");
                    rs.put("code", "LimitExceeded");
                }
            }
        }

        return rs;
    }

    public static Map<String, Object> upload(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            try {
                FileInputStream e = new FileInputStream(filePath);
                String key = DigestUtils.md5Hex(IOUtils.toByteArray(e));
                IOUtils.closeQuietly(e);
                return uploadWantu(filePath, key);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {

            }
        }

        return null;
    }
}
