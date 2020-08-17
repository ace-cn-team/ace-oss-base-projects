package ace.oss.provider.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/14 11:50
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OSSUploadResponse {
    /**
     * 文件url地址
     */
    private String url;
}
