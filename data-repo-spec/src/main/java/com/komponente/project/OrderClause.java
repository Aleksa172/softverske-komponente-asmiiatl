package com.komponente.project;

public class OrderClause {

    public String key;
    /**
     *  Value can contain any string (for exact match)
     *  or an asterisk at the beginning (for ends-with)
     *  or an asterisk at the end (for begins-with)
     *  or asterisk both at the beginning and end (for contains)
     */
    public String value;

}
