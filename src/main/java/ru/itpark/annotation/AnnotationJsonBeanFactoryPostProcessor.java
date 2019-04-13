package ru.itpark.annotation;

import com.google.gson.Gson;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.itpark.AbstractJsonBeanFactoryPostProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

@Component
public class AnnotationJsonBeanFactoryPostProcessor extends AbstractJsonBeanFactoryPostProcessor {

    public AnnotationJsonBeanFactoryPostProcessor() {
        super();
    }

}
