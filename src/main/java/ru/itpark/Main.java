package ru.itpark;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import ru.itpark.annotation.AnnotationPostService;
import ru.itpark.java.JavaConfiguration;
import ru.itpark.java.JavaPostService;
import ru.itpark.programmatic.ProgCachedBPP;
import ru.itpark.programmatic.ProgJsonBeanFactoryPostProcessor;
import ru.itpark.programmatic.ProgPostService;
import ru.itpark.programmatic.ProgRequestClient;
import ru.itpark.xml.XmlPostService;

public class Main {

    public static void main(String[] args) {
        {
            var ctx = new ClassPathXmlApplicationContext("beans.xml");
            System.out.println(ctx.getBean(XmlPostService.class).getPost());
            System.out.println(ctx.getBean(XmlPostService.class).getPost());
        }
        {
            var ctx = new AnnotationConfigApplicationContext("ru.itpark.annotation");
            System.out.println(ctx.getBean(AnnotationPostService.class).getPost());
            System.out.println(ctx.getBean(AnnotationPostService.class).getPost());
        }
        {
            var ctx = new AnnotationConfigApplicationContext(JavaConfiguration.class);
            System.out.println(ctx.getBean("postService", JavaPostService.class).getPost());
            System.out.println(ctx.getBean("postService", JavaPostService.class).getPost());
        }

    }

}
