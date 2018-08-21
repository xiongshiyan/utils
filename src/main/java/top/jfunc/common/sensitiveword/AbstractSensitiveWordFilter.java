package top.jfunc.common.sensitiveword;

/**
 * 统一进行是否初始化的检查
 * @author 熊诗言
 */
public abstract class AbstractSensitiveWordFilter implements SensitiveWordFilter {
    /**
     * 调用初始化方法之后，设置该值为true。
     */
    protected boolean isInit = false;
    /**
     * 检查是否已经初始化了
     */
    public void checkInit(){
        if(!isInit){
            throw new RuntimeException("SensitiveWordFilter没被初始化，使用之前请调用init(Set<String> sensitiveWords)");
        }
    }
}
