package com.coolgua.signup.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.coolgua.signup.domain.Sign;

@Mapper
public interface SignMapper {
	
	void addData(Map<String, Object> data);

	Sign findUserSignByOpenId(Map<String, Object> params);

	Integer findRanking(Map<String, Object> params);
	
	List<Map<String, Object>> findRankAll(String eventId);

	Map<String, Object> getSignInfos(Map<String, Object> params);

	Integer getSameTradeCountByOpenId(Map<String, Object> params);

	List<Map<String, Object>> getSameTradeUsersByOpenId(Map<String, Object> params);

	void updateInviteNum(Map<String, Object> params);

	void updateInviteEnterTimes(Map<String, Object> params);

	Integer findAttentionRanking(Map<String, Object> params);

	int checkRegister(Map<String, Object> params);

	List<Map<String, Object>> findAttentionRankAll(String eventId);

}
