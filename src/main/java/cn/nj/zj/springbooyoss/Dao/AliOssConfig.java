package cn.nj.zj.springbooyoss.Dao;

import lombok.Data;

/**
 * Package: cn.nj.zj.springbooyoss.Dao
 * @Author: zhaotianyu
 * @Date: 2019/11/8
 */
@Data
public class AliOssConfig {

    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucket;

}
