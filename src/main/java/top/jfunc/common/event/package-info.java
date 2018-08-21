/**
 * JFinal消息机制插件
 * 本包为事件分发机制，用于发布某些事件，监听器对自己感兴趣的事件做出处理，支持同步或者异步
 * 使用方式：
 * 1.创建事件：DumpSQLEvent extends ApplicationEvent
        public DumpSQLEvent(DumpSQLBean dumpSQLBean){
            super(dumpSQLBean);
        }
 * 2.创建监听器（DumpSQLEvent基本上就是为传递参数）： public class DumpSQLListener implements ApplicationListener<DumpSQLEvent>{}
        添加@Listener(order = 7, enableAsync = true)
        public void onApplicationEvent(DumpSQLEvent event){
        DumpSQLBean bean = (DumpSQLBean)event.getSource();
 * 3.初始化插件
        EventInitializer eventPlugin = new EventInitializer();
        // 开启全局异步
        eventPlugin.async();
        eventPlugin.addListener(...)
        // 设置扫描jar包，默认不扫描
        // eventPlugin.scanJar();//不用扫描包
        // 设置监听器默认包，默认全扫描
        eventPlugin.scanPackage("cn.esstx.cq.server.util.jevent.listener");
        me.add(eventPlugin);
 *  4.在事件触发的地方调用
 *      EventKit.post(new DumpSQLEvent(new DumpSQLBean(tableName, path + fileName)));
 */
package top.jfunc.common.event;