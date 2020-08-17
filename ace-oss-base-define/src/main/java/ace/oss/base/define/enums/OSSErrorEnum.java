package ace.oss.base.define.enums;

import ace.fw.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/3/17 15:10
 * @description Integer[] OSS = new Integer[]{920000, 929999};
 */
@Getter
@AllArgsConstructor
public enum OSSErrorEnum implements BaseEnum<String> {
    UPLOAD_FAIL("920001", "上传失败"),
    NOT_EXIST_BIZ("920002", "没有该业务ID"),
    UPLOAD_FILE_TYPE_INVALID("920003", "请上传规定的文件类型," + OSSErrorEnum.TEMPLATE_FILE_TYPE_DESC_KEY),
    UPLOAD_FILE_SIZE_INVALID("920004", "请上传规定的文件大小,最大" + OSSErrorEnum.TEMPLATE_FILE_SIZE_DESC_KEY),
    ;
    public final static String TEMPLATE_FILE_TYPE_DESC_KEY = "${fileTypeDesc}";
    public final static String TEMPLATE_FILE_SIZE_DESC_KEY = "${fileSizeDesc}";
    private String code;

    private String desc;

}
