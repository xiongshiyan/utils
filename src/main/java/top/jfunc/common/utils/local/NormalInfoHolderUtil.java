package top.jfunc.common.utils.local;

/**
 * 任意类型值的工具类
 * @author xiongshiyan at 2017/12/27
 */
public class NormalInfoHolderUtil {

    private static final InfoHolder INFO_HOLDER = new InfoHolder();
    @SuppressWarnings("unchecked")
    public static <T> void set(T object){
        INFO_HOLDER.set(object);
    }
    @SuppressWarnings("unchecked")
    public static <T>  T get(){
        return (T) INFO_HOLDER.get();
    }
    public static void clear(){
        INFO_HOLDER.clear();
    }
}
