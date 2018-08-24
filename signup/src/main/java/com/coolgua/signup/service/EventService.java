package com.coolgua.signup.service;

import java.util.List;
import java.util.Map;

import com.coolgua.signup.domain.Event;
import com.coolgua.signup.domain.Scene;
import com.coolgua.signup.util.PageUtil;

public interface EventService {

	List<Event> getEvents(Integer orgId);

	void addEvent(Event event);

	Event getEventById(String id);

	PageUtil<Map<String, Object>> findPageRegisters(String eventId, Integer page, Integer size);
	
	Map<String, Object> getRegisters(Map<String, Object> params, String uploadDir);

	void updateLogo(String logoUrl, String eventId);

	Scene getScene(String id);
	
	void addScene(Scene scene);

	void updateEvent(Event event);

	List<Map<String, Object>> effectReport(String eventId);

	List<Map<String, Object>> inviteReport(String eventId);

	Integer regCount(String eventId);

	Map<String, Object> basicReport(String eventId, String dim, String startTime, String endTime);

	List<Map<String, Object>> findGalaxyRank(String eventId, String openId);

	Map<String, Object> getRegister(String eventId, String openId);

	Map<String, Object> getRegisterSimple(String eventId, String openId);

}
