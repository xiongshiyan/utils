package top.jfunc.common.event.async;

import top.jfunc.common.event.core.ApplicationEvent;

/**
 * 通用异事件，可以将非常耗时的工作放入runnable，调用EventKit.post（AsyncEvent）方法发布
 * @author 熊诗言 2017/11/23
 */
public class AsyncEvent extends ApplicationEvent {
    public AsyncEvent(Runnable runnable){
        super(runnable);
    }
}
