package top.jfunc.common.sensitiveword.wordset;

import top.jfunc.common.sensitiveword.FilterWordSet;
import top.jfunc.common.utils.IoUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 敏感词来自于文件，一行一个敏感词
 * @author 熊诗言
 */
public class FileFilterWordSet implements FilterWordSet {

    private File file;
    private String encoding = "UTF-8";

    public FileFilterWordSet(File file){
        validFile(file);
        this.file = file;
    }

    public FileFilterWordSet(File file, String encoding){
        validFile(file);
        this.file = file;
        this.encoding = encoding;
    }

    private void validFile(File file) {
        if(!file.exists() || !file.isFile()){throw new IllegalArgumentException("文件不存在，或者不是文件");}
    }

    @Override
    public Set<String> getWordSet(){
        Set<String> set = new HashSet<>();

        InputStreamReader read = null;
        try{
            read = new InputStreamReader(new FileInputStream(file), encoding);
            @SuppressWarnings("resource")
            BufferedReader bufferedReader = new BufferedReader(read);
            String txt = null;
            // 读取文件，将文件内容放入到set中
            while((txt = bufferedReader.readLine()) != null){
                if(!"".equals(txt.trim())){
                    set.add(txt);
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            IoUtil.close(read);
        }
        return set;
    }

}
