package com.komponente.project;

import java.util.HashMap;

public abstract class AbstractEntity {

    private String entityName;
    private String id;
    private HashMap<String, Object> attributes;

    public AbstractEntity() {
        super();
    }

    public AbstractEntity(String entityName, String id) {
        this.entityName = entityName;
        this.id = id;
        this.attributes = new HashMap<>();
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAttribute(String key, Object aa) {
        this.attributes.put(key, aa);
    }

    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "entityName='" + entityName + '\'' +
                ", id='" + id + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
