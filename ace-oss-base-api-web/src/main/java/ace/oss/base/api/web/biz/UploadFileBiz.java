package ace.oss.base.api.web.biz;

import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.AceStringUtils;
import ace.fw.util.BusinessErrorUtils;
import ace.fw.util.GenericResponseExtUtils;
import ace.oss.base.api.web.service.OSSBizConfigService;
import ace.oss.base.define.constant.OSSConstants;
import ace.oss.base.define.enums.OSSErrorEnum;
import ace.oss.base.define.model.bo.OSSBizConfigBo;
import ace.oss.base.define.model.request.UploadFileRequest;
import ace.oss.base.define.model.response.UploadFileResponse;
import ace.oss.provider.api.OSSProvider;
import ace.oss.provider.model.request.OSSUploadRequest;
import ace.oss.provider.model.response.OSSUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/14 16:29
 * @description 上传文件
 */
@Component
@Slf4j
public class UploadFileBiz {
    @Autowired
    private OSSProvider ossProvider;
    @Autowired
    private OSSBizConfigService ossBizConfigService;

    public GenericResponseExt<UploadFileResponse> uploadFile(UploadFileRequest request) {

        OSSUploadResponse OSSUploadResponse = ossProvider.upload(this.toOSSUploadFileRequest(request));

        return GenericResponseExtUtils.buildSuccessWithData(
                UploadFileResponse.builder()
                        .url(OSSUploadResponse.getUrl())
                        .build()
        );
    }


    private OSSUploadRequest toOSSUploadFileRequest(UploadFileRequest request) {
        try {
            return OSSUploadRequest.builder()
                    .fileName(request.getFile().getOriginalFilename())
                    .fileInputStream(request.getFile().getInputStream())
                    .fileContentType(request.getFile().getContentType())
                    .dirName(this.getDirName(request))
                    .build();
        } catch (Throwable ex) {
            throw BusinessErrorUtils.create(OSSErrorEnum.UPLOAD_FAIL, ex);
        }
    }

    /**
     * 生成保存目录的路径
     *
     * @param request
     * @return
     */
    private String getDirName(UploadFileRequest request) {
        OSSBizConfigBo ossBizConfigBo = ossBizConfigService.findByIdOrDefault(request.getBizId());
        return AceStringUtils.replaceEach("${separator}${appId}${separator}${bizId}", ArrayUtils.toArray(
                Pair.of("${separator}", OSSConstants.SEPARATOR_CHAR),
                Pair.of("${appId}", request.getAppId()),
                Pair.of("${bizId}", ossBizConfigBo.getBizId())
        ));
    }
}
