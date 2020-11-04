package com.komponente.project;

public abstract class AbstractAttribute {

    private Object value;

    public AbstractAttribute() {
        super();
    }

    public AbstractAttribute(Object value){
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
