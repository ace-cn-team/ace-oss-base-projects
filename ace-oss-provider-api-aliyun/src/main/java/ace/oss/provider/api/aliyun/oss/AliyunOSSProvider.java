package ace.oss.provider.api.aliyun.oss;

import ace.fw.json.JsonUtils;
import ace.fw.logic.common.util.AceUUIDUtils;
import ace.oss.provider.api.OSSProvider;
import ace.oss.provider.api.aliyun.oss.property.AliyunOSSProperties;
import ace.oss.provider.model.request.OSSUploadRequest;
import ace.oss.provider.model.response.OSSUploadResponse;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/13 16:09
 * @description 阿里云 OSS实现
 */
@Slf4j
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliyunOSSProvider implements OSSProvider {
    private final static String SEPARATOR_CHAR = "/";
    /**
     * 阿里云配置
     */
    private AliyunOSSProperties aliyunOSSProperties;
    /**
     * 阿里云oss 接口
     */
    private OSS ossClient;

    @Override
    public OSSUploadResponse upload(OSSUploadRequest file) {

        // 检查是否需要创建相关bucket
        this.checkBucket(file);
        // 生成web服务器存放的绝对路径
        String serverRelativeFileFullName = this.getServerRelativeFileFullName(file);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentDisposition("attachment;fileName=" + file.getFileName());
        metadata.setContentType(file.getFileContentType());
        PutObjectRequest request = new PutObjectRequest(aliyunOSSProperties.getBucketName(), serverRelativeFileFullName, file.getFileInputStream(), metadata);
        PutObjectResult result = ossClient.putObject(request);

        log.info("result={}", JsonUtils.toJson(result));

        URL url;
        try {
            url = new URL(new URL(aliyunOSSProperties.getUrlPrefix()), serverRelativeFileFullName);
        } catch (Throwable ex) {
            log.error("url格式不正确", ex);
            throw new RuntimeException(ex);
        }
        return OSSUploadResponse.builder()
                .url(url.toString())
                .build();
    }

    private String getServerRelativeFileFullName(OSSUploadRequest file) {
        // 生成文件名
        String fileName = this.newFileName(file);

        String dirName = StringUtils.replaceEach("${dirName}${separatorChar}${fileName}",
                new String[]{
                        "${dirName}",
                        "${fileName}",
                        "${separatorChar}"
                }, new String[]{
                        file.getDirName(),
                        fileName,
                        SEPARATOR_CHAR
                });

        return this.replaceFirstCharIsBackSlash(dirName);
    }

    /**
     * 第一个字符是 '/' 需要删除，因为阿里云不支持第一个字符是反斜杠
     *
     * @param urlPath
     * @return
     */
    private String replaceFirstCharIsBackSlash(String urlPath) {
        if (urlPath.charAt(0) == '/') {
            return urlPath.substring(1, urlPath.length());
        } else {
            return urlPath;
        }
    }

    /**
     * 生成新的文件名
     *
     * @param file
     * @return
     */
    private String newFileName(OSSUploadRequest file) {
        return StringUtils.replaceEach("${fileName}.${fileExt}", new String[]{
                "${fileName}",
                "${fileExt}"
        }, new String[]{
                AceUUIDUtils.generateTimeUUIDShort32(),
                FilenameUtils.getExtension(file.getFileName())
        });
    }

    private void checkBucket(OSSUploadRequest file) {
        if (!ossClient.doesBucketExist(aliyunOSSProperties.getBucketName())) {
            ossClient.createBucket(aliyunOSSProperties.getBucketName());
        }
    }
}
