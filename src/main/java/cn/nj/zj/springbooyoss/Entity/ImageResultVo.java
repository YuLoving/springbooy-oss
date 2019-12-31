package cn.nj.zj.springbooyoss.Entity;

import lombok.Data;

import java.io.Serializable;

 /**
 * Package: cn.nj.zj.springbooyoss.Entity
 *
 * @Author: zhaotianyu
 * @Date: 2019/11/8
 */
@Data
public class ImageResultVo implements Serializable {
    private static final long serialVersionUID = -5432335018514196108L;

    /**
     * 图片路径
     */
    private String path;
    /**
     * 图片域名
     */
    private String host;


}
