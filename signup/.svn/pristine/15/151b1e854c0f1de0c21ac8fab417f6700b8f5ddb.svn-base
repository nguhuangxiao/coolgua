package com.coolgua.signup.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.coolgua.common.service.RedisService;
import com.coolgua.common.util.UUIDGenerator;
import com.coolgua.signup.constant.UserConstant;
import com.coolgua.signup.domain.Event;
import com.coolgua.signup.domain.Log;
import com.coolgua.signup.domain.Scene;
import com.coolgua.signup.domain.Sign;
import com.coolgua.signup.domain.SysUserInfoEntity;
import com.coolgua.signup.service.EventService;
import com.coolgua.signup.service.LogService;
import com.coolgua.signup.service.SignService;
import com.coolgua.signup.service.SysUserInfoService;
import com.coolgua.signup.util.HttpsUtil;
import com.coolgua.signup.util.QrcodeUtil;
import com.coolgua.signup.util.UserInfoUtil;
import com.coolgua.signup.util.image.ImageUtils;
import com.vdurmont.emoji.EmojiParser;

@Controller
@RequestMapping(value = "/userinfo")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	Environment environment;
	
	@Autowired
	private SysUserInfoService sysUserInfoService;
	
	@Autowired
	private LogService logService;

	@Autowired
	private SignService signService;
	
	@Autowired
	private RedisService redisService;

	@Autowired
	private EventService eventService;
	
	@Value("${XIAOGUA_URL}")
	private String xiaoguaUrl;

	@Value("${UPLOAD_DIR}")
	private String uploadDir;

	@Value("${appID}")
	private String appID;

	/**
	 * 登录 通过js_code 获取openid及session_key
	 * 
	 * @param code
	 * @param state
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/vote")
	public Map<String, Object> listVote(@RequestParam(name = "code") String code,
			@RequestParam(name = "nickname") String nickname, @RequestParam(name = "headimgurl") String headimgurl,
			@RequestParam(name = "sex") String sex, @RequestParam(name = "inviteOpenId") String inviteOpenId
			, @RequestParam(name = "eventId") String eventId) {
		System.out.println(inviteOpenId+">>1111》》》收到请求，请求数据为[ code：" + code + "]" + "==nickname==" + nickname);
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isEmpty(eventId)) {
			System.out.println("eventId is empty!!!!!!!!!");
			map.put("stauts", 0);
			map.put("msg", false);
			return map;
		}
		
		// 通过code换取网页授权web_access_token
		if (code != null && !(code.equals(""))) {
			String CODE = code;
			String accessToken = "";
			String openId = "";
			int restigerStatus = 0;
			String userId = null;
			// 替换字符串，获得请求URL
			String appId = environment.getProperty("appID");
			String appSecret = environment.getProperty("appSecret");
			String token = UserInfoUtil.getWebAccess(CODE, appId, appSecret);
			// 通过https方式请求获得web_access_token
			String response = HttpsUtil.httpsRequestToString(token, "GET", null);
			JSONObject jsonObject = JSON.parseObject(response);
			// 如果JasonObject或opeid为空则授权失败
			if (null != jsonObject && jsonObject.getString("openid") != null) {
				try {
					accessToken = jsonObject.getString("session_key");
					openId = jsonObject.getString("openid");
					logger.info(
							"》》》获取access_token成功[session_key:" + accessToken + "---------------openid:" + openId);
					// 通过SysUserInfoService检查openid是否在SysUserInfoEntity库中
					SysUserInfoEntity one = sysUserInfoService.findByOpenId(openId, eventId);
					System.out.println(eventId+"66666666user>>>" + one);
					if (one == null) {
						SysUserInfoEntity entity = new SysUserInfoEntity();
						entity.setOpenId(openId);
						entity.setCreateTime(new Date());
						// 处理微信用户昵称中的表情符号
						System.out.println("88888888nickname=====" + nickname);
//						nickname = nickname.replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", "^-^");
						nickname = EmojiParser.parseToAliases(nickname);
						System.out.println("99999999nickname=====" + nickname);
						entity.setNickName(nickname);
						entity.setSex(sex);
						entity.setHeadImgurl(headimgurl);
						String headImgCircle = getCircleHeadImage(headimgurl, openId);
						entity.setHeadImgCircle(headImgCircle);
						entity.setRestigerStatus(UserConstant.SIGNDOWN);
						System.out.println("aaaaaaaaaaaaa========" + inviteOpenId);
						if (inviteOpenId == null || inviteOpenId == "") {
							entity.setInviteTickets(inviteOpenId);
						}
						String userQrCode = getUserQrCode(openId, eventId);
						entity.setQrCodeUrl(userQrCode);
						entity.setTicketsNum("vote-save"); // just test
						entity.setEventId(eventId);
						sysUserInfoService.saveUserinfo(entity);
						System.out.println(openId+"==cccccccccc========"+eventId);
						try {
							SysUserInfoEntity user = sysUserInfoService.findByOpenId(openId, eventId);
							if(user != null) {
								userId = user.getUserId();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						userId = one.getUserId();
						restigerStatus = one.getRestigerStatus();
						String nick = one.getNickName();
						System.out.println(nick + "====nickname22=====" + nickname);
						System.out.println("====!nick.equals(nickname)=====" + !nickname.equals(nick));
						if(nick == null || !nick.equals(nickname)) {
							System.out.println("====nickname23=====" + one.getEventId());
							nickname = EmojiParser.parseToAliases(nickname);
							one.setNickName(nickname);
							one.setSex(sex);
							one.setHeadImgurl(headimgurl);
							String headImgCircle = getCircleHeadImage(headimgurl, openId);
							one.setHeadImgCircle(headImgCircle);
							one.setTicketsNum("vote-update"); // just test
							sysUserInfoService.updateNickName(one);
							System.out.println("saved!");
						}
					}
					/*
					Log lg = new Log(userId, openId, accessToken, 1, new Date(), null, inviteOpenId);
					logService.saveLog(lg);
					*/
					int showcount = logService.selectCount(eventId);
//					int sharecount = sysUserInfoService.selectCount();
					Map<String, Integer> stat = sysUserInfoService.selectCount(eventId);
					System.out.println("==========stat22=======" + stat);
					map.put("sharecount", stat.get("sharecount"));
					System.out.println("==========map22=======" + map);
					map.put("userid", userId);
					map.put("showcount", showcount);
//					map.put("sharecount", sharecount);
					map.put("restigerStatus", restigerStatus);
					map.put("openid", jsonObject.getString("openid"));
					map.put("session_key", jsonObject.getString("session_key"));
					map.put("status", 1);
					map.put("msg", true);
				} catch (JSONException e) {
					e.printStackTrace();
					accessToken = null;// 获取code失败
					System.out.println("获取session_key失败");
					map.put("stauts", 0);
					map.put("msg", false);
				}
			} else {
				System.out.println("获取openid及session_key失败");
				map.put("stauts", 0);
				map.put("msg", false);
			}
		}
		return map;
	}
	
	private String getCircleHeadImage(String headImgUrl, String openId) {
		if(StringUtils.isEmpty(headImgUrl)) {
			return null;
		}
		
		try {
			String split = "/";
			String path = new StringBuilder("attachment/wxhead/").append(appID).append(split).append(openId).toString();
			String headImgPath = uploadDir + path + ".jpg";
			String circleHeadImgPath = uploadDir + path + "_c.png";

			File downDir = new File(uploadDir + path);
			if(!downDir.exists()) {
				downDir.mkdirs();
			}
			ImageUtils.download(headImgUrl, headImgPath);
			ImageUtils.getCircleImage(headImgPath, circleHeadImgPath);
			return xiaoguaUrl + path + "_c.png";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/getvote", method = RequestMethod.POST)
	public Map<String, Object> getVote(@RequestParam(name = "code") String code
			, @RequestParam(name = "inviteOpenId") String inviteOpenId
			, @RequestParam(name = "eventId") String eventId) {
		System.out.println(inviteOpenId+"=====getVote=====" + code);
		String openId = null;
		Sign sign = null;
		if(StringUtils.isNotEmpty(code)) {
			String userId = null;
			String accessToken = null;
			
			// 替换字符串，获得请求URL
			String appId = environment.getProperty("appID");
			String appSecret = environment.getProperty("appSecret");
			String token = UserInfoUtil.getWebAccess(code, appId, appSecret);
			// 通过https方式请求获得web_access_token
			String response = HttpsUtil.httpsRequestToString(token, "GET", null);
			JSONObject jsonObject = JSON.parseObject(response);
			System.out.println("jsonObject>>>" + jsonObject);
			// 如果JsonObject或opeid为空则授权失败
			if (null != jsonObject && jsonObject.getString("openid") != null) {
				try {
					openId = jsonObject.getString("openid");
					SysUserInfoEntity user = sysUserInfoService.findByOpenId(openId, eventId);
					if(user == null) { // 如果用户不存在, 则新增用户
						SysUserInfoEntity entity = new SysUserInfoEntity();
						entity.setOpenId(openId);
						entity.setCreateTime(new Date());
						entity.setRestigerStatus(UserConstant.SIGNDOWN);
						String userQrCode = getUserQrCode(openId, eventId);
						entity.setQrCodeUrl(userQrCode);
						System.out.println(openId + "===========entityQrCodeUrl======" + entity.getQrCodeUrl());
						entity.setTicketsNum("getvote-save"); // just test
						entity.setEventId(eventId);
						sysUserInfoService.saveUserinfo(entity);
						userId = entity.getUserId();
					} else if(user.getQrCodeUrl() == null) {
						String userQrCode = getUserQrCode(openId, eventId);
						SysUserInfoEntity entity = new SysUserInfoEntity();
						entity.setOpenId(openId);
						entity.setQrCodeUrl(userQrCode);
						sysUserInfoService.updateQrCodeUrl(entity);
						userId = entity.getUserId();
					}
					
					sign = signService.findUserSignByOpenId(openId, eventId);
					System.out.println("===========sign======" + sign);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("获取session_key失败");
				}
			} else {
				System.out.println("获取openid及session_key失败");
			}

			Log lg = new Log(userId, openId, accessToken, 1, new Date(), null, inviteOpenId);
			lg.setEventId(eventId);
			logService.saveLog(lg);
		} else {
			Log lg = new Log(null, openId, null, 1, new Date(), null, null);
			lg.setEventId(eventId);
			logService.saveLog(lg);
		}

		Map<String, Object> map = new HashMap<>();
		try {
			int showcount = logService.selectCount(eventId);
			Map<String, Integer> stat = sysUserInfoService.selectCount(eventId);
			System.out.println("==========stat=======" + stat);
			map.put("showcount", showcount);
			map.put("sharecount", stat.get("sharecount"));
			Event event = eventService.getEventById(eventId);
			map.put("event", event);
			System.out.println("==========mapaa=======" + map);
			map.put("msg", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", false);
			return map;
		}
		
		map.put("sign", sign == null ? false : true);
		System.out.println("===========mapbb======" + map);
		return map;
	}

	private synchronized String getUserQrCode(String openId, String eventId) {
		// 获取配置信息
		String appId = environment.getProperty("appID");
		String appSecret = environment.getProperty("appSecret");
		
		String sceneId = UUIDGenerator.getUUID();
		Scene scene = new Scene();
		scene.setId(sceneId);
		scene.setEventId(eventId);
		scene.setOpenId(openId);
		eventService.addScene(scene);
		
		String userQrCode = xiaoguaUrl + QrcodeUtil.getAcode(appId, appSecret, sceneId, uploadDir);
		return userQrCode;
	}

}
