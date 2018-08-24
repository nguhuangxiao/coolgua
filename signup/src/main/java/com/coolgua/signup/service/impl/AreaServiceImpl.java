package com.coolgua.signup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolgua.signup.domain.City;
import com.coolgua.signup.domain.Country;
import com.coolgua.signup.domain.Province;
import com.coolgua.signup.mapper.AreaMapper;
import com.coolgua.signup.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaMapper areaMapper;

	@Override
	public List<Country> getCountries() {
		return areaMapper.getCountries();
	}

	@Override
	public List<Province> getProvinces() {
		return areaMapper.getProvinces();
	}

	@Override
	public List<City> getCities(String name) {
		return areaMapper.getCities(name);
	}

}
