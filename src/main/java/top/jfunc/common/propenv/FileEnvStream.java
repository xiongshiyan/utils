package top.jfunc.common.propenv;

import top.jfunc.common.utils.IoUtil;

import java.io.InputStream;

/**
 * 根据给定文件寻找
 * @author 熊诗言
 */
public class FileEnvStream extends BaseEnvStream {
    @Override
    protected InputStream loadInputStream(String fileName) {
        return IoUtil.getFileInputStream(fileName);
    }
}
