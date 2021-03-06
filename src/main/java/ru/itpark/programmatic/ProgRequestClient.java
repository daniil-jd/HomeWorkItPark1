package ru.itpark.programmatic;

import ru.itpark.RequestClient;
import ru.itpark.entity.Entity;
import ru.itpark.util.Cached;

import java.util.Optional;

public class ProgRequestClient extends RequestClient {

    @Cached
    @Override
    public Entity getPost(int id) {
        if (entities == null) {
            loadJson();
        }

        Optional<Entity> result = entities.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst();

        return result.get();
    }
}
