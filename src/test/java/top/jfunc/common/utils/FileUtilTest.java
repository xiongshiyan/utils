package top.jfunc.common.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;

/**
 * @author xiongshiyan at 2019/11/15 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class FileUtilTest {
    @Test
    public void testConcat(){
        if(OSUtil.isWindows()){
            Assert.assertThat(FileUtil.concat("/dd/tt" , "ff.txt") , is("/dd/tt\\ff.txt"));
            Assert.assertThat(FileUtil.concat("/dd/tt/" , "ff.txt") , is("/dd/tt/ff.txt"));
            Assert.assertThat(FileUtil.concat("/dd/tt" , "/ff.txt") , is("/dd/tt/ff.txt"));
            Assert.assertThat(FileUtil.concat("/dd/tt/" , "/ff.txt") , is("/dd/tt/ff.txt"));
            Assert.assertThat(FileUtil.concat("" , "/ff.txt") , is("/ff.txt"));
        }else {
            Assert.assertThat(FileUtil.concat("/dd/tt" , "ff.txt") , is("/dd/tt/ff.txt"));
            Assert.assertThat(FileUtil.concat("/dd/tt/" , "ff.txt") , is("/dd/tt/ff.txt"));
            Assert.assertThat(FileUtil.concat("/dd/tt" , "/ff.txt") , is("/dd/tt/ff.txt"));
            Assert.assertThat(FileUtil.concat("/dd/tt/" , "/ff.txt") , is("/dd/tt/ff.txt"));
            Assert.assertThat(FileUtil.concat("" , "/ff.txt") , is("/ff.txt"));

        }
    }

    @Ignore
    @Test
    public void testMakeSureExist() throws IOException{
        if(OSUtil.isWindows()){
            String fileName = "D:\\mnt\\cc\\dd\\vv\\ff.txt";
            FileUtil.makeSureExistFile(fileName);
        }else {
            String fileName = "/mnt/cc/dd/vv/ff.txt";
            FileUtil.makeSureExistFile(fileName);
        }
    }
}
