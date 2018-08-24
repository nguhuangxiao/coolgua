package com.coolgua.signup.domain;

import java.util.Date;

import com.coolgua.signup.constant.UserConstant;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Event {

	private String id;
	private String name;
	private String eventTime;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
	private Date registerStartTime;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
	private Date registerEndTime;
	private String country;
	private String province;
	private String city;
	private String address;
	private String banner;
	private String introduce;
	private String color;
	private int orgId;
	private String creator;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String editor;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date editTime;
	private int regNum;
	private String logo;
	private String acode;   // 小程序码
	private String qrcode;  // 小程序二维码
	private String layout;
	private String ticketPrefix; // 门票前缀
	private Integer ticketLength;    // 门票长度

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

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public Date getRegisterStartTime() {
		return registerStartTime;
	}

	public void setRegisterStartTime(Date registerStartTime) {
		this.registerStartTime = registerStartTime;
	}

	public Date getRegisterEndTime() {
		return registerEndTime;
	}

	public void setRegisterEndTime(Date registerEndTime) {
		this.registerEndTime = registerEndTime;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public int getRegNum() {
		return regNum;
	}

	public void setRegNum(int regNum) {
		this.regNum = regNum;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public int getStatus() {
		long now = System.currentTimeMillis();
		long start = registerStartTime == null ? 0 : registerStartTime.getTime();
		long end = registerEndTime == null ? 0 : registerEndTime.getTime();
		if(now < start) {
			return UserConstant.UNSTART;
		} else if(now > end) {
			return UserConstant.FINISHED;
		} else {
			return UserConstant.GOING;
		}
	}
	
	public String getTicketPrefix() {
		return ticketPrefix;
	}

	public void setTicketPrefix(String ticketPrefix) {
		this.ticketPrefix = ticketPrefix;
	}

	public Integer getTicketLength() {
		return ticketLength;
	}

	public void setTicketLength(Integer ticketLength) {
		this.ticketLength = ticketLength;
	}

	@Override
	public String toString() {
		return "Event {id=" + id + ", name=" + name + ", registerStartTime=" + registerStartTime + ", registerEndTime=" + registerEndTime
				+ ", country=" + country + ", province=" + province + ", city=" + city
				+ ", address=" + address + ", banner=" + banner + ", introduce=" + introduce
				+ ", color=" + color + ", orgId=" + orgId + "}";
	}

}
