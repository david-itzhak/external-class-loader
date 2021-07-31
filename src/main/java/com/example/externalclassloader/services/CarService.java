package com.example.externalclassloader.services;

import com.example.externalclassloader.dto.request.CarTuningOrder;
import com.example.externalclassloader.dto.response.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Itskov
 */
@Service
public class CarService {

    @Autowired
    private GenericApplicationContext context;

    private Map<String, CarMaster> masters = new HashMap<>();

    public void register(String type, CarMaster master) {
        masters.put(type, master);
    }

    @Autowired
    private CustomClassLoader loader;

    public Action tuneCar(CarTuningOrder order) {
        if (order.getJarPath() != null && !masters.containsKey(order.getAction())) {
            Class<?> beanClass = loader.findClass(order.getJarPath(), order.getClassName());
            BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) context.getBeanFactory();
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
            beanDefinition.setBeanClass(beanClass);
            beanFactory.registerBeanDefinition(order.getAction(), beanDefinition);
            context.getBean(order.getAction());
        }
        System.out.println("Received an order: " + order);
        return masters.getOrDefault(order.getAction(), masters.get("boss")).doCarTuning(order);
    }
}
