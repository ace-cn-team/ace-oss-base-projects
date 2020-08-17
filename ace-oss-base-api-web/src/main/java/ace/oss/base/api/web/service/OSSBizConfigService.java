package ace.oss.base.api.web.service;

import ace.oss.base.define.model.bo.OSSBizConfigBo;

import java.util.Optional;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/14 17:16
 * @description
 */
public interface OSSBizConfigService {
    Optional<OSSBizConfigBo> findById(String id);

    OSSBizConfigBo findByIdOrDefault(String id);
}
