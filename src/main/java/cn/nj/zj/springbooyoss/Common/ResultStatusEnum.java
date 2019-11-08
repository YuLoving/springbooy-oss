package cn.nj.zj.springbooyoss.Common;

/**
 * /**
 * Copyright 2008-2018 OPPO Mobile Comm Corp., Ltd, All rights reserved.*
 * Package: cn.nj.zj.springbooyoss.Common
 *
 * @Author: zhaotianyu
 * @Date: 2019/11/8
 */
public enum ResultStatusEnum {
    SUCCESS(200, "成功"),
    NODATA(204, "无数据"),
    DATA_EXISTED(400, "资源已存在"),
    DATA_NOT_EXISTS(404, "资源不存在"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "服务不可用"),
    SIGN_ERR(405, "签名错误"),
    PARAM_ERR(415, "请求参数错误"),
    FAIL(500, "服务器内部错误"),
    HOST_ERROR(501, "服务器无法识别请求");

    private int status;
    private String describe;

    private ResultStatusEnum(int status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public int getStatus() {
        return this.status;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
