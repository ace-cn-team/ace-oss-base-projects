package ace.oss.provider.api.aliyun.oss.autoconfigure;

import ace.oss.provider.api.OSSProvider;
import ace.oss.provider.api.aliyun.oss.AliyunOSSProvider;
import ace.oss.provider.api.aliyun.oss.property.AliyunOSSProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/13 18:04
 * @description
 */
@ConditionalOnProperty(
        value = AliyunOSSProperties.ALIYUN_OSS_CONFIG_ENABLE
)
@EnableConfigurationProperties(AliyunOSSProperties.class)
@Configuration
public class AliyunOSSAutoConfigure {
    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    @Bean
    @ConditionalOnMissingBean
    public OSS ossClient() {
        OSS ossClient = new OSSClientBuilder()
                .build(
                        aliyunOSSProperties.getEndPoint(),
                        aliyunOSSProperties.getAccessKeyId(),
                        aliyunOSSProperties.getAccessKeySecret()
                );
        return ossClient;
    }

    @Bean
    public OSSProvider ossProvider(OSS ossClient) {
        OSSProvider ossProvider = AliyunOSSProvider.builder()
                .aliyunOSSProperties(aliyunOSSProperties)
                .ossClient(ossClient)
                .build();
        return ossProvider;
    }
}
