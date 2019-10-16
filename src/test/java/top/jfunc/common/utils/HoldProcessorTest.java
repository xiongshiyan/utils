package top.jfunc.common.utils;

import top.jfunc.common.thread.HoldProcessor;

/**
 * @author xiongshiyan at 2019/10/16 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class HoldProcessorTest {
    public static void main(String[] args) {
        System.out.println("程序开始");
        HoldProcessor holdProcessor = new HoldProcessor();

        System.out.println("开始等待");
        holdProcessor.startAwait();

        System.out.println("添加shutdown hook");
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("收到kill 信号，停止程序");
            holdProcessor.stopAwait();
        }));
    }
}
