package com.komponente.project.exceptions;

public class EntityIdNotUniqueException extends Exception{
    public EntityIdNotUniqueException(String id) {
        super("Entity ID not unique for collection: "+id);
    }
}
