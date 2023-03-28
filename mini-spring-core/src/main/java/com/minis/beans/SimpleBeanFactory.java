package com.minis.beans;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBeanFactory implements BeanFactory{
    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private List<String> beanNames = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();
    public SimpleBeanFactory() {
    }

    //getBean，容器的核心方法
    public Object getBean(String beanName) throws BeansException {
        //先尝试直接拿Bean实例
        Object singleton = singletons.get(beanName);
        //如果此时还没有这个Bean的实例，则获取它的定义来创建实例
        if (singleton == null) {
            int i = beanNames.indexOf(beanName);
            if (i == -1) {
                throw new BeansException("未知异常");
            } else {
                //获取Bean的定义
                BeanDefinition beanDefinition = beanDefinitions.get(i);
                try {
                    singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                //注册Bean实例
                singletons.put(beanDefinition.getId(), singleton);
            }
        }
        return singleton;
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}