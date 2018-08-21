package top.jfunc.common.event.async;

import top.jfunc.common.event.core.ApplicationListener;
import top.jfunc.common.event.core.EventType;
import top.jfunc.common.event.core.ListenerAttr;

/**
 * @author 熊诗言
 * 通用异步事件监听器
 */
public class AsyncListener implements ApplicationListener<AsyncEvent>,ListenerAttr {
    @Override
    public void onApplicationEvent(AsyncEvent event) {
        Runnable runnable = (Runnable)event.getSource();
        if(null != runnable){
            runnable.run();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public boolean getEnableAsync() {
        return true;
    }

    @Override
    public String getTag() {
        return EventType.DEFAULT_TAG;
    }
}
