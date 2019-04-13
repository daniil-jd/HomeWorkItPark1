package ru.itpark.programmatic;

import org.springframework.beans.factory.annotation.Value;
import ru.itpark.entity.Entity;

public class ProgPostService {
    private ProgRequestClient progRequestClient;
    private int id;

    public ProgPostService(@Value("${id}") int id) {
        this.progRequestClient = new ProgRequestClient();
        this.id = id;
    }

    public Entity getPost() {
        return progRequestClient.getPost(id);
    }
}
