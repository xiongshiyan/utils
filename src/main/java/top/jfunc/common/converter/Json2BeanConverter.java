package top.jfunc.common.converter;

import cn.zytx.common.json.JsonObject;
import cn.zytx.common.json.impl.JSONObject;

/**
 * Json String -> JavaBean
 * 此类也示范了统一json的好处
 * cn.zytx.common.json.JsonObject
 * cn.zytx.common.json.impl.JSONObject
 * <!包名不变!>,只需要在build.gradle中修改引用就能改变json的实现
 * @author xiongshiyan at 2018/7/16
 */
public class Json2BeanConverter implements Converter{
    @Override
    public <R> R convert(String src, Class<R> clazz){
        JsonObject json = new JSONObject();
        return json.deserialize(src, clazz);
    }
}
