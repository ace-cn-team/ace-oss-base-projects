package ace.oss.provider.api;

import ace.oss.provider.model.request.OSSUploadRequest;
import ace.oss.provider.model.response.OSSUploadResponse;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/13 14:56
 * @description 底层存储服务提供者
 */
public interface OSSProvider {
    /**
     * 上传文件
     *
     * @param OSSUploadRequest
     * @return
     */
    OSSUploadResponse upload(OSSUploadRequest OSSUploadRequest);
}
