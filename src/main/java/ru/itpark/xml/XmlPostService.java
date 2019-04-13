package ru.itpark.xml;

import ru.itpark.entity.Entity;

public class XmlPostService {
    private final XmlRequestClient xmlRequestClient;
    private int id;

    public XmlPostService(XmlRequestClient xmlRequestClient) {
        this.xmlRequestClient = xmlRequestClient;
    }

    //@Value("${id}")
    public void setId(int id) {
        this.id = id;
    }

    public Entity getPost() {
        return xmlRequestClient.getPost(id);
    }
}
