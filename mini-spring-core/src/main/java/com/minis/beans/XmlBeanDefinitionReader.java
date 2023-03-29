package com.minis.beans;


import com.minis.core.Resource;
import org.dom4j.Element;

public class XmlBeanDefinitionReader {
    BeanFactory beanFactory = new SimpleBeanFactory();
    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        //wml：这里一定注意， this.beanFactory 和 beanFactory是一个对象，可以打印2个的地址看出来，他们的地址是同一个。
        this.beanFactory = beanFactory;
    }
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            this.beanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}