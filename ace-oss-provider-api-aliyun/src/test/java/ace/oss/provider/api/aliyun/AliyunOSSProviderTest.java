package ace.oss.provider.api.aliyun;

import ace.fw.json.JsonUtils;
import ace.fw.json.jackson.JacksonPlugin;
import ace.oss.provider.api.OSSProvider;
import ace.oss.provider.api.aliyun.oss.AliyunOSSProvider;
import ace.oss.provider.api.aliyun.oss.property.AliyunOSSProperties;
import ace.oss.provider.model.request.OSSUploadRequest;
import ace.oss.provider.model.response.OSSUploadResponse;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/13 18:16
 * @description
 */
@Slf4j
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class AliyunOSSProviderTest {

    @Autowired
    private OSSProvider ossProvider;

    @Before
    public void before() {
        JsonUtils.setJsonPlugin(new JacksonPlugin());
    }

    @Test
    public void test_0001_uploadFile() throws Throwable {

        FileInputStream fileInputStream = FileUtils.openInputStream(ResourceUtils.getFile("classpath:test.png"));
        OSSProvider ossProvider = getOSSProvider();
        OSSUploadResponse OSSUploadResponse = ossProvider.upload(OSSUploadRequest.builder()
                .dirName("/test")
                .fileContentType("application/x-png")
                .fileInputStream(fileInputStream)
                .fileName("test.png")
                .build());
        System.out.println(OSSUploadResponse);
        File downloadFile = new File("d:/test.png");
        FileUtils.copyURLToFile(new URL(OSSUploadResponse.getUrl()), downloadFile);
        downloadFile.delete();
    }

    private OSSProvider getOSSProvider() {
        AliyunOSSProperties aliyunOSSProperties = AliyunOSSProperties.builder()
                .accessKeyId("")
                .accessKeySecret("")
                .bucketName("dev-shop-image")
                .enable(true)
                .endPoint("oss-cn-shenzhen.aliyuncs.com")
                .urlPrefix("http://dev-shop-image.oss-cn-shenzhen.aliyuncs.com")
                .build();

        OSS ossClient = new OSSClientBuilder()
                .build(
                        aliyunOSSProperties.getEndPoint(),
                        aliyunOSSProperties.getAccessKeyId(),
                        aliyunOSSProperties.getAccessKeySecret()
                );

        OSSProvider ossProvider = AliyunOSSProvider.builder()
                .ossClient(ossClient)
                .aliyunOSSProperties(aliyunOSSProperties)
                .build();

        return ossProvider;
    }
}
