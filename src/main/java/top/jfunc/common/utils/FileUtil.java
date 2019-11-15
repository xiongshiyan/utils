package top.jfunc.common.utils;

import java.io.File;
import java.io.IOException;

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

    public static File makeSureExistDir(String fileDir) throws IOException{
        File file = new File(fileDir);
        return makeSureExistDir(file);
    }

    public static File makeSureExistDir(File dirFile) throws IOException{
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        return dirFile;
    }

    public static File makeSureExistFile(String fileName) throws IOException {
        File file = new File(fileName);
        return makeSureExistFile(file);
    }

    public static File makeSureExistFile(File file) throws IOException {
        if(file.exists()){
            return file;
        }
        //先确保父目录存在
        File parentFile = file.getParentFile();
        makeSureExistDir(parentFile);

        file.createNewFile();
        return file;
    }

    /**
     * 连接目录名+文件名，自动处理分隔符
     * @param dir 目录名
     * @param fileName  文件名，可以带分隔符
     * @return 连接后的文件名
     */
    public static String concat(String dir , String fileName){
        if(StrUtil.isEmpty(fileName)){
            throw new IllegalArgumentException("fileName 不允许为空");
        }
        if(StrUtil.isEmpty(dir)){
            return fileName;
        }
        if(dir.endsWith(File.separator) && fileName.startsWith(File.separator)){
            return dir + fileName.substring(1);
        }
        if(!dir.endsWith(File.separator) && !fileName.startsWith(File.separator)){
            return dir + File.separator + fileName;
        }
        return dir + fileName;
    }
}
