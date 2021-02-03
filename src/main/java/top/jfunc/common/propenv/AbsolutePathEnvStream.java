package top.jfunc.common.propenv;

import top.jfunc.common.utils.FileUtil;
import top.jfunc.common.utils.IoUtil;

import java.io.InputStream;

/**
 * 绝对路径下
 * 可以给定某个目录下寻找
 * @author 熊诗言
 */
public class AbsolutePathEnvStream extends BaseEnvStream {
    private String dir;

    public AbsolutePathEnvStream(String dir){
        this.dir = dir;
    }

    @Override
    protected InputStream loadInputStream(String fileName) {
        return IoUtil.getFileInputStream(FileUtil.concat(dir, fileName));
    }
}
