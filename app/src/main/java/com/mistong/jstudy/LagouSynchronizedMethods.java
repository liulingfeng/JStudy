package com.mistong.jstudy;

import android.util.Log;

/**
 * @author liuxiaoshuai
 * @date 2020/4/26
 * @desc
 * @email liulingfeng@mistong.com
 */
public class LagouSynchronizedMethods {
    private int sum = 0;
    private final Object lock = new Object();

    public synchronized void calculate() {
        sum += 1;
    }

    public synchronized void printLog() {
        try {
            for (int i = 0; i < 5; i++) {
                Log.e("德玛", Thread.currentThread().getName() + "is printing" + i);
                Thread.sleep(300);
            }
        } catch (Exception ignored){

        }
    }

    public void printLog2(){
        synchronized (lock){
            for (int i = 0; i < 5; i++) {
                Log.e("德玛", Thread.currentThread().getName() + "is printing" + i);
            }
        }
    }
}
