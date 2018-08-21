package top.jfunc.common.propenv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.jfunc.common.utils.FileUtil;

import java.io.File;
import java.io.InputStream;

/**
 * 根据环境变量env加载文件(-Denv=test)，从类路径下或者给定文件路径寻找，首先寻找${fileName}-${env}.ext，没找到就寻找找${env}/${fileName}.ext，再没找到就找找${env}/${fileName}.ext。
 * 没有设置env环境变量的时候，给什么路径就从什么路径找
 * @author 熊诗言
 * @see ClasspathEnvStream
 * @see FileEnvStream
 * @see EnvStreamFactory
 */
public abstract class BaseEnvStream {



    private static Logger logger = LoggerFactory.getLogger(BaseEnvStream.class);
    private static String env = EnvUtil.env();

    /**
     * @param fileName 可以是 jdbc.properties 或者 config/jdbc.properties fileName必须带有扩展名
     * @return InputStream
     */
    public  InputStream loadEnvInputStream(String fileName){
        InputStream inputStream = null;
        if(env == null){
            logger.info("env = null , try to load " + fileName);
            inputStream = loadInputStream(fileName);//给什么路径就从什么路径找
            if(inputStream == null){
                throw new IllegalArgumentException("Properties file not found : " + fileName );
            }
        }else {
            String envFileName = FileUtil.getFileNameNoEx(fileName) + "-" + env + "." + FileUtil.getExtensionName(fileName);
            logger.info("env = " + env + " , try to load " + envFileName);
            inputStream = loadInputStream(envFileName);//找${fileName}-${env}.ext
            if (inputStream == null) {
                String envDirName = env + File.separator + fileName;
                logger.info("env = " + env + " , try to load " + envDirName);
                inputStream = loadInputStream(envDirName);//找${env}/${fileName}.ext
                if(inputStream == null){
                    logger.info("env = " + env + " , try to load " + fileName);
                    inputStream = loadInputStream(fileName);//给什么路径就从什么路径找
                    if(inputStream == null){
                        throw new IllegalArgumentException("Properties file not found : " + fileName + ", and " + envFileName + ",and " + envDirName);
                    }
                }

            }
        }
        return  inputStream;
    }

    /**
     * @param fileName 文件名
     * @return 没找到就返回null
     */
    protected abstract InputStream loadInputStream(String fileName);
}
