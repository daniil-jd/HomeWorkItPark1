package ru.itpark;

import com.google.gson.Gson;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public abstract class AbstractJsonBeanFactoryPostProcessor extends PropertyPlaceholderConfigurer {

    private final String JSON_FILE_EXTENSION = ".json";

    @Nullable
    private Resource[] jsonLocations;

    public void setJsonLocation(Resource jsonLocation) {
        this.jsonLocations = new Resource[]{jsonLocation};
    }

    public void setJsonLocations(Resource... jsonLocations) {
        this.jsonLocations = jsonLocations;
    }

    public AbstractJsonBeanFactoryPostProcessor() {
        setJsonLocation(new ClassPathResource("json/connection.json"));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            Properties mergedProps = loadJson();

            // Convert the merged properties, if necessary.
            convertProperties(mergedProps);

            // Let the subclass process the properties.
            processProperties(beanFactory, mergedProps);
        }
        catch (IOException ex) {
            throw new BeanInitializationException("Could not load properties", ex);
        }
    }

    private final Properties loadJson() throws IOException {
        Properties props = null;
        if (jsonLocations != null) {
            for (Resource location : jsonLocations) {
                EncodedResource resource = new EncodedResource(location);
                InputStream stream = null;
                Reader reader = null;
                try {
                    String filename = resource.getResource().getFilename();
                    if (filename != null && filename.endsWith(JSON_FILE_EXTENSION)) {
                        stream = resource.getInputStream();
                        reader = new InputStreamReader(stream, "UTF-8");
                        props = new Gson().fromJson(reader, Properties.class);
                    } else {
                        throw new IOException();
                    }
                }
                finally {
                    if (stream != null) {
                        stream.close();
                    }
                    if (reader != null) {
                        reader.close();
                    }
                }
            }
        }

        return props;
    }

}
