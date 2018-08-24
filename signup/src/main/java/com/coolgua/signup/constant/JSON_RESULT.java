package com.coolgua.signup.constant;

public class JSON_RESULT {
	/**
	 * 成功
	 */
	public static int OK = 200;

	/**
	 * 客户端请求无效
	 */
	public static int BAD_REQUEST = 400;

	/**
	 * token失效或非法，需要重新登录
	 */
	public static int NEED_LOGIN = 401;

	/**
	 * 普通失败，message中带原因
	 */
	public static int FAILED = 403;

	/**
	 * 未找到请求页面
	 */
	public static int NOT_FOUND = 404;

	/**
	 * 服务器错误
	 */
	public static int SERVER_ERROR = 500;
	

	public static final String MSG = "msg";
	public static final String CODE = "code";
	public static final String SUCCESS = "success";

	public static int DEFAULT_SIZE = 20;
}
