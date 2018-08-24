package com.coolgua.signup.service;

import java.util.List;
import java.util.Map;

import com.coolgua.signup.domain.SysUserInfoEntity;

public interface SysUserInfoService {

	SysUserInfoEntity findByOpenId(String openId, String eventId);

	void saveUserinfo(SysUserInfoEntity entity);

	Map<String, Integer> selectCount(String eventId);

	void updateStatus(SysUserInfoEntity sysUserInfoEntity);
	
	void updateShareNumByopenId(SysUserInfoEntity user);

	int findStatisticsCount(SysUserInfoEntity user);

	List<SysUserInfoEntity> findUserList(SysUserInfoEntity user);

	int findRanking(String openId, String eventId);

	void updateQrCodeUrl(SysUserInfoEntity entity);

	void updateNickName(SysUserInfoEntity user);

}
