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

    private MyClassLoader myClassLoader;

    @SneakyThrows
    public Class<?> findClass(String jarPath, String fullClassName) {
        if(myClassLoader == null){
            myClassLoader = new MyClassLoader();
        }
        myClassLoader.addURL(new URL("file:" + jarPath));
        Class<?> clazz = myClassLoader.loadClass(fullClassName);
//        myClassLoader.close(); // this method releases resources. But it cant be used if a target class hase dependencies on other classes.
//        Class clazz = Class.forName(fullClassName, true, myClassLoader); // This is an alternative variant to load a class (it can replace the previous line).
        log.info("Class {} is loaded", clazz.getName());
        return clazz;
    }
}

class MyClassLoader extends URLClassLoader {

    private static final URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();

    public MyClassLoader() {
        super(loader.getURLs());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }
}
