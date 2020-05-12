package com.mistong.jstudy;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author liuxiaoshuai
 * @date 2020/4/28
 * @desc
 * @email liulingfeng@mistong.com
 */
public class DiskClassLoaderTest {
    @Test
    @SuppressWarnings("unchecked")
    public void testClassLoader() {
        DiskClassLoader diskClassLoader = new DiskClassLoader("file:///Users/liuxiaoshuai/desktop/");
        try {
            Class c = diskClassLoader.findClass("Secret");
            if (c != null) {
                Object obj = c.newInstance();
                Method method = c.getDeclaredMethod("printSecret");
                method.invoke(obj);
            }
        } catch (Exception e) {
            System.out.println("没找到类");
        }
    }
}
