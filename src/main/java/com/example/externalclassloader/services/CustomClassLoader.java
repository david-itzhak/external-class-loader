package com.example.externalclassloader.services;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Dmitry Itskov
 */
@Service
@Log4j2
public class CustomClassLoader {

    @SneakyThrows
    public Class<?> findClass(String jarPath, String fullClassName) {
        URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        MyClassLoader l = new MyClassLoader(loader.getURLs());
        l.addURL(new URL("file:" + jarPath));
        Class<?> clazz = l.loadClass(fullClassName);
//        Class c = Class.forName(fullClassName, true, l); // This is an alternative variant to load a class (it can replace the previous line).
        log.info("Class {} is loaded", clazz.getName());
        return clazz;
    }
}

class MyClassLoader extends URLClassLoader {
    public MyClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }
}
