package com.coolgua.signup.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.coolgua.signup.domain.Event;
import com.coolgua.signup.domain.Field;
import com.coolgua.signup.domain.FormField;
import com.coolgua.signup.domain.Scene;

public interface EventMapper {

	List<Event> getEvents(@Param("orgId") Integer orgId);
	
	void addEvent(Event event);

	Event getEventById(Map<String, Object> param);

	List<Map<String, Object>> findPageRegisters(Map<String, Object> params);

	void updateLogo(Map<String, Object> param);

	Scene getScene(String id);

	void addScene(Scene scene);

	void updateEvent(Event event);

	List<Field> getAvailableFields(Map<String, Object> paramters);

	Field getFieldByName(String name);

	void addDBField(Map<String, Object> paramters);

	void addField(Field field);

	void addFormField(FormField formField);

	void updateFormField(FormField formField);

	void clearFormFields(Map<String, Object> params);

	List<Map<String, Object>> effectReport(String eventId);

	List<Map<String, Object>> inviteReport(String eventId);

	List<FormField> getEventFields(String eventId);

	List<Map<String, Object>> getRegisters(Map<String, Object> params);

	Integer regCount(String eventId);

	List<Map<String, Object>> regBasicReport(Map<String, Object> params);

	List<Map<String, Object>> getRegCount(@Param("ids") List<String> ids);

	List<Map<String, Object>> daySignCountReport(Map<String, Object> params);

	List<Map<String, Object>> dayShareCountReport(Map<String, Object> params);

	List<Map<String, Object>> dayShowCountReport(Map<String, Object> params);

	List<Map<String, Object>> monthSignCountReport(Map<String, Object> params);

	List<Map<String, Object>> monthShareCountReport(Map<String, Object> params);

	List<Map<String, Object>> monthShowCountReport(Map<String, Object> params);

	List<Map<String, Object>> findGalaxyRank(Map<String, Object> params);

	Map<String, Object> getRegister(Map<String, Object> params);

	Map<String, Object> getRegisterSimple(Map<String, Object> params);

	int findPageRegisterCount(Map<String, Object> params);

}
