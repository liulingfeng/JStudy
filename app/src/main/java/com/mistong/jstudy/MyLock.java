package com.mistong.jstudy;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuxiaoshuai
 * @date 2020/4/26
 * @desc
 * @email liulingfeng@mistong.com
 */
public class MyLock {
    private Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        //是否独占资源
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }
}
