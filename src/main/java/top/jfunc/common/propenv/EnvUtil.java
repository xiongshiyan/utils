package top.jfunc.common.propenv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取当前环境变量的工具类
 * @author 熊诗言
 */
public class EnvUtil {
    private EnvUtil(){}

    private static final Logger logger = LoggerFactory.getLogger(EnvUtil.class);
    public static final String ENV_PROD    = "prod";
    public static final String ENV_PRETEST = "pretest";
    public static final String ENV_TEST    = "test";
    public static final String ENV_DEV     = "dev";
    private static final String KEY = "ENVSETTING";
    /**
     * 获取当前环境
     * @return String
     */
    public static String env(){
        return env(KEY);
    }
    /**
     * 获取当前环境
     * @param envKey envKey
     * @return String
     */
    public static  String env(String envKey){
        String env = System.getProperty(envKey);
        logger.debug(env);
        if(null == env || "".equals(env)){
            env = System.getenv(envKey);
        }
        logger.debug(env);
        return env;
    }
}
