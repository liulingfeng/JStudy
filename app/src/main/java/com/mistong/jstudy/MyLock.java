package com.mistong.jstudy;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author liuxiaoshuai
 * @date 2020/4/26
 * @desc
 * @email liulingfeng@mistong.com
 */
public class MyLock {
    private Sync sync = new Sync();

    public void lock() {
        sync.acquire(0);
    }

    public void unlock() {
        sync.release(0);
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
    }
}
