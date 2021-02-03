package top.jfunc.common.propenv;

import java.util.HashMap;
import java.util.Map;

/**
 * EnvStream工厂
 * @author 熊诗言
 */
public class EnvStreamFactory {
    public static final String ENV_STREAM_KIND_CLASSPATH = "classpath";
    public static final String ENV_STREAM_KIND_FILE      = "file";
    /**
     *默认为classpath下加载，通过choose方法初始化
     */
    public static String ENV_STREAM_KIND = ENV_STREAM_KIND_CLASSPATH;

    private static final Map<String, BaseEnvStream> CACHE = new HashMap<>();
    static {
        CACHE.put(ENV_STREAM_KIND_CLASSPATH, new ClasspathEnvStream());
        CACHE.put(ENV_STREAM_KIND_FILE, new FileEnvStream());
    }

    /**
     * 如果要改变默认的加载方式Classpath，请调用该方法
     * @param kind 哪种加载方式
     * @see EnvStreamFactory#ENV_STREAM_KIND_CLASSPATH
     * @see EnvStreamFactory#ENV_STREAM_KIND_FILE
     */
    public static void choose(String kind){
        ENV_STREAM_KIND = kind;
    }


    public static void put(String kind, BaseEnvStream baseEnvStream){
        CACHE.put(kind, baseEnvStream);
    }

    /**
     * 这是一个包级方法，在Prop中调用
     * @return BaseEnvStream
     */
    static BaseEnvStream getEnvStream(String kind){
        return CACHE.get(kind);
    }
}
