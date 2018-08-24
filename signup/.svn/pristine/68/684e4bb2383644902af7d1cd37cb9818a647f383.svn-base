package com.coolgua.signup.domain;

import java.util.Date;

/**
 * 登录登出库
 * 
 * @author zhanghaonan
 *
 */

public class Log {
	private String id;

	/**
	 * 本次登录的userid
	 */
	private String userId;

	/**
	 * openid 微信用户的标识 使用GET方法访问
	 * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&
	 * openid=OPENID&lang=zh_CN 返回用户基本信息
	 */
	private String openId;
	/**
	 * session_key 秘钥 维护登录状态
	 */
	private String sessionKey;
	/**
	 * 用户状态 0：未登录 1：登录
	 */
	private Integer state;
	/**
	 * 登录时间
	 */
	private Date inDate;
	/**
	 * 登出时间
	 */
	private Date outDate;

	private String inviteOpenId;
	private String eventId;

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Log(String id, String userId, String openId, String sessionKey, Integer state, Date inDate, Date outDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.openId = openId;
		this.sessionKey = sessionKey;
		this.state = state;
		this.inDate = inDate;
		this.outDate = outDate;
	}

	public Log(String userId, String openId, String sessionKey, Integer state, Date inDate, Date outDate,
			String inviteOpenId) {
		super();
		this.userId = userId;
		this.openId = openId;
		this.sessionKey = sessionKey;
		this.state = state;
		this.inDate = inDate;
		this.outDate = outDate;
		this.inviteOpenId = inviteOpenId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getInviteOpenId() {
		return inviteOpenId;
	}

	public void setInviteOpenId(String inviteOpenId) {
		this.inviteOpenId = inviteOpenId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
