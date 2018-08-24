package com.coolgua.signup.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldConstant {

	public static final String NAME = "name";
	public static final String NAME_NAME = "姓名";
	public static final String COMPANY = "company";
	public static final String COMPANY_NAME = "公司";
	public static final String CELLPHONE = "cellphone";
	public static final String CELLPHONE_NAME = "手机";
	public static final String EMAIL = "email";
	public static final String EMAIL_NAME = "邮箱";
	public static final String COUNTRY = "country";
	public static final String COUNTRY_NAME = "国家";
	public static final String PROVINCE = "province";
	public static final String PROVINCE_NAME = "省份";
	public static final String CITY = "city";
	public static final String CITY_NAME = "城市";
	public static final String ADDRESS = "address";
	public static final String ADDRESS_NAME = "地址";

	public static final Integer STRING = 0;
	public static final Integer TEXT = 1;
	public static final Integer IMAGE = 2;
	public static final Integer URL = 3;
	public static final Integer RADIO = 4;
	public static final Integer CHECKBOX = 5;
	public static final Integer ATTACHMENT = 6;

	public static Map<Integer, String> fieldType = new HashMap<Integer, String>();
	public static Map<Integer, Integer> fieldLength = new HashMap<Integer, Integer>();

	static {
		fieldType.put(STRING, "单行文本");
		fieldType.put(TEXT, "多行文本");
		fieldType.put(IMAGE, "图像");
		fieldType.put(URL, "链接");
		fieldType.put(RADIO, "单选框");
		fieldType.put(CHECKBOX, "多选框");
		fieldType.put(ATTACHMENT, "附件");
	}
	
	static {
		fieldLength.put(STRING, 250);
		fieldLength.put(TEXT, -1);
		fieldLength.put(IMAGE, 100);
		fieldLength.put(URL, 100);
		fieldLength.put(RADIO, 100);
		fieldLength.put(CHECKBOX, 250);
		fieldLength.put(ATTACHMENT, 100);
	}
	
	public static int getLength(int typeId) {
		return fieldLength.get(typeId);
	}
	
	public static List<String> definedFields = new ArrayList<String>();
	static {
		definedFields.add("cellphone");
		definedFields.add("name");
		definedFields.add("company");
		definedFields.add("department");
		definedFields.add("position");
		definedFields.add("address");
		definedFields.add("email");
		definedFields.add("telephone");
		definedFields.add("sex");
		definedFields.add("country");
		definedFields.add("province");
		definedFields.add("city");
		definedFields.add("photo");
	}

}
