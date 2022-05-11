package test;

import dao3.TestDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    public void demo()
    {
        ApplicationContext applicationtest=new ClassPathXmlApplicationContext("applicationContext.xml");
        TestDao testdao=(TestDao)applicationtest.getBean("TestDao");
        testdao.sayHello();
    }
}
