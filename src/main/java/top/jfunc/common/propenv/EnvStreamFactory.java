package top.jfunc.common.propenv;

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

    /**
     * 如果要改变默认的加载方式Classpath，请调用该方法
     * @param kind 哪种加载方式
     * @see EnvStreamFactory#ENV_STREAM_KIND_CLASSPATH
     * @see EnvStreamFactory#ENV_STREAM_KIND_FILE
     */
    public static void choose(String kind){
        ENV_STREAM_KIND = kind;
    }

    /**
     * 这是一个包级方法，在Prop中调用
     * @param kind 哪种加载方式
     * @return BaseEnvStream
     */
    static BaseEnvStream getEnvStream(String kind){
        switch (kind){
            case ENV_STREAM_KIND_FILE:{return new FileEnvStream();}
            case ENV_STREAM_KIND_CLASSPATH:{return new ClasspathEnvStream();}
            default:throw new RuntimeException(kind + " of env stream is not supported!");
        }
    }
}
