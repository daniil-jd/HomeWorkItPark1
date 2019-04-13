package ru.itpark.java;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itpark.CachedAnnotationBPP;

@Configuration
public class JavaConfiguration {

    @Bean
    public static JavaJsonBeanFactoryPostProcessor jsonBeanFactoryPostProcessor() {
        return new JavaJsonBeanFactoryPostProcessor();
    }

    @Bean
    public JavaRequestClient requestClient() {
        return new JavaRequestClient();
    }

    @Bean
    public JavaPostService postService(@Value("${id}") int id) {
        return new JavaPostService(requestClient(), id);
    }

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new CachedAnnotationBPP();
    }

}
