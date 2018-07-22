package cn.zytx.common.utils;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 文件工具
 * @author 熊诗言
 */
public class FileUtil {
    private FileUtil(){}

    /**
     * Java文件操作 获取文件扩展名
     * @param filename 文件名
     * @return 扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     * @param filename 文件名
     * @return 不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}
