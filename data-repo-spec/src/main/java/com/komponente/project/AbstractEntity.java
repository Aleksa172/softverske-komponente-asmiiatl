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


    /**
     * Gets entity name
     * @return String name of the entity
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Set a new name for the entity
     * @param entityName new name of the entity
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * Get entity id
     * @return String id of the entity
     */
    public String getId() {
        return id;
    }

    /**
     * Set id of the entity
     * @param id of the entity
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set attribute of the entity
     * @param key - attribute key to update or add
     * @param value  - value for attribute key
     */
    public void setAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }

    /**
     * Get attribute value of entity
     * @param key - attribute key that needs to be obtained
     * @return String value for the attribute
     */
    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    /**
     * Get all attributes for an entity
     * @return HashMap of key-value pairs that the entity has
     */
    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * Set all attributes for an entity
     * @param attributes - HashMap of key-value pairs that the entity should have
     */
    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }


    /**
     * Generic toString representation of AbstractEntity
     * @return String representation of entity
     */
    @Override
    public String toString() {
        return "AbstractEntity{" +
                "entityName='" + entityName + '\'' +
                ", id='" + id + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
