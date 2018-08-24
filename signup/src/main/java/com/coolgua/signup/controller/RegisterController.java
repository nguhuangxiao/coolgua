package com.coolgua.signup.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.coolgua.signup.constant.UserConstant;
import com.coolgua.signup.domain.Event;
import com.coolgua.signup.domain.SysUserInfoEntity;
import com.coolgua.signup.domain.Tickets;
import com.coolgua.signup.service.EventService;
import com.coolgua.signup.service.SequenceService;
import com.coolgua.signup.service.SignService;
import com.coolgua.signup.service.SysUserInfoService;
import com.coolgua.signup.service.TicketsService;
import com.coolgua.signup.util.CryptUtil;
import com.coolgua.signup.util.DateUtil;
import com.coolgua.signup.util.HttpClientUtil;
import com.coolgua.signup.util.HttpsUtil;
import com.coolgua.signup.util.NumberUtil;
import com.coolgua.signup.util.QrcodeUtil;
import com.coolgua.signup.util.RandomUtil;
import com.coolgua.signup.util.SmsUtil;
import com.coolgua.signup.util.UserInfoUtil;
import com.coolgua.signup.util.image.ImageUtils;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {
	private static List<String> tokens = new ArrayList<String>();
	private static Map<Long, String> requestTimes = new LinkedHashMap<Long, String>();
	private static Map<String, Long> createTime = new HashMap<String, Long>();
	private static Map<String, Long> smsMap = new HashMap<String, Long>();
	
	@Autowired
	Environment environment;
	@Autowired
	SignService signService;
	@Autowired
	SysUserInfoService sysUserInfoService;
	@Autowired
	TicketsService ticketService;

	@Autowired
	EventService eventService;
	
	@Autowired
	SequenceService sequenceService;

	public static final String SEMICOLON = ";";// 分号
	protected static final long short_valid_interval = 2000L;

	@Value("${XIAOGUA_URL}")
	private String xiaoguaUrl;

	@Value("${UPLOAD_DIR}")
	private String uploadDir;

	@ResponseBody
	@RequestMapping(value = "/phone")
	public Map<String, Object> smsMessage(@RequestParam(name = "phone") String phone) {
		System.out.println("》》》收到请求，请求数据为[ phone：" + phone + "]");
		Map<String, Object> res = new HashMap<String, Object>();
		if(phone == null || phone.trim().length() == 0) {
			res.put("msg", "手机号不正确");
			return res;
		}
		Long lastSendTime = smsMap.get(phone);
		if(lastSendTime != null && System.currentTimeMillis() - lastSendTime <= 120000) {
			res.put("msg", "发送验证码过于频繁，请两分钟后再试");
			smsMap.put(phone, System.currentTimeMillis());
			return res;
		}
		
		String ossServerUrl = environment.getProperty("ossServerUrl");
		String smsServerUrl = environment.getProperty("smsServerUrl");
		String smspassword = environment.getProperty("smspassword");
		String smsaccount = environment.getProperty("smsaccount");
		StringBuffer sb = new StringBuffer();
		try {
			sb.append(smsaccount).append(",").append(smspassword);
			String encryptAES = CryptUtil.getInstance().encryptAES(sb.toString());
			String url = ossServerUrl + "/coolguaOpenApi/getClientBycondition.action?v=" + encryptAES;
			String json = HttpClientUtil.doGet(url);
			JSONObject obj = JSONObject.parseObject(json);

			Boolean success = (Boolean) obj.get("success");
			System.out.println("》》》判断获取请求是否成功《《《");
			if (success) {
				Map<String, Object> clientObj = (Map<String, Object>) obj.get("data");
				String orgId = clientObj.get("id").toString();
				String encryptTaskId = CryptUtil.getInstance().encryptAES(orgId + ",1");
				String smsServerUrl2 = smsServerUrl + "/msgs/getTaskById.json?id=" + encryptTaskId;
				String json2 = HttpClientUtil.doGet(smsServerUrl2);

				JSONObject obj2 = JSONObject.parseObject(json2);
				Boolean bool2 = (Boolean) obj2.get("success");
				if (bool2) {
					Map<String, Object> task = (Map<String, Object>) obj2.get("data");
					List<String> replaceFields = (List<String>) task.get("replaceField");
					if (CollectionUtils.isEmpty(replaceFields)) {
						replaceFields = new ArrayList<String>();
					}
					System.out.println("》》》创建验证码《《《");
					replaceFields.add(task.get("mobileField").toString());
					List<String> verifyCodeFileds = new ArrayList<String>();
					verifyCodeFileds.add("手机");
					verifyCodeFileds.add("验证码");
					Map<String, String> taskObj = new HashMap<String, String>();
					for (String display : replaceFields) {
						if (display.equals("手机")) {
							taskObj.put(display, phone);
						} else if (display.equals("验证码")) {
							String random = SmsUtil.createRandom(true, 6);
							taskObj.put(display, random);
						}
					}
					Map<String, String> params = new HashMap<String, String>();
					String smsServerUrl3 = smsServerUrl + "/msgs/sendInstantSms.json";
					System.out.println("smsServerUrl3>>>>" + smsServerUrl3);
					params.put("id", encryptTaskId);
					System.out.println("encryptTaskId" + encryptTaskId);
					JSONObject obj3 = null;

					Boolean bool3 = true;
					try {
						System.out.println("》》》生成验证码《《《");
						JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(taskObj));
						System.out.println("jsonObject.toString()>>>>>" + jsonObject.toString());
						params.put("content", jsonObject.toString());
						String json3 = HttpClientUtil.doPost(smsServerUrl3, params);
						System.out.println("json3>>>>" + json3);
						obj3 = JSONObject.parseObject(json3);
						bool3 = (Boolean) obj3.get("success");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("》》》发送验证码《《《");
					if (bool3) {
						smsMap.put(phone, System.currentTimeMillis());
						res.put("success", true);
					} else {
						res.put("msg", obj3.get("msg"));
					}
				} else {
					res.put("msg", obj2.get("msg"));
				}
			} else {
				res.put("msg", obj.get("msg"));
			}
		} catch (Exception e) {
			res.put("msg", "SMS Platform Exception");
			e.printStackTrace();
		}

		String token = createToken(short_valid_interval, phone);
		res.put("token", token);
		System.out.println("》》》请求数据结束，请求数据为[ phone：" + phone + "]");
		return res;
	}

	private String createToken(long myready, String cellphone) {
		String random = SmsUtil.createRandom(false, 6);
		long timestamp = System.currentTimeMillis() + myready;
		StringBuilder sb = new StringBuilder();
		sb.append(random).append(RegisterController.SEMICOLON).append(timestamp);
		String token = CryptUtil.getInstance().encryptAES(sb.toString());
		addToken(token, cellphone);
		return token;
	}

	public static synchronized boolean addToken(String token, String account) {
		try {
			tokens.add(token);
			if (StringUtils.isNotBlank(account)) {
				createTime.put(account, System.currentTimeMillis());
				requestTimes.put(System.currentTimeMillis(), account);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@ResponseBody
	@RequestMapping(value = "/verify")
	public Map<String, Object> verifySms(@RequestParam(name = "phone") String phone,
			@RequestParam(name = "codeNum") String codeNum) {
		JSONObject result = new JSONObject();
		result.put("success", false);
		if (StringUtils.isBlank(phone)) {
			result.put("msg", "发送云短信手机号不能为空");
		}
		if (StringUtils.isBlank(codeNum)) {
			result.put("msg", "验证码不能为空");
		}
		System.out.println("》》》进入验证码校验《《《");
		String ossServerUrl = environment.getProperty("ossServerUrl");
		String smsServerUrl = environment.getProperty("smsServerUrl");
		try {
			if (StringUtils.isBlank(smsServerUrl) || StringUtils.isBlank(ossServerUrl)) {
				result.put("msg", "请先配置相关参数");
			}
			String smspassword = environment.getProperty("smspassword");
			String smsaccount = environment.getProperty("smsaccount");
			StringBuffer sb = new StringBuffer();
			sb.append(smsaccount).append(",").append(smspassword);
			String encryptAES = CryptUtil.getInstance().encryptAES(sb.toString());
			String url = ossServerUrl + "/coolguaOpenApi/getClientBycondition.action?v=" + encryptAES;
			String json = HttpClientUtil.doGet(url);
			JSONObject obj = JSONObject.parseObject(json);
			Boolean success = (Boolean) obj.get("success");
			System.out.println("》》》验证码开始校验《《《");
			if (success) {

				Map<String, Object> clientObj = (Map<String, Object>) obj.get("data");
				String orgId = clientObj.get("id").toString();
				String encryptTaskId = CryptUtil.getInstance().encryptAES(orgId + ",1");
				String smsServerUrl2 = smsServerUrl + "/msgs/getTaskById.json?id=" + encryptTaskId;
				String json2 = HttpClientUtil.doGet(smsServerUrl2);
				JSONObject obj2 = JSONObject.parseObject(json2);
				Boolean bool2 = (Boolean) obj2.get("success");
				if (bool2) {
					Map<String, Object> task = (Map<String, Object>) obj2.get("data");
					List<String> replaceFields = (List<String>) task.get("replaceField");
					if (CollectionUtils.isEmpty(replaceFields)) {
						replaceFields = new ArrayList<String>();
					}
					replaceFields.add(task.get("mobileField").toString());
					List<String> verifyCodeFileds = new ArrayList<String>();
					verifyCodeFileds.add("手机");
					verifyCodeFileds.add("验证码");
					Map<String, String> taskObj = new HashMap<String, String>();
					for (String display : replaceFields) {
						if (display.equals("手机")) {
							taskObj.put(display, phone);
						} else if (display.equals("验证码")) {
							taskObj.put(display, codeNum);
						}
					}

					Map<String, String> params = new HashMap<String, String>();
					String smsServerUrl3 = smsServerUrl + "/msgs/verifyCode.json";
					params.put("id", encryptTaskId);
					JSONObject obj3 = null;
					Boolean bool3 = true;
					try {

						JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(taskObj));
						params.put("content", jsonObject.toString());
						String json3 = HttpClientUtil.doPost(smsServerUrl3, params);
						obj3 = JSONObject.parseObject(json3);
						bool3 = (Boolean) obj3.get("success");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("》》》验证码校验结束《《《");
					if (bool3) {
						result.put("success", true);
					} else {
						result.put("msg", obj3.get("msg"));
					}
				} else {
					System.out.println("》》》验证码校验结束《《《");
					result.put("msg", obj2.get("msg"));
				}
			}
		} catch (Exception e) {
			result.put("msg", "SMS Platform Exception");
			e.printStackTrace();
		}

		return result;
	}
	
	private String getData() {
		Map<String,Object> map = new HashMap<>();
	    map.put("event_id", "f8175aff76c549d4a00eb9aa20c6301c");
	    map.put("open_id", "ohjKK5apJzdKGqOj-jB6DCAYEkrM");
	    map.put("invite_openid", "ohjKK5apJzdKGqOj-jB6DCAYEkrj");
	    map.put("cellphone", "13399998888");
	    map.put("name", "武德");
	    map.put("company", "苹果公司");
	    map.put("vOKI_", "白酒馆");
	    map.put("aiDi_", "iphonx");
	    String json = JSON.toJSONString(map);
	    return json;
	}

	@ResponseBody
	@RequestMapping(value = "/information")
	public Map<String, Object> registerInfo(@RequestParam(name = "data") String data, @RequestParam(name = "code", required = false) String code)
			throws MalformedURLException, IOException {
		System.out.println("informationdata==="+data);
		if("1".equals(data)) {
			data = getData();
		}
		
		Map dataMap = JSON.parseObject(data);
		System.out.println("dataMap===="+dataMap);
		String eventId = (String) dataMap.get("event_id");
		String openId = (String) dataMap.get("open_id");
		String cellphone = (String) dataMap.get("cellphone");
		String name = (String) dataMap.get("name");
		
		// 如果openId为空，则通过code获取openId
		if(StringUtils.isEmpty(openId) && StringUtils.isNotEmpty(code)) {
			System.out.println("1111");
			String appId = environment.getProperty("appID");
			String appSecret = environment.getProperty("appSecret");
			String userUrl = UserInfoUtil.getWebAccess(code, appId, appSecret);
			String userResponse = HttpsUtil.httpsRequestToString(userUrl, "GET", null);
			JSONObject jsonUserObject = JSONObject.parseObject(userResponse);
			if (null != jsonUserObject && jsonUserObject.getString("openid") != null) {
				openId = jsonUserObject.getString("openid");
			}
		}
		
		Map<String, Object> res = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(cellphone) && StringUtils.isNotEmpty(openId) && StringUtils.isNotEmpty(eventId)) {
			System.out.println("2222");
			// 如果已经报名，则取出之前的报名信息
			if(signService.checkRegister(openId, eventId)) {
				System.out.println(openId + "======这个用户曾经报名过====" + eventId);
				Tickets ticket = ticketService.findTicketByopenId(openId, eventId);
				res.put("msg", true);
				res.put("name", name);
				res.put("numUrl", ticket.getTicketNumUrl());
				res.put("serialNumber", ticket.getTicketsNum());
				res.put("cellphone", cellphone);
				res.put("openId", openId);
				return res;
			}
			
			// 如果有photo字段,则生成圆形的图
			String photo = (String) dataMap.get("photo");
			if(StringUtils.isNotEmpty(photo)) {
				int index = photo.lastIndexOf(".");
				if(index > 0) {
					String url = photo.substring(0, index) + "_c.png";
					String circlePath = uploadDir + url.substring(xiaoguaUrl.length());
					File path = new File(circlePath);
					if(!path.exists()) {
						System.out.println("====baoming2circle====");
						getCircleHeadImage(photo, eventId, circlePath);
					}
				}
			}
			
			// 增加报名信息
			signService.register(dataMap);

			SysUserInfoEntity user = new SysUserInfoEntity();
			user.setOpenId(openId);
			user.setEventId(eventId);
			user.setRestigerStatus(UserConstant.SIGNUP);
			// 修改报名状态
			sysUserInfoService.updateStatus(user);
			
			try {
				Date timestamp = new Date();
				StringBuilder sb = new StringBuilder();
				sb.append("ticket/").append(DateUtil.formatDate(timestamp, "yyyyMM")).append("/")
				.append(DateUtil.formatDate(timestamp, "dd")).append("/").append(UUID.randomUUID().toString().replace("-", "")).append(".png");
				// 将小程序地址保存在数据库中
				
				Event event = eventService.getEventById(eventId);
				String ticketPrefix = event.getTicketPrefix();
				Integer ticketLength = event.getTicketLength();
				String generateNumber = null;
				if(StringUtils.isNotEmpty(ticketPrefix)) {
					if( ticketLength == null || ticketLength < 0) {
						ticketLength = 9;
					}
					int len = ticketLength - ticketPrefix.length() - 1;
					String format = getBarcodeMidFormat(len);
					Integer senquence = sequenceService.nextInt(eventId);
					String formatSenquence = formatDecimal(new BigDecimal(senquence), format);
					int rand = (int)(Math.random() * (10));
					generateNumber = ticketPrefix + formatSenquence + rand;  // 前缀  + 序列号 + 1位随机数
				} else {
					generateNumber = NumberUtil.generateNumber();
				}
				String ticketNumUrl = sb.toString();
				File ticketFile = new File(uploadDir + ticketNumUrl);
				
				File parentDir = ticketFile.getParentFile();
				if (!parentDir.exists()) {
					parentDir.mkdirs(); // 如果目录不存在则创建
				}
				QrcodeUtil.encode(generateNumber, 300, 300, ticketFile, "png");
				
				String url = xiaoguaUrl + ticketNumUrl;
				System.out.println("====ticketNumUrl========" + url);
				Tickets tickets = new Tickets();
				tickets.setTicketsNum(generateNumber);
				tickets.setTicketNumUrl(url);
				tickets.setOpenId(openId);
				tickets.setCreateTime(new Date());
				tickets.setEventId(eventId);
				ticketService.insertTicket(tickets);
				res.put("msg", true);
				res.put("name", name);
				res.put("numUrl", url);
				res.put("serialNumber", generateNumber);
				res.put("cellphone", cellphone);
				res.put("openId", openId);
			} catch (Exception e) {
				e.printStackTrace();
				res.put("msg", false);
				return res;
			}
		} else {
			System.out.println("cellphone, open_id, event_id必须有值");
			res.put("msg", false);
		}
		return res;
	}

	private String getBarcodeMidFormat(int len) {
	    if (len <= 0)
	      return "00000000";// 如果数字小于0，即不合法则返回八位格式的
	    else {
	      StringBuilder sb = new StringBuilder();
	      for (int i = 0; i < len; i++) {
	        sb.append("0");
	      }
	      return sb.toString();
	    }
	}

	private String formatDecimal(BigDecimal value, String format) {
		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(value);
	}

	@ResponseBody
	@RequestMapping(value = "/getinfo")
	public Map<String, Object> registerInfo(String openId, HttpServletRequest request
			, @RequestParam(name = "eventId") String eventId) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("msg", true);
		Map<String, Object> signInfos = signService.getSignInfos(openId, eventId);
		System.out.println("====signInfos====" + signInfos);
		if(CollectionUtils.isEmpty(signInfos)) {
			SysUserInfoEntity user = sysUserInfoService.findByOpenId(openId, eventId);
			res.put("qrCodeUrl", user.getQrCodeUrl());
			res.put("openId", openId);
		} else {
			res.put("name", signInfos.get("name"));
			String photo = (String) signInfos.get("photo");
			if(StringUtils.isNotEmpty(photo)) {
				int index = photo.lastIndexOf(".");
				if(index > 0) {
					String url = photo.substring(0, index) + "_c.png";
					String circlePath = uploadDir + url.substring(xiaoguaUrl.length());
					File path = new File(circlePath);
					if(!path.exists()) {
						getCircleHeadImage(photo, eventId, circlePath);
					}
					photo = url;
				}
			}
			res.put("photo", photo);
			res.put("numUrl", signInfos.get("ticket_num_url"));
			res.put("qrCodeUrl", signInfos.get("qrcode_url"));
			res.put("serialNumber", signInfos.get("ticket_num"));
			res.put("cellphone", signInfos.get("cellphone"));
			res.put("openId", openId);
		}
		System.out.println("=====getinfo=====" + res);
		return res;
	}
	
	private void getCircleHeadImage(String headImgUrl, String eventId, String circleImgPath) {
		System.out.println(headImgUrl + "====circleImgPath===" + circleImgPath);
		if(StringUtils.isEmpty(headImgUrl)) {
			return;
		}
		
		try {
			Date timestamp = new Date();
			String split = "/";
			StringBuilder pathSb = new StringBuilder();
			pathSb.append("attachment/")
					.append(eventId).append(split)
					.append(DateUtil.formatDate(timestamp, "yyyyMM")).append(split)
					.append(DateUtil.formatDate(timestamp, "dd")).append(split)
					.append(getRandomName(timestamp));
			
			String destPath = uploadDir + pathSb + ".jpg";
			File downDir = new File(destPath).getParentFile();
			if(!downDir.exists()) {
				downDir.mkdirs();
			}
			ImageUtils.download(headImgUrl, destPath);
			ImageUtils.getCircleImage(destPath, circleImgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getRandomName(Date timestamp) {
		return DateUtil.formatDate(timestamp, "yyyyMMddHHmmssSSS")+RandomUtil.getString(4);
	}

}
