package top.jfunc.common.utils.local;

/**
 * 在同一个线程保存信息的工具类
 * @author xiongshiyan at 2017/12/27
 */
public class InfoHolder<T> {

    protected ThreadLocal<T> holder = new ThreadLocal<>();

    /**
     * 往当前线程添加
     * @param t t
     */
    public void set(T t){
        holder.set(t);
    }


    /**
     * 获取当前线程的
     */
    public T get(){
        return holder.get();
    }

    /**
     * 清空
     */
    public void clear(){
        holder.remove();
    }
}
