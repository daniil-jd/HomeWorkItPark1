package ru.itpark;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import ru.itpark.entity.Entity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public abstract class RequestClient {

    protected Resource json;
    protected List<Entity> entities;

    public RequestClient() {
        setJson(new ClassPathResource("json/posts.json"));
    }

    public void setJson(Resource json) {
        this.json = json;
    }

    public void loadJson() {
        try {
            EncodedResource resource = new EncodedResource(json);
            String filename = resource.getResource().getFilename();
            if (filename != null) {
                Reader reader = new InputStreamReader(resource.getInputStream(), "UTF-8");
                Type listType = new TypeToken<List<Entity>>() {}.getType();
                entities = new Gson().fromJson(reader, listType);
            } else {
                throw new IOException("Не найден post.json");
            }
        } catch (IOException e) {
            new Exception(e.getMessage());
        }
    }

    public abstract Entity getPost(int id);

}
