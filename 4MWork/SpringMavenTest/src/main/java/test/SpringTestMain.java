package test;

import dao3.TestDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestMain {
    public static void main(String[] args)
    {
        ApplicationContext applicationtest=new ClassPathXmlApplicationContext("applicationContext.xml");
        TestDao testdao=(TestDao)applicationtest.getBean("TestDao");
        testdao.sayHello();
    }
}
