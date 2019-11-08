package cn.nj.zj.springbooyoss.Common;

import cn.nj.zj.springbooyoss.Dao.AliOssConfig;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * /**
 * Copyright 2008-2018 OPPO Mobile Comm Corp., Ltd, All rights reserved.*
 * Package: cn.nj.zj.springbooyoss.Common
 * 阿里oss工具类
 *
 * @Author: zhaotianyu
 * @Date: 2019/11/8
 */

public class OSSUtils {
    private static final Logger logger = LoggerFactory.getLogger(OSSUtils.class);

    public static boolean aclUpload(InputStream inputStream, String fileName, AliOssConfig aliOssConfig) {

        boolean result = true;
        OSS ossClient = new OSSClientBuilder().build(aliOssConfig.getEndPoint(), aliOssConfig.getAccessKeyId(), aliOssConfig.getAccessKeySecret());
        if (ossClient.doesBucketExist(aliOssConfig.getBucket())) {

            try {
                ossClient.putObject(aliOssConfig.getBucket(), fileName, inputStream);
                ossClient.setObjectAcl(aliOssConfig.getBucket(), fileName, CannedAccessControlList.PublicRead);
                result = ossClient.doesObjectExist(aliOssConfig.getBucket(), fileName);
            } catch (Exception e) {
               logger.info("上传至阿里云失败" + e.getMessage());
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        System.out.println("aclUpload oss close inputStream error:" + e.getMessage());
                    }
                }
            }
        } else {
            logger.error("OSS中不存在对应的bucket");
            result = false;
        }

        ossClient.shutdown();
        return result;
    }

}
