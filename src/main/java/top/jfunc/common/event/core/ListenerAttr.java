package top.jfunc.common.event.core;

/**
 * @author 熊诗言
 * @description Listener注解的替代品，一个Listener要么实现该接口，要么用注解标注，后者优先
 * @date 2017/11/23
 */
public interface ListenerAttr {
    /**
     * 监听器顺序 默认为Integer.MAX
     * @return
     */
    int getOrder();

    /**
     * 监听器是否异步执行 默认为否
     * @return
     */
    boolean getEnableAsync();
    /**
     * 标签 默认为 EventType.DEFAULT_TAG
     * @return
     */
    String getTag();
}
