package com.coolgua.signup.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coolgua.common.util.DateUtil;
import com.coolgua.signup.constant.UserConstant;
import com.coolgua.signup.domain.Sign;
import com.coolgua.signup.domain.SysUserInfoEntity;
import com.coolgua.signup.service.LogService;
import com.coolgua.signup.service.SignService;
import com.coolgua.signup.service.SysUserInfoService;
import com.coolgua.signup.service.TicketsService;
import com.coolgua.signup.util.RandomUtil;
import com.coolgua.signup.util.image.ImageUtils;
import com.vdurmont.emoji.EmojiParser;

@Controller
@RequestMapping(value = "/invite")
public class InviteController {

	@Autowired
	SysUserInfoService sysUserInfoService;
	
	@Autowired
	TicketsService ticketService;
	
	@Autowired
	SignService signService;
	
	@Autowired
	LogService logService;

	@Value("${UPLOAD_DIR}")
	private String uploadDir;

	@Value("${XIAOGUA_URL}")
	private String xiaoguaUrl;

	@ResponseBody
	@RequestMapping(value = "/share")
	public Map<String, Object> listVote(@RequestParam(name = "openId") String openId,
			@RequestParam(name = "eventId") String eventId) {
		SysUserInfoEntity user = sysUserInfoService.findByOpenId(openId, eventId);
		Map<String, Object> map = new HashMap<>();
		if (user != null) {
			if (user.getRestigerStatus() == UserConstant.SIGNUP) {
				map.put("userHeadPicUrl", user.getHeadImgurl());
				Sign sign = signService.findUserSignByOpenId(openId, eventId);
				map.put("userName", sign.getName());
				map.put("openId", openId);
				map.put("msg", true);
			} else {
				map.put("qrCodeUrl", "");
				map.put("userHeadPicUrl", user.getHeadImgurl());
				map.put("userName", user.getNickName());
				map.put("openId", openId);
				map.put("msg", true);
			}
		} else {
			map.put("msg", false);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/shareNum")
	public Map<String, Object> shareNum(@RequestParam(name = "openId") String openId
			, @RequestParam(name = "eventId") String eventId) {
		Map<String, Object> map = new HashMap<>();
		SysUserInfoEntity user = sysUserInfoService.findByOpenId(openId, eventId);
		if(user == null) {
			map.put("msg", false);
			map.put("openId", openId);
			map.put("error", "该微信还没有报名,不能进行分享");
		} else {
			user.setOpenId(openId);
			sysUserInfoService.updateShareNumByopenId(user);
			map.put("msg", true);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/sametrade")
	public Map<String, Object> sameTrade(@RequestParam(name = "openId") String openId
			, @RequestParam(name = "eventId") String eventId) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("=====sameTrade11111111======"+openId);
		SysUserInfoEntity user = sysUserInfoService.findByOpenId(openId, eventId);
		System.out.println("=====sameTrade22222======"+user);
		if (user != null) {
			Sign sign = signService.findUserSignByOpenId(openId, eventId);
			if (sign != null) {
				String inviteOpenId = sign.getInviteOpenid();
				System.out.println("=====sameTrade333333======"+inviteOpenId);
				if(inviteOpenId != null && !inviteOpenId.equals("undefined")) {
					SysUserInfoEntity byInviteUser = sysUserInfoService.findByOpenId(inviteOpenId, eventId);
					System.out.println("=====sameTrade44444======"+byInviteUser);
					if(byInviteUser!=null && !byInviteUser.getOpenId().equals(openId)) {
						String nickName = byInviteUser.getNickName();
						System.out.println("=====sameTrade55555111======"+nickName);
						if(StringUtils.isNoneEmpty(nickName)) {
							nickName = EmojiParser.parseToUnicode(nickName);
							byInviteUser.setNickName(nickName);
						}
						System.out.println("=====sameTrade55555222======"+nickName);
						Sign byInviteSign = signService.findUserSignByOpenId(inviteOpenId, eventId);
						if(byInviteSign != null && StringUtils.isNotEmpty(byInviteSign.getPhoto())) {
							String photo = byInviteSign.getPhoto();

							// 如果有上传图片，则需要处理成圆形的
							int index = photo.lastIndexOf(".");
							if(index > 0) {
								try {
									String url = photo.substring(0, index) + "_c.png";
									String circlePath = uploadDir + url.substring(xiaoguaUrl.length());
									File path = new File(circlePath);
									if(!path.exists()) {
										getCircleHeadImage(photo, eventId, circlePath);
									}
									photo = url;
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							byInviteUser.setHeadImgurl(photo);
						}
						map.put("byInviteUser", byInviteUser);
						System.out.println("=====sameTrade66666======"+map);
					}
				} else {
					System.out.println("=====sameTrade7777777======");
				}
				System.out.println("=====sameTrade8888======");
				map.put("userName", sign.getName());
				map.put("userHeadPicUrl", StringUtils.isNotEmpty(sign.getPhoto()) ? sign.getPhoto() : user.getHeadImgurl());
				// 邀请好友的人
				List<Map<String, Object>> userlist = signService.getSameTradeUsersByOpenId(openId, eventId);
				map.put("inviteUser", userlist);
				
				// 受邀好友  个数(分享后 从分享链接注册的用户数)
				int count = signService.getSameTradeCountByOpenId(openId, eventId);
				map.put("sameTrade", count);
				// 获取邀请人的排名
				Integer inviteRanking = signService.findRanking(user.getOpenId(), eventId);
				map.put("inviteRanking", inviteRanking == null ? 0 : inviteRanking);
				
				// 影响力指数
				int attentionCount = logService.findStatisticsCount(openId, eventId);
				map.put("invitAttentionCount", attentionCount);
				// 影响力排名
				Integer ranking = signService.findAttentionRanking(openId, eventId);
				map.put("shareRanking", ranking == null ? 0 : ranking);
				map.put("msg", true);
			} else {
				map.put("msg", false);
			}
		} else {
			map.put("msg", false);
		}
		return map;
	}
	
	private String getCircleHeadImage(String headImgUrl, String eventId, String circleImgPath) {
		System.out.println(headImgUrl + "====circleImgPath===" + circleImgPath);
		if(StringUtils.isEmpty(headImgUrl)) {
			return null;
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
//			String circleImgPath = uploadDir + pathSb + "_c.png";
			ImageUtils.getCircleImage(destPath, circleImgPath);
			return xiaoguaUrl + pathSb + "_c.png";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getRandomName(Date timestamp) {
		return DateUtil.formatDate(timestamp, "yyyyMMddHHmmssSSS")+RandomUtil.getString(4);
	}

}
