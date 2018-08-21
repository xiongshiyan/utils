package top.jfunc.common.propenv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * 从类路径下找
 * @author 熊诗言
 */
public class ClasspathEnvStream extends BaseEnvStream {
    private static final Logger logger = LoggerFactory.getLogger(ClasspathEnvStream.class);
    @Override
    protected InputStream loadInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }
}
