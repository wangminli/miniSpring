package com.minis.test;


import com.minis.ClassPathXmlApplicationContext;
import com.minis.beans.BeansException;

public class Test1 {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext ctx = new
                ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService)ctx.getBean("aservice");
        aService.sayHello();
    }
}
