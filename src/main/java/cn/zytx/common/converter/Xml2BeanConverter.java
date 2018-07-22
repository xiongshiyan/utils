package cn.zytx.common.converter;

/**
 * xml String -> JavaBean
 * @author xiongshiyan at 2018/7/16
 */
public class Xml2BeanConverter implements Converter{
    @Override
    public <R> R convert(String src, Class<R> clazz){
        throw new RuntimeException("方法等待实现 ...");
    }
}
