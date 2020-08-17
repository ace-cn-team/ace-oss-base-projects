package ace.oss.provider.model.request;

import ace.oss.provider.enums.FileTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/13 15:11
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OSSUploadRequest {
    /**
     * 文件名称,如:test.txt
     */
    private String fileName;
    /**
     * 文件类型，http的 content-type字段
     * {@link FileTypeEnum}
     */
    private String fileContentType;
    /**
     * 目录名称
     */
    private String dirName;
    /**
     * 文件上传流
     */
    private InputStream fileInputStream;
}
