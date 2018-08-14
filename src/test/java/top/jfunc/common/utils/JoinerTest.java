package top.jfunc.common.utils;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiongshiyan at 2017/12/7
 */
public class JoinerTest {
    @Test
    public void testJoin() throws Exception{
        System.out.println(Joiner.on(":").skipNulls().appendTo(new StringBuffer(),"f1","f2","2332323",null).toString());
    }

    @Test
    public void testJoinMap(){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3",null);
        map.put(null,null);
        String dsa = Joiner.on("&").withKeyValueSeparator("=").useForNull("").join(map);
        System.out.println(dsa);
    }
    @Test
    //对值进行特殊处理
    public void testJoinMapEditor(){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        String dsa = Joiner.on("&").withKeyValueSeparator("=",(v)->"{"+v+"}").useForNull("").join(map);
        System.out.println(dsa);
    }
}
