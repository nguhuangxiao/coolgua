package com.coolgua.signup.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolgua.signup.domain.Sequence;
import com.coolgua.signup.mapper.SequenceMapper;
import com.coolgua.signup.service.SequenceService;

@Service("sequenceService")
public class SequenceServiceImpl implements SequenceService {

	@Autowired
	private SequenceMapper sequenceMapper;
	
	@Override
	@Transactional
	public Integer nextInt(String eventId) {
		//先查这条此条记录是否存在，不存在则新建 ，存在则加1返回
		Sequence seq = sequenceMapper.getSequence(eventId);
		if (seq == null) {
//			int seqNum = new Random().nextInt(10);
			int seqNum = 1;
			Map<String, Object> param = new HashMap<>();
			param.put("eventId", eventId);
			param.put("seq", seqNum);
			sequenceMapper.add(param);
			return seqNum;
		} else {
			int result = seq.getSeq() + 1;
			Map<String, Object> param = new HashMap<>();
			param.put("eventId", eventId);
			param.put("seq", result);
			sequenceMapper.update(param);
			return result;
		}
	}

}
