package com.coolgua.signup.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.coolgua.common.util.UUIDGenerator;
import com.coolgua.signup.domain.Sign;
import com.coolgua.signup.mapper.SignMapper;
import com.coolgua.signup.service.SignService;

@Service
public class SignServiceImpl implements SignService{
	
	@Resource
	SignMapper signMapper;

	@Override
	public Sign findUserSignByOpenId(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		return signMapper.findUserSignByOpenId(params);
	}

	@Override
	public Integer findRanking(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		return signMapper.findRanking(params);
	}

	@Override
	public Map<String, Object> getSignInfos(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		return signMapper.getSignInfos(params);
	}

	@Override
	public Integer getSameTradeCountByOpenId(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		return signMapper.getSameTradeCountByOpenId(params);
	}

	@Override
	public List<Map<String, Object>> getSameTradeUsersByOpenId(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		return signMapper.getSameTradeUsersByOpenId(params);
	}

	@Override
	public Integer findAttentionRanking(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		return signMapper.findAttentionRanking(params);
	}

	@Override
	public void register(Map<String, Object> data) {
		List<String> fields = new ArrayList<>(data.keySet());
		List<Object> datas = new ArrayList<>(fields.size());
		for (String field : fields) {
			Object e = data.get(field);
			if(e instanceof JSONArray) { // 复选框的值
				JSONArray a = (JSONArray)e;
				datas.add(a.toString());
			} else {
				datas.add(e);
			}
		}

		Map<String, Object> params = new HashMap<>();
		params.put("id", UUIDGenerator.getUUID());
		params.put("fields", fields);
		params.put("datas", datas);
		signMapper.addData(params);
		
		// 更新邀请人数
		String inviteOpenId = (String) data.get("invite_openid");
		System.out.println("inviteOpenId====" + inviteOpenId);
		if(StringUtils.isNotEmpty(inviteOpenId)) {
			String eventId = (String) data.get("event_id");
			System.out.println("eventId====" + eventId);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("openId", inviteOpenId);
			param.put("eventId", eventId);
			System.out.println("param====" + param);
			signMapper.updateInviteNum(param);
		}
	}

	@Override
	public boolean checkRegister(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		int count = signMapper.checkRegister(params);
		return count > 0;
	}

}
