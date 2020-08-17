package ace.oss.base.define.model.bo;

import ace.fw.enums.FileSizeUnitEnum;
import ace.oss.base.define.enums.FileTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Caspar
 * @contract 279397942@qq.com
 * @create 2020/8/14 17:36
 * @description 操作的业务类型
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OSSBizConfigBo {
    /**
     * id
     */
    private String id;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 业务ID
     */
    private String bizId;
    /**
     * 业务名称
     */
    private String bizName;
    /**
     * 业务分组
     */
    private String bizGroup;
    /**
     * 限制文件类型
     */
    private List<FileTypeEnum> limitFileTypes;
    /**
     * 限制文件大小
     */
    private Double limitFileSize;
    /**
     * 限制文件大小的单位
     */
    private FileSizeUnitEnum limitFileSizeUnit = FileSizeUnitEnum.BYTE;
    /**
     * 是否默认值
     */
    private Boolean defaultFlag;
}
