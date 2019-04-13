package ru.itpark.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itpark.entity.Entity;

@Component("postService")
public class AnnotationPostService {
    private final AnnotationRequestClient annotationRequestClient;
    private int id;

    public AnnotationPostService(AnnotationRequestClient annotationRequestClient, @Value("${id}")int id) {
        this.annotationRequestClient = annotationRequestClient;
        this.id = id;
    }

    public Entity getPost() {
        return annotationRequestClient.getPost(id);
    }

}
