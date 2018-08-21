package top.jfunc.common.propenv;

import java.util.HashMap;
import java.util.Map;

/**
 * 加载配置工具类，主要提供缓存支持
 * @author 熊诗言
 */
public class PropertiesUtils {
    private static Prop prop = null;
    private static final Map<String, Prop> map = new HashMap<String, Prop>();

    private PropertiesUtils() {}

    /**
     * Using the properties file. It will loading the properties file if not loading.
     * @see #use(String, String)
     */
    public static Prop use(String fileName) {
        return use(fileName, "UTF-8");
    }

    /**
     * Using the properties file. It will loading the properties file if not loading.
     * <p>
     * Example:<br>
     * PropertiesUtils.use("config.txt", "UTF-8");<br>
     * PropertiesUtils.use("other_config.txt", "UTF-8");<br><br>
     * String userName = PropertiesUtils.get("userName");<br>
     * String password = PropertiesUtils.get("password");<br><br>
     *
     * userName = PropertiesUtils.use("other_config.txt").get("userName");<br>
     * password = PropertiesUtils.use("other_config.txt").get("password");<br><br>
     *
     * PropertiesUtils.use("com/jfinal/config_in_sub_directory_of_classpath.txt");
     *
     * @param fileName the properties file's name in classpath or the sub directory of classpath
     * @param encoding the encoding
     */
    public static Prop use(String fileName, String encoding) {
        Prop result = map.get(fileName);
        if (result == null) {
            synchronized (map) {
                result = map.get(fileName);
                if (result == null) {
                    result = new Prop(fileName, encoding);
                    map.put(fileName, result);
                    if (PropertiesUtils.prop == null){
                        PropertiesUtils.prop = result;
                    }
                }
            }
        }
        return result;
    }

    public static Prop useless(String fileName) {
        Prop previous = map.remove(fileName);
        if (PropertiesUtils.prop == previous){
            PropertiesUtils.prop = null;
        }
        return previous;
    }

    public static void clear() {
        prop = null;
        map.clear();
    }

    public static Prop getProp() {
        if (prop == null) {
            throw new IllegalStateException("Load propties file by invoking PropertiesUtils.use(String fileName) method first.");
        }
        return prop;
    }

    public static Prop getProp(String fileName) {
        return map.get(fileName);
    }

    public static String get(String key) {
        return getProp().get(key);
    }

    public static String get(String key, String defaultValue) {
        return getProp().get(key, defaultValue);
    }

    public static Integer getInt(String key) {
        return getProp().getInt(key);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        return getProp().getInt(key, defaultValue);
    }

    public static Long getLong(String key) {
        return getProp().getLong(key);
    }

    public static Long getLong(String key, Long defaultValue) {
        return getProp().getLong(key, defaultValue);
    }

    public static Boolean getBoolean(String key) {
        return getProp().getBoolean(key);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return getProp().getBoolean(key, defaultValue);
    }

    public static boolean containsKey(String key) {
        return getProp().containsKey(key);
    }

}

