package com.coolgua.signup.domain;

public class FormField {

	private String id;
	private String eventId;
	private String fieldId;
	private String name;
	private Integer custom;// 定制字段（0：固定字段；1：定义字段）
	private String createTime;
	private String dictionary;
	
	private String english;
	private int typeId;
	private int required;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustom() {
		return custom;
	}

	public void setCustom(Integer custom) {
		this.custom = custom;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDictionary() {
		return dictionary;
	}

	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}
	
	@Override
	public String toString() {
		return "FormField{id=" + id + ", name=" + name + ", fieldId=" + fieldId + ",english=" + english
				 + ",typeId=" + typeId + ",dictionary=" + dictionary + "}";
	}
}
