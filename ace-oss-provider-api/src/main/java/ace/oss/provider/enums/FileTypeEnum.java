package ace.oss.provider.enums;

import ace.fw.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 云存储支持的资源类型枚举
 *
 * @author caspar
 * @email 279397942@qq.com
 * Date: 2018/11/21
 * Time: 11:47
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum implements BaseEnum<String> {
    IMAGE_JPG("jpg", "jpg", "application/x-jpg", "jpg"),
    IMAGE_PNG("png", "png", "application/x-png", "png"),
    IMAGE_GIF("gif", "gif", "image/gif", "gif"),
    IMAGE_WEBP("webp", "webp", "application/x-jpg", "webp"),

    MUSIC_MP3("mp3", "mp3", "audio/mp3", "mp3"),
    MUSIC_M4A("m4a", "m4a", "audio/m4a", "m4a"),
    MUSIC_AAC("aac", "aac", "audio/x-mei-aac", "aac"),
    MUSIC_M4R("m4r", "m4r", "audio/m4r", "m4r"),

    VIDEO_MP4("mp4", "mp4", "video/mpeg4", "mp4"),
    VIDEO_M3U8("m3u8", "m3u8", "application/vnd.apple.mpegurl", "m3u8"),
    VIDEO_TS("ts", "ts", "video/MP2T", "ts"),
    VIDEO_MKV("mkv", "mkv", "video/mpeg4", "mkv"),

    FILE_ZIP("zip", "zip", "application/zip", "zip"),
    FILE_SVGA("svga", "svga", "application/octet-stream", "svga"),
    FILE_TXT("txt", "txt", "text/plain", "txt"),
    FILE_MD("md", "md", "text/markdown", "md"),
    FILE_LOG("log", "log", "text/plain", "log"),
    FILE_RAR("rar", "rar", "application/vnd.rar", "RAR压缩格式"),
    FILE_HTML("html", "html", "text/html", "富文本网页"),
    FILE_PDF("pdf", "pdf", "application/pdf\t", "pdf文档"),
    FILE_EXE("exe", "exe", "application/octet-stream", "Windows可运行文件"),

    OFFICE_XSLX("xlsx", "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "excel2007-2016版默认的文件格式，不能有宏"),
    OFFICE_XLS("xls", "xls", "application/vnd.ms-excel", "excel97-2003版，可以有宏"),
    OFFICE_CSV("csv", "csv", "text/csv", "以逗号分隔的文本文件"),
    OFFICE_DOC("doc", "doc", "application/msword", "office word文档"),
    OFFICE_DOCX("docx", "docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document\n", "office word文档"),
    OFFICE_PPT("ppt", "ppt", "application/vnd.ms-powerpoint", "office power point文档"),
    OFFICE_PPTX("pptx", "pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation", "office power point文档");

    /**
     * 业务码
     */
    private String code;
    /**
     * 文件扩展名
     */
    private String suffix;
    /**
     * Content-Type(Mime-Type)
     */
    private String contentType;
    /**
     * 枚举描述信息
     */
    private String desc;


}
