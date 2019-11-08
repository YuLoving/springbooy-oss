package cn.nj.zj.springbooyoss.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * /**
 * Copyright 2008-2018 OPPO Mobile Comm Corp., Ltd, All rights reserved.*
 * Package: cn.nj.zj.springbooyoss.Configs
 *
 * @Author: zhaotianyu
 * @Date: 2019/11/8
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * 本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，
     * 并产生文档内容（除了被@ApiIgnore指定的请求）。添加文档内容
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.nj.zj.springbooyoss.Controller"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Springboot使用swagger2 搭建API")
                .description("主要是提供阿里的OSS等服务")
                //超链接
                .termsOfServiceUrl("")
                //联系人
                .contact(new Contact("ZTY", "", "974456795@qq.com"))
                .version("1.0")
                .build();

    }


}
