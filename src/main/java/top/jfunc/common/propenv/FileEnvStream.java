package top.jfunc.common.propenv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.jfunc.common.utils.IoUtil;

import java.io.InputStream;

/**
 * 根据给定文件寻找
 * @author 熊诗言
 */
public class FileEnvStream extends BaseEnvStream {
    private static final Logger logger = LoggerFactory.getLogger(FileEnvStream.class);
    @Override
    protected InputStream loadInputStream(String fileName) {
        return IoUtil.getFileInputStream(fileName);
    }
}
