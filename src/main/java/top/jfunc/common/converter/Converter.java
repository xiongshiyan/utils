package top.jfunc.common.converter;

/**
 * @author xiongshiyan at 2018/7/16
 */
public interface Converter {
    /**
     * 将字符串[可能是Json、Xml或者其他格式]转为Bean
     * @param src 原字符串
     * @param clazz BeanClass
     * @param <R> Bean
     * @return R
     */
    <R> R convert(String src, Class<R> clazz);

    static void checkNull(Converter converter){
        if (converter == null) {
            throw new NullPointerException("converter is null,please find method to set a converter or inject it with constructor");
        }
    }
}
