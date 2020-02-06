package top.jfunc.common.event;

import top.jfunc.common.event.core.ApplicationEvent;
import top.jfunc.common.event.core.EventType;
import top.jfunc.common.event.core.ListenerHelper;
import top.jfunc.common.utils.MultiValueMap;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

/**
 * 事件工具类
 * 
 * @author L.cm email: 596392912@qq.com site:http://www.dreamlu.net date
 *         2015年4月26日下午9:58:53
 */
public class EventKit{
    private static MultiValueMap<EventType, ListenerHelper> map;
    private static ExecutorService                              pool;

    static void init(MultiValueMap<EventType, ListenerHelper> map, ExecutorService pool){
        EventKit.map = map;
        EventKit.pool = pool;
    }

    /**
     * 发布事件
     * 
     * @param event
     *            ApplicationEvent
     * @since 1.4.0
     */
    public static void post(final ApplicationEvent event){
        post(EventType.DEFAULT_TAG, event);
    }

    /**
     * 发布事件
     * 
     * @param tag
     *            标记
     * @param event
     *            事件
     * @since 1.4.0
     */
    public static void post(final String tag, final ApplicationEvent event){
        Class<?> eventClazz = event.getClass();
        EventType eventType = new EventType(tag, eventClazz);
        post(eventType, event);
    }

    /**
     * 发布事件
     * 
     * @param eventType
     *            事件封装
     */
    @SuppressWarnings("unchecked")
    private static void post(final EventType eventType, final ApplicationEvent event){
        Collection<ListenerHelper> listenerList = map.get(eventType);
        for(final ListenerHelper helper : listenerList){

            if(null != pool && helper.enableAsync){
                pool.execute(()->helper.listener.onApplicationEvent(event));
            } else{
                helper.listener.onApplicationEvent(event);
            }
        }
    }
}
