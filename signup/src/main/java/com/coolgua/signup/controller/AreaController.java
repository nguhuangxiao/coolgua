package com.coolgua.signup.controller;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coolgua.signup.domain.City;
import com.coolgua.signup.domain.Country;
import com.coolgua.signup.domain.Province;
import com.coolgua.signup.service.AreaService;

@Controller
@RequestMapping("/area/*")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@RequestMapping("country")
	@ResponseBody
	public Map<String, Object> country() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", Boolean.FALSE);
		try {
			List<String> list = new ArrayList<String>();
			List<Country> countries = areaService.getCountries();
			for (Country country : countries) {
				StringBuffer sb = new StringBuffer();
				sb.append(country.getName()).append("|").append(country.getPinyin()).append("|")
						.append(country.getPinyinShort());
				list.add(sb.toString());
			}
			Collections.sort(list, Collator.getInstance(Locale.CHINA));
			list.add(0, "中国大陆|zhongguodalu|zgdl");
			list.add(1, "中国香港|zhongguoxianggang|zgxg");
			list.add(2, "中国澳门|zhongguoaomen|zgam");
			list.add(3, "中国台湾|zhongguotaiwan|zgtw");
			result.put("datas", list);
			result.put("success", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", "系统异常，请稍后重试");
		}
		return result;

	}

	@RequestMapping("province")
	@ResponseBody
	public Map<String, Object> province() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", Boolean.FALSE);
		try {
			List<String> list = new ArrayList<String>();
			List<Province> provinces = areaService.getProvinces();
			for (Province province : provinces) {
				StringBuffer sb = new StringBuffer();
				sb.append(province.getName()).append("|").append(province.getPinyin()).append("|")
						.append(province.getPinyinShort());
				list.add(sb.toString());
			}
			Collections.sort(list, Collator.getInstance(Locale.CHINA));
			result.put("datas", list);
			result.put("success", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", "系统异常，请稍后重试");
		}
		return result;

	}

	@RequestMapping("city")
	@ResponseBody
	public Map<String, Object> city(String name) {
		System.out.println("cityname===" + name);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", Boolean.FALSE);
		try {
			List<String> list = new ArrayList<String>();
			List<City> cities = areaService.getCities(name);
			System.out.println("citynamecities===" + cities.size());
			for (City city : cities) {
				StringBuffer sb = new StringBuffer();
				sb.append(city.getName()).append("|").append(city.getPinyin()).append("|")
						.append(city.getPinyinShort());
				list.add(sb.toString());
			}
			Collections.sort(list, Collator.getInstance(Locale.CHINA));
			result.put("datas", list);
			result.put("success", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", "系统异常，请稍后重试");
		}
		return result;
	}

}