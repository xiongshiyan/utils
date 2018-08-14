package top.jfunc.common.converter;

import cn.zytx.common.json.JsonArray;
import cn.zytx.common.json.JsonObject;
import cn.zytx.common.json.impl.JSONArray;
import cn.zytx.common.json.impl.JSONObject;

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
