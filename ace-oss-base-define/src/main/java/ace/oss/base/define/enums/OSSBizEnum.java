package ace.oss.base.define.enums;

import ace.fw.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/3/17 15:10
 * @description oss 上传文件的业务类型
 */
@Getter
@AllArgsConstructor
public enum OSSBizEnum implements BaseEnum<String> {
    UPLOAD_AVATAR("upload_avatar", "用户上传头像"),
    ;

    private String code;

    private String desc;

}
