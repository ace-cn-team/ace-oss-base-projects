package ace.oss.base.define.model.response;

import ace.common.base.define.model.bo.IAccountId;
import ace.common.base.define.model.bo.IAppId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
public class UploadFileResponse {
    @ApiModelProperty("文件url地址")
    private String url;
}
