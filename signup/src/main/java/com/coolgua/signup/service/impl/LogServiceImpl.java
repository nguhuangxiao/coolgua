package com.coolgua.signup.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolgua.signup.domain.Log;
import com.coolgua.signup.mapper.LogMapper;
import com.coolgua.signup.mapper.SignMapper;
import com.coolgua.signup.service.LogService;

@Service
public class LogServiceImpl implements LogService{
	
	@Resource
	private LogMapper logMapper;

	@Resource
	private SignMapper signMapper;
	
	@Override
	@Transactional
	public void saveLog(Log lg) {
		logMapper.saveLog(lg);
		String inviteOpenId = lg.getInviteOpenId();
		if(inviteOpenId != null && inviteOpenId.trim().length() > 0) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("openId", inviteOpenId);
			params.put("eventId", lg.getEventId());
			signMapper.updateInviteEnterTimes(params);
		}
	}

	@Override
	public int selectCount(String eventId) {
		return logMapper.selectCount(eventId);
	}

	@Override
	public int findStatisticsCount(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		return logMapper.findStatisticsCount(params);
	}
	
}
