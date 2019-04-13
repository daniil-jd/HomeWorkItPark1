package ru.itpark.java;

import ru.itpark.entity.Entity;

public class JavaPostService {
    private JavaRequestClient requestClient;
    private int id;

    public JavaPostService(JavaRequestClient requestClient, int id) {
        this.requestClient = requestClient;
        this.id = id;
    }

    public Entity getPost() {
        return requestClient.getPost(id);
    }
}
