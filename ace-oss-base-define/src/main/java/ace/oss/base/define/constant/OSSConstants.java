package ace.oss.base.define.constant;

import java.io.File;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/19 17:37
 * @description
 */
public interface OSSConstants {
    /**
     * openfeign扫描微服务api的包路径
     */
    String FEIGN_CLIENT_SERVICE_PACKAGE = "ace.oss.base.api";
    /**
     * base层微服务openfeign配置名称
     */
    String BASE_FEIGN_CLIENT_NAME = "${ace.ms.service.api.ace-oss-base-api.name:ace-oss-base-api-web}";
    /**
     * base-api是否开启openfeign注入
     */
    String CONFIG_KEY_BASE_API_ENABLE = "ace.ms.service.ace-oss-base-api-web.enable";
    /**
     * 分隔符
     */
    String SEPARATOR_CHAR = "/";

}
