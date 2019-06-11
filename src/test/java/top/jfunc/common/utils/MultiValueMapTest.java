package top.jfunc.common.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * @author xiongshiyan at 2019/3/28 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class MultiValueMapTest {
    private MultiValueMap<String, String> multiValueMap =
            new ArrayListMultiValueMap<String, String>(){
                {
                    add("charset" , "utf-8");
                    add("charset" , "gb2312");
                    add("charset2" , "gbk" , "big5");

                    add("dd" , "66");
                    addFirst("dd" , "77");
                }
            };
    @Test
    public void testInit(){
        Assert.assertThat(multiValueMap.size() , is(3));
        Assert.assertThat(multiValueMap.getFirst("charset") , is("utf-8"));
        Assert.assertThat(multiValueMap.getLast("charset") , is("gb2312"));
        Assert.assertThat(multiValueMap.getFirst("charset2") , is("gbk"));
        Assert.assertThat(multiValueMap.getLast("charset2") , is("big5"));

        Assert.assertThat(multiValueMap.getFirst("dd") , is("77"));
    }

    @Test
    public void testForEach(){
        multiValueMap.forEachKeyValue((k,v)->System.out.println(k+":"+v));
    }


}
