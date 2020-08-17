package ace.oss.base.api;

import ace.fw.feign.config.MultipartSupportConfig;
import ace.fw.model.response.GenericResponseExt;
import ace.oss.base.define.constant.OSSConstants;
import ace.oss.base.define.model.request.UploadFileRequest;
import ace.oss.base.define.model.response.UploadFileResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/14 13:55
 * @description 上传文件接口
 */
@FeignClient(
        name = OSSConstants.BASE_FEIGN_CLIENT_NAME,
        contextId = "UploadFileApi",
        path = "/" + UploadFileApi.MODULE_RESTFUL_NAME,
        configuration = MultipartSupportConfig.class
)
@Validated
public interface UploadFileApi {
    String MODULE_RESTFUL_NAME = "oss-base";

    @ApiOperation(value = "上传文件")
    @RequestMapping(path = "/upload/file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    GenericResponseExt<UploadFileResponse> upload(@Valid @RequestBody UploadFileRequest request);
}
