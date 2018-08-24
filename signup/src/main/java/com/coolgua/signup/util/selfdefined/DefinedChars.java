package com.coolgua.signup.util.selfdefined;

import org.springframework.beans.factory.annotation.Value;

/**
 * 自定义的各种字符串
 * 
 * @author zhanghaonan
 *
 */
public class DefinedChars {
	

	
	/**
	 * 用于获取用户openid的接口网址
	 * https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&
	 * js_code=JSCODE&grant_type=authorization_code
	 */
	public static String Web_access_tokenhttps = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

	/**
	 * 生成获取用户openid的接口的字符串
	 * 
	 * @param code
	 * @return
	 */
	public static String getWebAccess(String code,String appId,String secret) {
		return String.format(Web_access_tokenhttps, appId, secret, code);
	}

}
