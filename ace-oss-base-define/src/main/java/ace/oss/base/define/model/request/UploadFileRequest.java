package ace.oss.base.define.model.request;

import ace.common.base.define.model.bo.IAccountId;
import ace.common.base.define.model.bo.IAppId;
import ace.common.base.define.model.constraint.AppIdConstraint;
import ace.common.base.define.model.constraint.BizIdConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/14 16:16
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileRequest implements IAppId {
    /**
     * 应用ID
     */
    @AppIdConstraint
    private String appId;
    /**
     * 业务ID
     * {@link ace.oss.base.define.enums.OSSBizEnum}
     */
    @BizIdConstraint
    private String bizId;
    /**
     * 上传文件
     */
    @NotNull(message = "上传文件不能为空")
    private MultipartFile file;
}
