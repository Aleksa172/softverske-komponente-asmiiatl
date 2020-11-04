package com.komponente.project.models;

import com.komponente.project.AbstractEntity;

import java.util.UUID;

public class Student extends AbstractEntity {

    private static final String ENTITY_NAME = "Student";

    public Student(){
        super(ENTITY_NAME, UUID.randomUUID().toString());
    }

    public Student(String id){
        super(ENTITY_NAME, id);
    }

}
