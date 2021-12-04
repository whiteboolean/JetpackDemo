package com.example.jetpackdemo.day1204_2021_semaphore;

import java.util.concurrent.Semaphore;

/**
 *介绍：Semaphore中管理着一组虚拟的许可，许可的初始数量可通过构造函数来指定【new Semaphore(1);】，
 * 执行操作时可以首先获得许可【semaphore.acquire();】，并在使用后释放许可【semaphore.release();】。如果没有许可，那么acquire方法将会一直阻塞直到有许可（或者直到被终端或者操作超时）
 * ————————————————
 * 作用：可以用来控制同时访问某个资源的操作数量，活着某个操作的数量
 *
 *
 */
public class MutexPrint {


    /**
     * 定义一个初始值为1的信号量
     */
    private final Semaphore semaphore = new Semaphore(1);


    /**
     *
     */
    public void print(String str) throws InterruptedException{
        //请求许可
        semaphore.acquire();

//        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " enter ....");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "正在打印 ..." + str);
            System.out.println(Thread.currentThread().getName()+" out ...");
            System.out.println();
//        }

        //释放许可
        semaphore.release();
    }

    public static void main(String[] args) {
        MutexPrint print = new MutexPrint();
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        print.print("helloWorld");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
