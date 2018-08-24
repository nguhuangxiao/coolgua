package com.coolgua.signup.domain;

public class Province {

	private String id;
	private String name;
	private String english;
	private String pinyin;
	private String pinyinShort;

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

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getPinyinShort() {
		return pinyinShort;
	}

	public void setPinyinShort(String pinyinShort) {
		this.pinyinShort = pinyinShort;
	}

}
