package com.minis;

import com.minis.beans.*;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

public class ClassPathXmlApplicationContext implements BeanFactory{
    BeanFactory beanFactory;
    //context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
    public ClassPathXmlApplicationContext(String fileName) {
        //1、获取xml里的bean内容，这时候还没有转成类
        Resource resource = new ClassPathXmlResource(fileName);
        //2、生成一个bean工厂
        SimpleBeanFactory beanFactory = new SimpleBeanFactory();
        //3、定义一个阅读器，【注意】：这里的bean工厂被赋值到了reader的成员变量里面了，是同一个对象了（从toString就能看出来）
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //4、阅读器阅读resource中的资源，对上面beanFactory对象进行赋值
        reader.loadBeanDefinitions(resource);
        //5、对自己的beanFactory成员变量赋值
        this.beanFactory = beanFactory;
    }
    @Override
    //context再对外提供一个getBean，底下就是调用的BeanFactory对应的方法
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }
    @Override
    public Boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }
    @Override
    public void registerBean(String beanName, Object obj) {
        this.beanFactory.registerBean(beanName, obj);
    }
}
