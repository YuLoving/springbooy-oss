package cn.nj.zj.springbooyoss.Controller;

import cn.nj.zj.springbooyoss.Common.Constant;
import cn.nj.zj.springbooyoss.Common.OSSUtils;
import cn.nj.zj.springbooyoss.Common.Result;
import cn.nj.zj.springbooyoss.Dao.AliOssConfig;
import cn.nj.zj.springbooyoss.Entity.ImageResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;

/**
 * /**
 * Copyright 2008-2018 OPPO Mobile Comm Corp., Ltd, All rights reserved.*
 * Package: cn.nj.zj.springbooyoss.Controller
 *
 * @Author: zhaotianyu
 * @Date: 2019/11/8
 */
@Api(tags = {"后台对阿里的OSS进行操作服务"})
@RestController
@RequestMapping("/oss")
public class UploadToAiliossController {

    private static final Logger logger= LoggerFactory.getLogger(UploadToAiliossController.class);
    @Autowired
    protected MessageSource messageSource;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.keyid}")
    private String keyid;

    @Value("${aliyun.oss.keysecret}")
    private String keysecret;

    @Value("${aliyun.oss.bucketname}")
    private String bucketname;

    @Value("${aliyun.oss.host}")
    private String host;

    @ApiOperation(value = "上传图片")
    @PostMapping (value = "/upload", headers = "Content-Type=multipart/form-data")
    public Result<ImageResultVo> upload(@RequestParam ("file") MultipartFile file){
        //1.先对传过来的图片类型校验
        if(!Constant.IMAGE_TYPE.contains(file.getContentType())){
            return Result.fail(messageSource.getMessage("error.file.type",null, Locale.CHINA));
        }
        ImageResultVo imageResultVo = new ImageResultVo();
        imageResultVo.setHost(host);

        //2.获取前端输入的文件名。取出拓展名
        String filename = file.getOriginalFilename();
        //取出拓展名
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        //3.校验拓展名格式
        if(!Constant.IMAGE_SUFFIX.contains(ext)){
            return Result.fail(messageSource.getMessage("error.file.type",null,Locale.CHINA));
        }
        StringBuilder builder = new StringBuilder();
        //存储之后新的文件名
        builder.append("image").append("/").append(System.currentTimeMillis()).append(".").append(ext);
        imageResultVo.setPath(builder.toString());
        //最后开始上传至阿里云oss
        AliOssConfig ossConfig = new AliOssConfig();
        ossConfig.setAccessKeyId(keyid);
        ossConfig.setEndPoint(endpoint);
        ossConfig.setAccessKeySecret(keysecret);
        ossConfig.setBucket(bucketname);
        try {
            boolean result = OSSUtils.aclUpload(file.getInputStream(), builder.toString(), ossConfig);
            if(!result){
                return Result.fail(messageSource.getMessage("error.file.fail",null,Locale.CHINA));
            }
        }catch (Exception e){
            logger.error("");
        }
        return Result.success(imageResultVo);
    }








}
