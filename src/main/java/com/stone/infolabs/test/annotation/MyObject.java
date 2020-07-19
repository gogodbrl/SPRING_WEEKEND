package com.stone.infolabs.test.annotation;

public class MyObject {
	@StringInjector("My Name is jakim")
	private String name;
	
	@StringInjector
	private String defaultValue;
	
	@StringInjector
	private Integer invalidType;

	public String getName() {
		return name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public Integer getInvalidType() {
		return invalidType;
	}
}
