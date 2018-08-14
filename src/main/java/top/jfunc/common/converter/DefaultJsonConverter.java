package top.jfunc.common.converter;


import top.jfunc.json.JsonArray;
import top.jfunc.json.JsonObject;
import top.jfunc.json.impl.JSONArray;
import top.jfunc.json.impl.JSONObject;

/**
 * 将字符串转换为Json操作
 * @author xiongshiyan at 2018/7/16
 */
public class DefaultJsonConverter extends Json2BeanConverter implements JsonConverter {
    @Override
    public JsonObject convertJsonObject(String src) {
        return new JSONObject().parse(src);
    }

    @Override
    public JsonArray convertJsonArray(String src) {
        return new JSONArray().parse(src);
    }
}
