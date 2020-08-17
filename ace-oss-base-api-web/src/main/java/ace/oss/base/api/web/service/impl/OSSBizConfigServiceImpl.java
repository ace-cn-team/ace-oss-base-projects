package ace.oss.base.api.web.service.impl;

import ace.oss.base.api.web.property.OSSProperties;
import ace.oss.base.api.web.service.OSSBizConfigService;
import ace.oss.base.define.model.bo.OSSBizConfigBo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/14 17:16
 * @description
 */
@Component
public class OSSBizConfigServiceImpl implements OSSBizConfigService {
    @Autowired
    private OSSProperties ossProperties;

    @Override
    public Optional<OSSBizConfigBo> findById(String id) {
        return ossProperties.getOssBizConfigList().stream().filter(p -> StringUtils.equals(p.getBizId(), id)).findFirst();
    }

    @Override
    public OSSBizConfigBo findByIdOrDefault(String id) {
        return ossProperties.getOssBizConfigList().stream()
                .filter(p -> StringUtils.equals(p.getBizId(), id))
                .filter(p -> Boolean.TRUE.equals(p.getDefaultFlag()))
                .findFirst()
                .get();
    }
}

