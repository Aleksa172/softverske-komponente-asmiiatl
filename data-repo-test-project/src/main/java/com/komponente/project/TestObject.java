package com.komponente.project;

import java.util.UUID;

public class TestObject {

	private String id;
	private String property1;
	private String property2;

	public TestObject() {

	}

	public TestObject(String property1, String property2) {
		this.id = UUID.randomUUID().toString();
		this.property1 = property1;
		this.property2 = property2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}

	public String getProperty2() {
		return property2;
	}

	public void setProperty2(String property2) {
		this.property2 = property2;
	}

	@Override
	public String toString() {
		return "TestObject [id=" + id + ", property1=" + property1 + ", property2=" + property2 + "]";
	}

}
