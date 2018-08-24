package com.coolgua.signup.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coolgua.common.util.UUIDGenerator;
import com.coolgua.signup.constant.FieldConstant;
import com.coolgua.signup.constant.JSON_RESULT;
import com.coolgua.signup.domain.Event;
import com.coolgua.signup.domain.Field;
import com.coolgua.signup.domain.FormField;
import com.coolgua.signup.domain.Scene;
import com.coolgua.signup.mapper.EventMapper;
import com.coolgua.signup.mapper.SignMapper;
import com.coolgua.signup.service.EventService;
import com.coolgua.signup.util.DateUtil;
import com.coolgua.signup.util.PageUtil;
import com.coolgua.signup.util.RandomUtil;

@Service("eventService")
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventMapper eventMapper;

	@Resource
	SignMapper signMapper;

	@Override
	public List<Event> getEvents(Integer orgId) {
		if(orgId != null && orgId < 0) {
			orgId = null;
		}
		List<Event> events = eventMapper.getEvents(orgId);
		if(!CollectionUtils.isEmpty(events)) {
			List<String> ids = getEventIds(events);
			List<Map<String, Object>> regCounts = eventMapper.getRegCount(ids);
			if(!CollectionUtils.isEmpty(regCounts)) {
				Map<String, Object> regMap = getRegMap(regCounts);
				for (Event event : events) {
					Object object = regMap.get(event.getId());
					if(object != null) {
						Integer count = null;
						if(object instanceof Long) {
							count = ((Long) object).intValue();
						} else if(object instanceof Double) {
							count = ((Double) object).intValue();
						}
						event.setRegNum(count == null ? 0 : count);
					}
				}
			}
		}
		return events;
	}
	
	private Map<String, Object> getRegMap(List<Map<String, Object>> regCounts) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (Map<String, Object> map : regCounts) {
			String eventId = (String)map.get("event_id");
			Object count = map.get("count");
			result.put(eventId, count);
		}
		return result;
	}
	
	private List<String> getEventIds(List<Event> events) {
		List<String> ids = new ArrayList<>();
		for (Event event : events) {
			ids.add(event.getId());
		}
		return ids;
	}

	@Override
	public void addEvent(Event event) {
		updateLayoutFields(event);
		eventMapper.addEvent(event);
	}

	@Override
	public Event getEventById(String id) {
		Map<String, Object> param = new HashMap<>();
		param.put("eventId", id);
		return eventMapper.getEventById(param);
	}

	@Override
	public Map<String, Object> getRegisters(Map<String, Object> params, String uploadDir) {
		String eventId = (String)params.get("eventId");
		List<FormField> fieldList = eventMapper.getEventFields(eventId);
		List<String> names = new ArrayList<>();
		List<FormField> fields = new ArrayList<>();
		for (FormField field : fieldList) {
			fields.add(field);
			names.add(field.getEnglish());
			if("9".equals(field.getId()) || "country".equals(field.getEnglish())) { // 如果有国家字段，则也需要查出省、市的字段值
				field.setName("国家");
				FormField f2 = new FormField();
				f2.setId("10");
				f2.setName("省");
				f2.setEnglish("province");
				f2.setTypeId(field.getTypeId());
				f2.setRequired(field.getRequired());
				names.add(f2.getEnglish());
				fields.add(f2);
				
				f2 = new FormField();
				f2.setId("11");
				f2.setName("城市");
				f2.setEnglish("city");
				f2.setTypeId(field.getTypeId());
				f2.setRequired(field.getRequired());
				names.add(f2.getEnglish());
				fields.add(f2);
			}
		}
		params.put("names", names);
		System.out.println("=====names=====" + names);
		System.out.println("=====fields111=====" + fields);
		
		List<Map<String, Object>> registers = eventMapper.getRegisters(params);
		System.out.println("=====registers=====" + registers);
	    Map<String, Integer> effectRanks = getRankMap(signMapper.findAttentionRankAll(eventId));
	    Map<String, Integer> inviteRanks = getRankMap(signMapper.findRankAll(eventId));
	    for (Map<String, Object> map : registers) {
	    	String openId = (String) map.get("open_id");
	    	Integer invite = inviteRanks.get(openId);
	    	Integer effect = effectRanks.get(openId);
	    	map.put("inviteRank", invite == null ? 0 : invite);
	    	map.put("effectRank", effect == null ? 0 : effect);
		}
		fillField(fields); // 补全字段
		System.out.println("=====fields2222=====" + fields);
		
		Map<String, Object> result = new HashMap<>();
		result.put("datas", registers);
		result.put("fields", fields);
		return result;
	}

	@Override
	public Map<String, Object> getRegisterSimple(String eventId, String openId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("eventId", eventId);
		params.put("openId", openId);
		Map<String, Object> register = eventMapper.getRegisterSimple(params);
		if(register != null) {
			Integer effectRank = signMapper.findAttentionRanking(params);
			Integer inviteRank = signMapper.findRanking(params);
			register.put("inviteRank", inviteRank);
			register.put("effectRank", effectRank);
			
			Object createTime = register.get("create_time");
			if(createTime != null && createTime instanceof Date) {
				String format = "yyyy-MM-dd HH:mm";
				String value = DateUtil.formatDate(((Date)createTime), format);
				register.put("create_time", value);
			}
		}
		return register;
	}

	@Override
	public Map<String, Object> getRegister(String eventId, String openId) {
		List<FormField> fieldList = eventMapper.getEventFields(eventId);
		List<String> names = new ArrayList<>();
		List<FormField> fields = new ArrayList<>();
		for (FormField field : fieldList) {
			fields.add(field);
			names.add(field.getEnglish());
			if("9".equals(field.getId()) || "country".equals(field.getEnglish())) { // 如果有国家字段，则也需要查出省、市的字段值
				field.setName("国家");
				FormField f2 = new FormField();
				f2.setId("10");
				f2.setName("省");
				f2.setEnglish("province");
				f2.setTypeId(field.getTypeId());
				f2.setRequired(field.getRequired());
				names.add(f2.getEnglish());
				fields.add(f2);
				
				f2 = new FormField();
				f2.setId("11");
				f2.setName("城市");
				f2.setEnglish("city");
				f2.setTypeId(field.getTypeId());
				f2.setRequired(field.getRequired());
				names.add(f2.getEnglish());
				fields.add(f2);
			}
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("eventId", eventId);
		params.put("openId", openId);
		params.put("names", names);
		Map<String, Object> register = eventMapper.getRegister(params);
		if(register == null || register.size() <= 0) {
			return null;
		}
	    Integer effectRank = signMapper.findAttentionRanking(params);
	    Integer inviteRank = signMapper.findRanking(params);
	    register.put("inviteRank", inviteRank);
	    register.put("effectRank", effectRank);
	    
		fillField(fields); // 补全字段
		FormField f = new FormField();
		f.setName("门票二维码");
		f.setEnglish("ticket_num_url");
		f.setTypeId(0);
		fields.add(f);
		
		f = new FormField();
		f.setName("微信头像");
		f.setEnglish("head_imgurl");
		f.setTypeId(0);
		fields.add(f);
		
		Map<String, FormField> fieldMap = new HashMap<>();
		for (FormField formField : fields) {
			fieldMap.put(formField.getEnglish(), formField);
		}
		
		List<FormField> topFields = getTopField(fieldMap, fields);
		
		List<FormField> reFields = reorderField(fields, eventId);
		
		List<Map<String, Object>> topData = getData(topFields, register);
		List<Map<String, Object>> customData = getData(reFields, register);
		Map<String, Object> result = new HashMap<>();
		result.put("topData", topData);
		result.put("customData", customData);
		return result;
	}
	
	private List<FormField> reorderField(List<FormField> fields, String eventId) {
		Map<String, FormField> fieldMap = new HashMap<>();
		for (FormField formField : fields) {
			fieldMap.put(formField.getEnglish(), formField);
		}
		
		Map<String, Object> p = new HashMap<>();
		p.put("eventId", eventId);
		String layout = eventMapper.getEventById(p).getLayout();
		JSONArray parseArray = JSONArray.parseArray(layout);
		List<FormField> result = new ArrayList<>();
		for (Object object : parseArray) {
			JSONObject obj = (JSONObject)object;
			String english = (String)obj.get("english");
			FormField formField = fieldMap.get(english);
			if(formField != null) {
				result.add(formField);
			}
			if("country".equals(english)) { // 布局里只有国家，所以需要加上省、市字段
				FormField field = fieldMap.get("province");
				if(field != null) {
					result.add(field);
				}
				field = fieldMap.get("city");
				if(field != null) {
					result.add(field);
				}
			}
		}
		return result;
	}

	private List<Map<String, Object>> getData(List<FormField> fields, Map<String, Object> register) {
		List<Map<String, Object>> result = new ArrayList<>();
		String format = "yyyy-MM-dd HH:mm";
		for (FormField field : fields) {
			String english = field.getEnglish();
			Object obj = register.get(english);
			Object value = null;
			if(obj == null) {
				value = "";
			} else if(obj instanceof Date) {
				value = DateUtil.formatDate(((Date)obj), format);
			} else {
				value = obj;
			}
			Map<String, Object> item = new HashMap<>();
			item.put("data", value);
			item.put("english", english);
			item.put("chinese", field.getName());
			item.put("required", field.getRequired());
			item.put("dictionary", field.getDictionary());
			item.put("typeId", field.getTypeId());
			result.add(item);
		}
		return result;
	}
	
	private List<FormField> getTopField(Map<String, FormField> fieldMap, List<FormField> fields) {
		String[] englishs = {"photo", "head_imgurl", "name", "inviter", "cellphone", "create_time"
				, "ticket_num", "ticket_num_url", "inviteNum", "inviteRank", "effect", "effectRank", "shareNum", "open_id"};
		List<FormField> topFields = new ArrayList<>();
		for (String english : englishs) {
			FormField formField = fieldMap.get(english);
			if(formField == null) {
				formField = new FormField();
				formField.setEnglish(english);
			} else {
				fields.remove(formField);
			}
			topFields.add(formField);
		}
		return topFields;
	}

	private void fillField(List<FormField> fields) {
		FormField field = new FormField();
		field.setName("报名时间");
		field.setEnglish("create_time");
		field.setTypeId(0);
		fields.add(field);
		
		field = new FormField();
		field.setName("OpenId");
		field.setEnglish("open_id");
		field.setTypeId(0);
		fields.add(field);
		
		field = new FormField();
		field.setName("门票号");
		field.setEnglish("ticket_num");
		field.setTypeId(0);
		fields.add(field);
		
		field = new FormField();
		field.setName("受邀好友");
		field.setEnglish("inviteNum");
		field.setTypeId(0);
		fields.add(field);
		
		field = new FormField();
		field.setName("邀请排行");
		field.setEnglish("inviteRank");
		field.setTypeId(0);
		fields.add(field);
		
		field = new FormField();
		field.setName("影响力指数");
		field.setEnglish("effect");
		field.setTypeId(0);
		fields.add(field);
		
		field = new FormField();
		field.setName("影响力排行");
		field.setEnglish("effectRank");
		field.setTypeId(0);
		fields.add(field);
		
		field = new FormField();
		field.setName("分享次数");
		field.setEnglish("shareNum");
		field.setTypeId(0);
		fields.add(field);
		
		field = new FormField();
		field.setName("邀请人");
		field.setEnglish("inviter");
		field.setTypeId(0);
		fields.add(field);
	}

	@Override
	public PageUtil<Map<String, Object>> findPageRegisters(String eventId, Integer page, Integer size) {
		System.out.println(page + "=======findPageRegisters111======" + size);
		Map<String, Object> params = new HashMap<>();
		params.put("eventId", eventId);
		if(page == null || page < 1) {
			page = 1;
		}
		if(size == null) {
			size = JSON_RESULT.DEFAULT_SIZE;
		}
		int countPerPage = size;
		params.put("size", size);
		params.put("start", (page-1)*size);

//	    PageHelper.startPage(page, size);
	    List<Map<String, Object>> list = eventMapper.findPageRegisters(params);
	    int count = eventMapper.findPageRegisterCount(params);
	    Map<String, Integer> effectRanks = getRankMap(signMapper.findAttentionRankAll(eventId));
	    Map<String, Integer> inviteRanks = getRankMap(signMapper.findRankAll(eventId));
	    for (Map<String, Object> map : list) {
	    	String openId = (String) map.get("open_id");
	    	Integer invite = inviteRanks.get(openId);
	    	Integer effect = effectRanks.get(openId);
	    	map.put("inviteRank", invite == null ? 0 : invite);
	    	map.put("effectRank", effect == null ? 0 : effect);
		}
	    PageUtil<Map<String, Object>> result = new PageUtil<Map<String, Object>>(list, page, countPerPage, count);
	    return result;
	}
	
	private Map<String, Integer> getRankMap(List<Map<String, Object>> ranks) {
		Map<String, Integer> result = new HashMap<>();
		for (Map<String, Object> map : ranks) {
			String openId = (String)map.get("open_id");
			Object rankObj = map.get("rank");
			Integer rank = null;
			if(rankObj instanceof Long) {
				rank = ((Long)map.get("rank")).intValue();
			} else if(rankObj instanceof Double) {
				rank = ((Double)map.get("rank")).intValue();
			}
			result.put(openId, rank);
		}
		return result;
	}

	@Override
	public void updateLogo(String logoUrl, String eventId) {
		Map<String, Object> param = new HashMap<>();
		param.put("logo", logoUrl);
		param.put("eventId", eventId);
		eventMapper.updateLogo(param);
	}

	@Override
	public void addScene(Scene scene) {
		eventMapper.addScene(scene);
	}

	@Override
	public Scene getScene(String id) {
		return eventMapper.getScene(id);
	}

	@Override
	public void updateEvent(Event event) {
		updateLayoutFields(event);
		eventMapper.updateEvent(event);
	}

	private void updateLayoutFields(Event event) {
		// 处理layout中的字段
		String layoutStr = event.getLayout();
		if(StringUtils.isEmpty(layoutStr)) return;
		List<FormField> layouts = JSONArray.parseArray(layoutStr, FormField.class);

		Set<String> ids = new HashSet<>();
		String dateStr = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		for (FormField formField : layouts) {
			formField.setEventId(event.getId());
			formField.setCreateTime(dateStr);
			String id = formField.getId();
			if(StringUtils.isEmpty(id)) { // 新加的字段
				String fieldId = formField.getFieldId();
				id = UUIDGenerator.getUUID();
				ids.add(id);
				formField.setId(id);
				if(StringUtils.isEmpty(fieldId)) {
					formField.setCustom(1);
					Map<String, Object> paramters = new HashMap<String, Object>();
					paramters.put("eventId", event.getId());
					int typeId = formField.getTypeId();
					paramters.put("typeId", typeId);
					List<Field> fields = eventMapper.getAvailableFields(paramters);
					if (CollectionUtils.isEmpty(fields)) {
						Field field = new Field();
						fieldId = UUIDGenerator.getUUID();
						field.setId(fieldId);
						while (true) {
							String nameStr = RandomUtil.random();
							Field check = eventMapper.getFieldByName(nameStr);
							if (null == check) {
								field.setName(nameStr);
								break;
							}
						}
						field.setTypeId(typeId);
						paramters = new HashMap<String, Object>();
						paramters.put("name", field.getName());
						paramters.put("type", typeId == 1 ? "text" : "VARCHAR");
						paramters.put("length", FieldConstant.getLength(typeId));
						eventMapper.addDBField(paramters);
						eventMapper.addField(field);
						formField.setFieldId(fieldId);
						formField.setEnglish(field.getName());
					} else {
						Field field = fields.get(0);
						formField.setFieldId(field.getId());
						formField.setEnglish(field.getName());
					}
					eventMapper.addFormField(formField);
				} else {
					eventMapper.addFormField(formField);
				}
			} else { // 之前已经存在的字段可能会修改
				ids.add(formField.getId());
				eventMapper.updateFormField(formField);
			}
		}
		Map<String, Object> params = new HashMap<>();
		params.put("ids", ids);
		params.put("eventId", event.getId());
		eventMapper.clearFormFields(params); // 清除之前选择过，但是编辑时又删除了的字段

		JSONArray jsonObj = (JSONArray) JSONObject.toJSON(layouts);
		event.setLayout(jsonObj.toJSONString());
	}

	@Override
	public List<Map<String, Object>> effectReport(String eventId) {
		return eventMapper.effectReport(eventId);
	}

	@Override
	public List<Map<String, Object>> inviteReport(String eventId) {
		return eventMapper.inviteReport(eventId);
	}

	@Override
	public Integer regCount(String eventId) {
		return eventMapper.regCount(eventId);
	}

	@Override
	public Map<String, Object> basicReport(String eventId, String dim, String startTime, String endTime) {
		// 如果传入的时间参数为空，则从活动报名时间中获取
		if(StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
			Map<String, Object> p = new HashMap<>();
			p.put("eventId", eventId);
			Event event = eventMapper.getEventById(p);
			String format = "yyyy-MM-dd HH:mm";
			if(StringUtils.isEmpty(startTime)) {
				startTime = DateUtil.formatDate(event.getRegisterStartTime(), format);
			}

			if(StringUtils.isEmpty(endTime)) {
				endTime = DateUtil.formatDate(event.getRegisterEndTime(), format);
			}
		}
		
		Map<String, Object> params = new HashMap<>();
		params.put("eventId", eventId);
		params.put("startTime", startTime);
		params.put("endTime", endTime);

		Map<String, Object> result = new HashMap<>();
		result.put("startTime", startTime);
		result.put("endTime", endTime);
		
		List<String> days = null;
		List<Map<String, Object>> chartDatas = null;
		Map<String, Object> totalMap = null;
		System.out.println(startTime + "===time22222=====" + endTime);
		if(dim == null || "day".equals(dim)) {
			days = getDays(startTime, endTime);
			List<Map<String, Object>> dayShowCount = eventMapper.dayShowCountReport(params);   // 浏览数
			System.out.println("dayShowCount111====" + dayShowCount);
			List<Map<String, Object>> daySignCount = eventMapper.daySignCountReport(params);   // 报名数
			List<Map<String, Object>> dayShareCount = eventMapper.dayShareCountReport(params); // 分享数
			System.out.println("dayShowCount222====" + dayShowCount);
			chartDatas = getMap(days);
			System.out.println("dayShowCount333====" + dayShowCount);
			getCountMap(dayShowCount, days, chartDatas, "showcount");
			getCountMap(daySignCount, days, chartDatas, "regcount");
			getCountMap(dayShareCount, days, chartDatas, "sharecount");
			totalMap = getConvertRate(chartDatas);
		} else if("month".equals(dim)) {
			days = getMonths(startTime, endTime);
			System.out.println("days2222====" + days);
			List<Map<String, Object>> monthShowCount = eventMapper.monthShowCountReport(params);   // 浏览数
			List<Map<String, Object>> monthSignCount = eventMapper.monthSignCountReport(params);   // 报名数
			List<Map<String, Object>> monthShareCount = eventMapper.monthShareCountReport(params); // 分享数

			chartDatas = getMap(days);
			System.out.println("chartDatasaa====" + chartDatas);
			getCountMap(monthShowCount, days, chartDatas, "showcount");
			getCountMap(monthSignCount, days, chartDatas, "regcount");
			getCountMap(monthShareCount, days, chartDatas, "sharecount");
			System.out.println("chartDatasccc====" + chartDatas);
			totalMap = getConvertRate(chartDatas);
			System.out.println("totalMapbbb====" + totalMap);
		}

		result.put("days", days);
		result.put("chartDatas", chartDatas);
		System.out.println("result111====" + result);
		if(totalMap != null) {
			result.putAll(totalMap);
		}
		System.out.println("result222====" + result);
		return result;
	}

	private List<String> getDays(String startTime, String endTime) {
		System.out.println(startTime + "===getDays111=====" + endTime);
		List<String> result = new ArrayList<String>();
		try {
			Date startDate = DateUtil.parseDate(startTime.substring(0, 10), DateUtil.YYYYMMDD1);
			Date endDate = DateUtil.parseDate(endTime.substring(0, 10), DateUtil.YYYYMMDD1);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
//			List<String> dates = new ArrayList<String>();
			System.out.println(calendar.getTimeInMillis() + "===getDays222=====" + endDate.getTime());
			while(calendar.getTimeInMillis() <= endDate.getTime()) {
				result.add(DateUtil.formatDate(calendar.getTime(), DateUtil.YYYYMMDD1));
				calendar.add(Calendar.DAY_OF_MONTH, 1); // 加1天
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private List<String> getMonths(String startTime, String endTime) {
		System.out.println(startTime + "===time3333=====" + endTime);
		List<String> result = new ArrayList<String>();
		try {
			String format = "yyyy-MM";
			Date startDate = DateUtil.parseDate(startTime.substring(0, 7), format);
			Date endDate = DateUtil.parseDate(endTime.substring(0, 7), format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
//			List<String> dates = new ArrayList<String>();
			System.out.println(calendar.getTimeInMillis() + "===time44444=====" + endDate.getTime());
			while(calendar.getTimeInMillis() <= endDate.getTime()) {
				result.add(DateUtil.formatDate(calendar.getTime(), format));
				calendar.add(Calendar.MONTH, 1); // 加1月
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private List<Map<String, Object>> getMap(List<String> days) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (String day : days) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("time", day);
			result.add(map);
		}
		return result;
	}

	private void getCountMap(List<Map<String, Object>> counts, List<String> days
			, List<Map<String, Object>> chartDatas, String dataType) {
		Map<String, Integer> tmpMap = new HashMap<String, Integer>();
		System.out.println("counts=======" + counts);
		for (Map<String, Object> map : counts) {
			Object obj = map.get("count");
			Integer count = null;
			if(obj != null) {
				if(obj instanceof Long) {
					count = ((Long)obj).intValue();
				} else if(obj instanceof Double) {
					count = ((Double)obj).intValue();
				} else if(obj instanceof Integer) {
					count = (Integer)obj;
				}
			}
			tmpMap.put((String)map.get("time"), count);
		}
		
		for (int i = 0; i < days.size(); i++) {
			Integer count = tmpMap.get(days.get(i));
			Map<String, Object> map = chartDatas.get(i);
			map.put(dataType, count == null ? 0 : count);
		}
	}

	/**
	 * 计算转化率(总转化率和每天/每月的转化率)
	 * @param chartDatas
	 */
	private Map<String, Object> getConvertRate(List<Map<String, Object>> chartDatas) {
		DecimalFormat df = new DecimalFormat("0.00");
		Integer showCountTotal = 0;
		Integer regCountTotal = 0;
		Integer shareCountTotal = 0;
		for (Map<String, Object> map : chartDatas) {
			Integer showCount = (Integer) map.get("showcount");
			Integer regCount = (Integer) map.get("regcount");
			Integer shareCount = (Integer) map.get("regcount");
			showCountTotal += showCount;
			regCountTotal += regCount;
			shareCountTotal += shareCount;
/*			String convertRate = regCount == 0 || showCount == 0 ? "0.00%" : (df.format((float) regCount / showCount * 100) + "%");
			map.put("convertrate", convertRate);*/
		}
		String convertRate = regCountTotal == 0 || showCountTotal == 0 ? 
				"0.00%" : (df.format((float) regCountTotal / showCountTotal * 100) + "%");
		
		Map<String, Object> result = new HashMap<>();
		result.put("showcount", showCountTotal);
		result.put("sharecount", shareCountTotal);
		result.put("regcount", regCountTotal);
		result.put("convertrate", convertRate);
		return result;
	}

	@Override
	public List<Map<String, Object>> findGalaxyRank(String eventId, String openId) {
		Map<String, Object> params = new HashMap<>();
		params.put("eventId", eventId);
		if(!StringUtils.isEmpty(openId)) {
			params.put("openId", openId);
		}
		return eventMapper.findGalaxyRank(params);
	}

}
