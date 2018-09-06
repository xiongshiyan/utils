package top.jfunc.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.jfunc.common.event.core.*;
import top.jfunc.common.utils.ArrayListMultimap;
import top.jfunc.common.utils.BeanUtil;
import top.jfunc.common.utils.ClassUtil;
import top.jfunc.common.utils.ThreadFactoryBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


/**
 * 模拟spring的消息机制插件
 * 初始化器，在JFinal环境中可以用一个类实现IPlugin，并委托给此类完成初始化
 * @author L.cm email: 596392912@qq.com site:http://www.dreamlu.net date
 *         2015年4月26日下午10:25:04
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EventInitializer {
    private static final Logger                                 log         = LoggerFactory.getLogger(EventInitializer.class);

    /** 线程池 */
    private static ExecutorService                              pool        = null;
    /** 重复key的map，使用监听的type，取出所有的监听器 */
    private static ArrayListMultimap<EventType, ListenerHelper> map         = null;
    /** 默认不扫描jar包 */
    private boolean                                             scanJar     = false;
    /** 默认扫描所有的包 */
    private String                                              scanPackage = "";

    /** 所有的Listener，包括主动添加的，也包括扫描的 */
    private List<ApplicationListener> listeners = new ArrayList<>();

    /** 构造EventPlugin */
    public EventInitializer(){}

    /**
     * 构造EventPlugin
     * 
     * @param scanJar
     *            是否扫描jar
     * @param scanPackage
     *            扫描的包名
     * @param asyncThreads
     *            异步的线程池的大小，不传、小于或者等于0时不开启
     */
    public EventInitializer(boolean scanJar, String scanPackage, int... asyncThreads){
        this.scanJar = scanJar;
        this.scanPackage = scanPackage;
        if(asyncThreads.length > 0 && asyncThreads[0] > 0){
            async(asyncThreads);
        }
    }

    /**
     * 异步，默认创建5个线程
     * @param nThreads 线程池的容量，不传或小于1时默认为5
     * @return EventInitializer
     */
    public EventInitializer async(int... nThreads){
        ThreadFactory factory = ThreadFactoryBuilder.create().setNameFormat("event-pool-%d").setDaemon(true).build();
        pool = new ThreadPoolExecutor(nThreads.length == 0 || nThreads[0] < 1 ? 5 : nThreads[0],
                100, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(512), factory, new ThreadPoolExecutor.CallerRunsPolicy());
        return this;
    }

    /**
     * 从jar包中搜索监听器
     * @return EventInitializer
     */
    public EventInitializer scanJar(){
        this.scanJar = true;
        return this;
    }

    public EventInitializer addListener(ApplicationListener listener){
        this.listeners.add(listener);
        return this;
    }
    /**
     * 指定扫描的包
     * 
     * @param scanPackage
     *            指定扫描的包
     * @return EventInitializer
     */
    public EventInitializer scanPackage(String scanPackage){
        this.scanPackage = scanPackage;
        return this;
    }

    public boolean start(){
        create();
        EventKit.init(map, pool);
        return true;
    }

    /**
     * 构造
     */
    private void create(){
        if(null != map){
            return;
        }
        // 扫描注解 {@code Listener}
        //Set<Class<?>> clazzSet = ClassUtil.scanPackageByAnnotation(scanPackage, scanJar, Listener.class);

        // 扫描 {@code ApplicationListener}
        /**既可以用注解Listener，又可以实现ListenerAtrr*/
        Set<Class<?>> clazzSet = ClassUtil.scanPackageBySuper(scanPackage, scanJar, ApplicationListener.class);
        if(clazzSet.isEmpty()){
            log.info("annotation Listener is empty");
        }

        // 装载所有 {@code ApplicationListener} 的子类
        Class superClass = ApplicationListener.class;
        for(Class<?> clazz : clazzSet){
            if(superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)){
                // 实例化监听器
                ApplicationListener listener = BeanUtil.newInstance(clazz);
                listeners.add(listener);
            }
        }
        if(listeners.isEmpty()){
            log.error("Listener is empty! Please check @Listener is right? or invoke addListener()");
            return;
        }

        // 监听器排序
        sortListeners(listeners);

        // 重复key的map，使用监听的type，取出所有的监听器
        map = new ArrayListMultimap<>();

        for(ApplicationListener listener : listeners){

            Class clazz = listener.getClass();
            boolean enableAsync = false;
            String tag = EventType.DEFAULT_TAG;
            if(clazz.isAnnotationPresent(Listener.class)){
                // 监听器上的注解
                enableAsync = ((Listener)(clazz.getAnnotation(Listener.class))).enableAsync();
                tag = ((Listener)(clazz.getAnnotation(Listener.class))).tag();
            }else if(listener instanceof ListenerAttr){
                //从接口来
                enableAsync = ((ListenerAttr) listener).getEnableAsync();
                tag = ((ListenerAttr) listener).getTag();
            }

            // 获取监听器上的泛型信息
            Type type = ((ParameterizedType)clazz.getGenericInterfaces()[0]).getActualTypeArguments()[0];
            EventType eventType = new EventType(tag, type);
            map.put(eventType, new ListenerHelper(listener, enableAsync));

        }
        StringBuilder builder = new StringBuilder("一共有 ").append(listeners.size()).append(" 个监听器:\r\n");
        listeners.forEach(listener -> builder.append(listener).append("\r\n"));
        log.info(builder.toString());
    }

    /**
     * 对所有的监听器进行排序
     */
    private void sortListeners(List<ApplicationListener> listeners){
        Collections.sort(listeners, (l1,l2)->{
            Class l1Class = l1.getClass();
            Class l2Class = l2.getClass();
            int x = Integer.MAX_VALUE;
            int y = Integer.MAX_VALUE;
            if(l1Class.isAnnotationPresent(Listener.class)){
                x = ((Listener)(l1Class.getAnnotation(Listener.class))).order();
            }else if(l1 instanceof ListenerAttr){
                x = ((ListenerAttr) l1).getOrder();
            }
            if(l2Class.isAnnotationPresent(Listener.class)){
                y = ((Listener)(l2Class.getAnnotation(Listener.class))).order();
            }else if(l2 instanceof ListenerAttr){
                y = ((ListenerAttr) l2).getOrder();
            }
            return Integer.compare(x,y);
        });
    }

    public boolean stop(){
        if(null != pool){
            pool.shutdown();
            pool = null;
        }
        if(null != map){
            map.clear();
            map = null;
        }
        if(listeners != null){
            listeners.clear();
            listeners = null;
        }
        return true;
    }

}
