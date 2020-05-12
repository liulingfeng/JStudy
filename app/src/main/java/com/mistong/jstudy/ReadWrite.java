package com.mistong.jstudy;

import android.util.Log;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author liuxiaoshuai
 * @date 2020/4/26
 * @desc
 * @email liulingfeng@mistong.com
 */
public class ReadWrite {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static String number = "0";

    static class Reader implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.readLock().lock();
                    Thread.sleep(1000);
                    Log.e("德玛", Thread.currentThread().getName() + "---> Number is " + number);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.readLock().unlock();
                }
            }
        }
    }

    static class Writer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i <= 7; i += 2) {
                try {
                    lock.writeLock().lock();
                    Log.e("德玛", Thread.currentThread().getName() + "正在写入" + i);
                    number = number.concat("" + i);
                } finally {
                    lock.writeLock().unlock();
                }

            }
        }
    }
}
