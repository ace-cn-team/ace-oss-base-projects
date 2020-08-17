package ace.oss.provider.api.aliyun.oss.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/13 16:42
 * @description 阿里云 配置信息
 */
@ConfigurationProperties(AliyunOSSProperties.ALIYUN_OSS_CONFIG_PREFIX)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AliyunOSSProperties {
    public final static String ALIYUN_OSS_CONFIG_PREFIX = "ace.aliyun.oss";
    public final static String ALIYUN_OSS_CONFIG_ENABLE = "ace.aliyun.oss.enable";
    private Boolean enable;
    /**
     * url前缀
     */
    private String urlPrefix;
    /**
     * 阿里云端点
     */
    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    /**
     * 默认bucket名称
     */
    private String bucketName;
}
