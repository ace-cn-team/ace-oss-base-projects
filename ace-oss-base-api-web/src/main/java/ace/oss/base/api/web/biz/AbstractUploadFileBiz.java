package ace.oss.base.api.web.biz;

import ace.fw.enums.FileSizeUnitEnum;
import ace.fw.exception.BusinessException;
import ace.fw.model.response.GenericResponseExt;
import ace.fw.util.AceFileUtils;
import ace.fw.util.AceStringUtils;
import ace.fw.util.BusinessErrorUtils;
import ace.fw.util.GenericResponseExtUtils;
import ace.oss.base.api.web.service.OSSBizConfigService;
import ace.oss.base.define.constant.OSSConstants;
import ace.oss.base.define.enums.FileTypeEnum;
import ace.oss.base.define.enums.OSSErrorEnum;
import ace.oss.base.define.model.bo.OSSBizConfigBo;
import ace.oss.base.define.model.request.UploadFileRequest;
import ace.oss.base.define.model.response.UploadFileResponse;
import ace.oss.provider.api.OSSProvider;
import ace.oss.provider.model.request.OSSUploadRequest;
import ace.oss.provider.model.response.OSSUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/14 16:29
 * @description 上传文件 抽象业务逻辑
 */
@Slf4j
public abstract class AbstractUploadFileBiz {
    @Autowired
    private OSSProvider ossProvider;
    @Autowired
    private OSSBizConfigService ossBizConfigService;

    public GenericResponseExt<UploadFileResponse> upload(UploadFileRequest request) {
        OSSBizConfigBo ossBizConfigBo = this.findOOSBizByIdOrThrow(request);
        // 检查上传文件类型
        this.checkFileType(request, ossBizConfigBo);
        // 检查上传文件大小
        this.checkFileSize(request, ossBizConfigBo);
        // 检查上传文件相关信息
        this.check(request, ossBizConfigBo);
        // 生成服务器的保存目录路径
        String dirName = this.getDirName(request);
        // 转换为上传文件请求对象
        OSSUploadRequest ossUploadRequest = this.toOSSUploadRequest(request, dirName);
        // 上传文件
        OSSUploadResponse OSSUploadResponse = ossProvider.upload(ossUploadRequest);

        return GenericResponseExtUtils.buildSuccessWithData(
                UploadFileResponse.builder()
                        .url(OSSUploadResponse.getUrl())
                        .build()
        );
    }

    protected OSSBizConfigBo findOOSBizByIdOrThrow(UploadFileRequest request) {
        Optional<OSSBizConfigBo> optionalOSSBizBo = ossBizConfigService.findById(request.getBizId());
        if (optionalOSSBizBo.isEmpty()) {
            BusinessErrorUtils.throwNew(OSSErrorEnum.NOT_EXIST_BIZ);
        }
        return optionalOSSBizBo.get();
    }

    /**
     * 验证上传文件大小
     *
     * @param request
     */
    protected void checkFileSize(UploadFileRequest request, OSSBizConfigBo ossBizConfigBo) {
        if (ossBizConfigBo.getLimitFileSize() == null || ossBizConfigBo.getLimitFileSize() <= 0) {
            return;
        }
        double fileSizeByte = request.getFile().getSize();
        double expectFileSize = ossBizConfigBo.getLimitFileSize();
        FileSizeUnitEnum expectFileSizeUnit = ossBizConfigBo.getLimitFileSizeUnit() != null ? ossBizConfigBo.getLimitFileSizeUnit() : FileSizeUnitEnum.BYTE;

        if (Boolean.FALSE.equals(AceFileUtils.validFileSize(fileSizeByte, expectFileSize, expectFileSizeUnit))) {
            String fileSizeDesc = expectFileSize + expectFileSizeUnit.getDesc();

            throw new BusinessException(OSSErrorEnum.UPLOAD_FILE_SIZE_INVALID.getCode(),
                    AceStringUtils.replaceEach(OSSErrorEnum.UPLOAD_FILE_SIZE_INVALID.getDesc(),
                            Pair.of(OSSErrorEnum.TEMPLATE_FILE_SIZE_DESC_KEY, fileSizeDesc)
                    )
            );
        }
    }

    /**
     * 验证上传文件类型
     *
     * @param request
     */
    protected void checkFileType(UploadFileRequest request, OSSBizConfigBo ossBizConfigBo) {
        if (CollectionUtils.isEmpty(ossBizConfigBo.getLimitFileTypes())) {
            return;
        }
        boolean isPass = false;
        for (FileTypeEnum limitFileType : ossBizConfigBo.getLimitFileTypes()) {
            if (AceFileUtils.validFileExtensionEqualsIgnoreCase(request.getFile().getOriginalFilename(), limitFileType.getSuffix())) {
                isPass = true;
                break;
            }
        }
        if (Boolean.FALSE.equals(isPass)) {
            String fileTypeDesc = ossBizConfigBo.getLimitFileTypes()
                    .stream()
                    .map(p -> p.getDesc())
                    .collect(Collectors.joining(","));

            throw new BusinessException(OSSErrorEnum.UPLOAD_FILE_TYPE_INVALID.getCode(),
                    AceStringUtils.replaceEach(OSSErrorEnum.UPLOAD_FILE_TYPE_INVALID.getDesc(),
                            Pair.of(OSSErrorEnum.TEMPLATE_FILE_TYPE_DESC_KEY, fileTypeDesc)
                    )
            );
        }
    }

    /**
     * 验证上传文件相关信息
     *
     * @param request
     */
    protected abstract void check(UploadFileRequest request, OSSBizConfigBo ossBizConfigBo);

    /**
     * 转换为上传文件请求对象
     *
     * @param request
     * @param dirName
     * @return
     */
    private OSSUploadRequest toOSSUploadRequest(UploadFileRequest request, String dirName) {
        try {
            return OSSUploadRequest.builder()
                    .fileName(request.getFile().getOriginalFilename())
                    .fileInputStream(request.getFile().getInputStream())
                    .fileContentType(request.getFile().getContentType())
                    .dirName(dirName)
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
