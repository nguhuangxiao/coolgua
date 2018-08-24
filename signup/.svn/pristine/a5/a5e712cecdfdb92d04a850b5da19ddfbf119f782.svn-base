package com.coolgua.signup.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.coolgua.signup.domain.Log;

@Mapper
public interface LogMapper {

	void saveLog(Log lg);

	int selectCount(String eventId);

	int findStatisticsCount(Map<String, Object> params);

}
