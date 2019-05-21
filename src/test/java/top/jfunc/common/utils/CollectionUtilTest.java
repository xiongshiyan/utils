package top.jfunc.common.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;

/**
 * @author xiongshiyan at 2019/5/21 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class CollectionUtilTest {
    @Test
    public void testMerge(){
        List<String> list = new ArrayList<>(1);
        list.add("ss");
        Set<String> set = new HashSet<>(2);
        set.add("xx");
        set.add("yy");

        //不同的类型返回公共类型
        Collection<String> merge = CollectionUtil.merge(list, set);
        String[] array = merge.toArray(new String[3]);
        Assert.assertThat(array[0], is("ss"));
        Assert.assertThat(array[1] , is("xx"));
        Assert.assertThat(array[2] , is("yy"));
    }
    @Test
    public void testMerge2(){
        List<String> list = new ArrayList<>(1);
        list.add("ss");
        List<String> set = new LinkedList<>();
        set.add("xx");
        set.add("yy");

        //同样的类型返回自己的类型
        List<String> merge = CollectionUtil.merge(list, set);
        Assert.assertThat(merge.get(0) , is("ss"));
        Assert.assertThat(merge.get(1) , is("xx"));
        Assert.assertThat(merge.get(2) , is("yy"));
    }
}
