package top.jfunc.common.propenv;

import java.io.InputStream;

/**
 * 从类路径下找
 * @author 熊诗言
 */
public class ClasspathEnvStream extends BaseEnvStream {
    @Override
    protected InputStream loadInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }
}
