package ace.oss.base.api.web.controller;

import ace.fw.model.response.GenericResponseExt;
import ace.oss.base.api.web.biz.UploadFileBiz;
import ace.oss.base.define.model.request.UploadFileRequest;
import ace.oss.base.define.model.response.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/14 16:27
 * @description
 */
public class UploadFileControllerImpl implements UploadFileController {
    @Autowired
    private UploadFileBiz uploadFileBiz;

    @Override
    public GenericResponseExt<UploadFileResponse> upload(@Valid UploadFileRequest request) {
        return uploadFileBiz.uploadFile(request);
    }
}
