package com.coolgua.signup.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolgua.common.util.UUIDGenerator;
import com.coolgua.signup.domain.SysUserInfoEntity;
import com.coolgua.signup.mapper.SysUserInfoMapper;
import com.coolgua.signup.service.SysUserInfoService;

@Service
public class SysUserInfoServiceImpl implements SysUserInfoService{

	@Resource
	private SysUserInfoMapper sysUserInfoMapper;
	
	@Override
	public SysUserInfoEntity findByOpenId(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		return sysUserInfoMapper.findByOpenId(params);
	}

	@Override
	@Transactional
	public void saveUserinfo(SysUserInfoEntity entity) {
		String openId = entity.getOpenId();
		synchronized (openId) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("openId", openId);
			params.put("eventId", entity.getEventId());
			SysUserInfoEntity user = sysUserInfoMapper.findByOpenId(params);
			if(user == null) { // 如果没有注册
				sysUserInfoMapper.saveUserinfo(entity);
			} else {
				sysUserInfoMapper.updateUserinfo(entity);
			}
		}
	}

	@Override
	public Map<String, Integer> selectCount(String eventId) {
		return sysUserInfoMapper.selectCount(eventId);
	}

	@Override
	public void updateStatus(SysUserInfoEntity sysUserInfoEntity) {
		sysUserInfoMapper.updateStatus(sysUserInfoEntity);
	}

	@Override
	@Transactional
	public void updateShareNumByopenId(SysUserInfoEntity user) {
		sysUserInfoMapper.updateShareNumByopenId(user);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", user.getOpenId());
		params.put("eventId", user.getEventId());
		params.put("id", UUIDGenerator.getUUID());
		sysUserInfoMapper.addShareLog(params);
	}

	@Override
	public int findStatisticsCount(SysUserInfoEntity user) {
		return sysUserInfoMapper.findStatisticsCount(user);
	}

	@Override
	public List<SysUserInfoEntity> findUserList(SysUserInfoEntity user) {
		return sysUserInfoMapper.findUserList(user);
	}

	@Override
	public int findRanking(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		return  sysUserInfoMapper.findRanking(params);
	}

	@Override
	public void updateQrCodeUrl(SysUserInfoEntity user) {
		sysUserInfoMapper.updateQrCodeUrl(user);
	}

	@Override
	public void updateNickName(SysUserInfoEntity user) {
		sysUserInfoMapper.updateNickName(user);
	}

}
