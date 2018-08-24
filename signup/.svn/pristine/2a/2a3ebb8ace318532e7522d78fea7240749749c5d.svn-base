package com.coolgua.signup.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.coolgua.signup.domain.SysUserInfoEntity;

@Mapper
public interface SysUserInfoMapper {

	SysUserInfoEntity findByOpenId(Map<String, Object> params);

	void saveUserinfo(SysUserInfoEntity entity);

	Map<String, Integer> selectCount(String eventid);

	void updateStatus(SysUserInfoEntity sysUserInfoEntity);

	void updateShareNumByopenId(SysUserInfoEntity user);

	int findStatisticsCount(SysUserInfoEntity user);

	List<SysUserInfoEntity> findUserList(SysUserInfoEntity user);

	int findRanking(Map<String, Object> params);

	void updateUserinfo(SysUserInfoEntity entity);

	void updateQrCodeUrl(SysUserInfoEntity user);

	void updateNickName(SysUserInfoEntity user);

	void addShareLog(Map<String, Object> params);

}
