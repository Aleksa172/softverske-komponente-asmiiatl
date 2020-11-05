package com.komponente.project.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.komponente.project.AbstractEntity;

import java.util.List;
import java.util.UUID;

// Razbijanje kruzne reference "prijatelj na prijatelja"
// https://stackoverflow.com/questions/41778386/jackson-bidirectional-relationship-one-to-many-not-working
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student extends AbstractEntity {

    private static final String ENTITY_NAME = "Student";

    public Student(){
        super(ENTITY_NAME, UUID.randomUUID().toString());
    }

    public Student(String id){
        super(ENTITY_NAME, id);
    }

    public Student(String id, String name){
        super(ENTITY_NAME, id);
        this.setName(name);
    }

    public void setName(String ime) {
        this.setAttribute("name", ime);
    }

    public void setDrugari(List<Student> studenti){
        this.setAttribute("friends", studenti);
    }

}
