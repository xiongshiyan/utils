package top.jfunc.common.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;

/**
 * @author xiongshiyan at 2019/5/21 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class MapUtilTest {
    @Test
    public void testMergeMap1(){
        Map<String , String> first = new HashMap<>();
        first.put("xx" , "xx");
        first.put("yy" , "yy");
        Map<String , String> second = new HashMap<>();
        second.put("xx" , "cc");
        second.put("zz" , "zz");

        Map<String, String> mergeMap = MapUtil.mergeMap(first, second , true);
        Assert.assertThat(mergeMap.size() , is(3));
        Assert.assertThat(mergeMap.get("xx") , is("xx"));
    }
    @Test
    public void testMergeMap2(){
        Map<String , String> first = new HashMap<>();
        first.put("xx" , "xx");
        first.put("yy" , "yy");
        Map<String , String> second = new HashMap<>();
        second.put("xx" , "cc");
        second.put("zz" , "zz");
        Map<String, String> mergeMap = MapUtil.mergeMap(first, second , false);
        Assert.assertThat(mergeMap.size() , is(3));
        Assert.assertThat(mergeMap.get("xx") , is("cc"));
    }


    @Test
    public void testMergeMultiMap(){
        MultiValueMap<String , String> first = new ArrayListMultiValueMap<>();
        first.add("xx" , "xx");
        first.add("yy" , "yy");
        MultiValueMap<String , String> second = new ArrayListMultiValueMap<>();
        second.add("xx" , "xx");
        second.add("zz" , "zz");

        MultiValueMap<String, String> mergeMap = MapUtil.mergeMap(first, second);
        Assert.assertThat(mergeMap.size() , is(3));
        Assert.assertThat(mergeMap.toString() , is("{xx=[xx, xx], yy=[yy], zz=[zz]}"));
    }
}
