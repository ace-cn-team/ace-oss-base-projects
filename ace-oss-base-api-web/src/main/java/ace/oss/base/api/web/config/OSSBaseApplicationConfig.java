package ace.oss.base.api.web.config;

import ace.oss.base.api.web.property.OSSProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/1/14 15:57
 * @description
 */
@EnableConfigurationProperties(OSSProperties.class)
@Configuration
public class OSSBaseApplicationConfig {

}