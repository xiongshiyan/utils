package top.jfunc.common.utils.local;

/**
 * Map<String,String></>类型值的工具类
 * @author xiongshiyan at 2017/12/27
 */
public class StringMapInfoHolderUtil {

    private static final MapInfoHolder<String , String> MAP_INFO_HOLDER = new MapInfoHolder<>();
    public static void put(String key , String value){
        MAP_INFO_HOLDER.add(key , value);
    }
    public static String get(String key){
        return MAP_INFO_HOLDER.get(key);
    }
    public static void remove(String key){
        MAP_INFO_HOLDER.remove(key);
    }
    public static void clear(){
        MAP_INFO_HOLDER.clear();
    }
}
