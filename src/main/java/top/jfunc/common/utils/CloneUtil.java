package top.jfunc.common.utils;

/**
 * @author xiongshiyan at 2020/2/23 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class CloneUtil {
    /**
     * 对象Clone
     * @see https://blog.csdn.net/xxssyyyyssxx/article/details/104365036
     * 保证实现类重写了{@link Object#clone()}方法
     */
    @SuppressWarnings("unchecked")
    public static <T extends Cloneable> T clone(T t){
        try {
            /**
             * clone()是Object类的方法，务必保证clone的对象类重写了该方法，并且方法置为了public
             */
            java.lang.reflect.Method method = t.getClass().getMethod("clone");
            method.setAccessible(true);
            return (T)method.invoke(t);
        } catch (Exception e) {
            throw new RuntimeException("不支持clone,请检查是否重写clone并且实现Cloneable接口" , e);
        }
    }
}
