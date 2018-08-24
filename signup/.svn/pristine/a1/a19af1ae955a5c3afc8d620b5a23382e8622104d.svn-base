package com.coolgua.signup.util;

import java.util.Random;

public class RandomUtil {

	private static String EMPTY = "";
	private static String STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	public static final String RANDOM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String random() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			int rand = (int) (Math.random() * RANDOM.length());
			sb.append(RANDOM.charAt(rand));
		}
		sb.append("_");
		return sb.toString();
	}
	
	/**
	 * 获取长度为size的随机字符串
	 * @param size, 字符串长度
	 * @return
	 */
	public static String getString(int size){
		return getString(STR, size);
	}

	/**
	 * 从str中获取长度为size的随机字符串
	 * @param str, 从str取值
	 * @param size, 字符串长度
	 * @return
	 */
	private static String getString(String str, int size){
		if(size <= 0) {
			return EMPTY;
		}
		
		StringBuilder s = new StringBuilder();
		Random random = new Random();
		int len = str.length();
		for (int i = 0; i < size; i++) {
			int b = random.nextInt(len);
			s.append(str.charAt(b));
		}
		return s.toString();
	}

}
