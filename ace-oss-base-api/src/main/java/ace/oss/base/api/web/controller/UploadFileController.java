package ace.oss.base.api.web.controller;

import ace.oss.base.api.UploadFileApi;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/14 16:26
 * @description
 */
@RestController
@RequestMapping(path = "/" + UploadFileApi.MODULE_RESTFUL_NAME)
@Validated
public interface UploadFileController extends UploadFileApi {
}
