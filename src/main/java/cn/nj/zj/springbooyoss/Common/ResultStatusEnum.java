package cn.nj.zj.springbooyoss.Common;

/**
 *
 * Package: cn.nj.zj.springbooyoss.Common
 *
 * @Author: zhaotianyu
 * @Date: 2019/11/8
 */
public enum ResultStatusEnum {
    SUCCESS(200, "成功"),
    FAIL(500, "服务器内部错误");


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
