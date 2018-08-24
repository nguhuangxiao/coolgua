package com.coolgua.signup.domain;

public class Layout {
	private String id;
	private String name;
	private String english;
	private int custom;
	private int typeId;
//	private int length;
	private String dictionary;
	private int required;
	private String fieldId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public int getCustom() {
		return custom;
	}

	public void setCustom(int custom) {
		this.custom = custom;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
/*
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
*/
	public String getDictionary() {
		return dictionary;
	}

	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

}
