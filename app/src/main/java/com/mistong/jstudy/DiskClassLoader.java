package com.mistong.jstudy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author liuxiaoshuai
 * @date 2020/4/28
 * @desc
 * @email liulingfeng@mistong.com
 */
public class DiskClassLoader extends ClassLoader {
    private String filePath;

    public DiskClassLoader(String path) {
        filePath = path;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String newPath = filePath + name + ".class";
        byte[] classBytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(newPath));
            classBytes = Files.readAllBytes(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        assert classBytes != null;
        return defineClass(name, classBytes, 0, classBytes.length);
    }
}
