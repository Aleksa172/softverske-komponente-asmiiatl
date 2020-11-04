package com.komponente.project;

import java.util.HashMap;

public abstract class AbstractEntity {

    private String entityName;
    private String id;
    private HashMap<String, AbstractAttribute> attributes;

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

    public void setAttribute(String key, AbstractAttribute aa) {
        this.attributes.put(key, aa);
    }

    public AbstractAttribute getAttribute(String key) {
        return this.attributes.get(key);
    }

    public HashMap<String, AbstractAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, AbstractAttribute> attributes) {
        this.attributes = attributes;
    }
}
