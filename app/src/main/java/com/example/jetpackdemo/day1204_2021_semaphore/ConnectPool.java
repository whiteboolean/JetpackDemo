package com.example.jetpackdemo.day1204_2021_semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 使用semaphore模拟数据连接池的使用
 *
 * @author dg
 */
public class ConnectPool {

    private static class Conn {
    }

    private final List<Conn> pool = new ArrayList<>(3);
    private final Semaphore semaphore = new Semaphore(3);

    public ConnectPool() {
        pool.add(new Conn());
        pool.add(new Conn());
        pool.add(new Conn());
    }

    /**
     * 请求分配连接
     *】`        
     * @return
     * @throws InterruptedException
     */
    public Conn getConn() throws InterruptedException {
        semaphore.acquire();

        Conn c = null;
        synchronized (pool) {
            c = pool.remove(0);
        }
        System.out.println(Thread.currentThread().getName() + "get a conn " + c);
        return c;
    }

    /**
     * 释放连接
     *
     * @param c 1
     */
    public void release(Conn c) {
        pool.add(c);
        System.out.println(Thread.currentThread().getName() + "release a conn" + c);
        semaphore.release();
    }

    public static void main(String[] args) {
        final ConnectPool pool = new ConnectPool();
        /*
         * 第一个线程占用一个连接池3秒
         */
        new Thread() {
            @Override
            public void run() {
                    try {
                        Conn c = pool.getConn();
                        Thread.sleep(3000);
                        pool.release(c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }.start();

        /*
         * 开启三个线程请求分配连接
         */
        for (int i = 0; i < 3; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        Conn c = pool.getConn();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

}
