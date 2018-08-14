package top.jfunc.common.converter;

import cn.zytx.common.json.JsonArray;
import cn.zytx.common.json.JsonObject;

/**
 * 专门针对string转换为json的
 * @author xiongshiyan at 2018/7/16
 */
public interface JsonConverter extends Converter {
    /**
     * 将字符串转换为JsonObject
     * @param src 原字符串
     * @return JsonObject
     */
    JsonObject convertJsonObject(String src);
    /**
     * 将字符串转换为JsonArray
     * @param src 原字符串
     * @return JsonArray
     */
    JsonArray convertJsonArray(String src);
}
