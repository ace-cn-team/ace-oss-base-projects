package ace.oss.base.api.web.property;

import ace.oss.base.define.model.bo.OSSBizConfigBo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/17 10:50
 * @description
 */
@Data
@ConfigurationProperties(OSSProperties.PREFIX)
public class OSSProperties {
    public final static String PREFIX = "ace.oss";

    /**
     * 上传资源相关配置
     */
    private List<OSSBizConfigBo> ossBizConfigList;
}
