package com.example.externalclassloader.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Dmitry Itskov
 */
@Service
public class CustomClassLoader extends ClassLoader {

    @Override
    @SneakyThrows
    public Class<?> findClass (String className, String classPath){
        String fileName = classPath.replace('.', File.separatorChar) + ".class";
        byte[] bytecode = Files.newInputStream(Path.of(fileName)).readAllBytes();
        return defineClass(className, bytecode, 0, bytecode.length);
    }
}
