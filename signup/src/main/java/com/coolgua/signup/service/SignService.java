package com.coolgua.signup.service;

import java.util.List;
import java.util.Map;

import com.coolgua.signup.domain.Sign;

public interface SignService {

	Sign findUserSignByOpenId(String openId, String eventId);

	Integer findRanking(String openId, String eventId);
	
	Map<String, Object> getSignInfos(String openId, String eventId);

	Integer getSameTradeCountByOpenId(String openId, String eventId);

	List<Map<String, Object>> getSameTradeUsersByOpenId(String openId, String eventId);

	Integer findAttentionRanking(String openId, String eventId);

	void register(Map<String, Object> dataMap);

	boolean checkRegister(String openId, String eventId);

}
