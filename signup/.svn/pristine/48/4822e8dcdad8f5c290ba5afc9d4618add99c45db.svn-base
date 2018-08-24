package com.coolgua.signup.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coolgua.signup.domain.Tickets;
import com.coolgua.signup.mapper.TicketMapper;
import com.coolgua.signup.service.TicketsService;

@Service
public class TicketsServiceImpl implements TicketsService {
	@Resource
	TicketMapper ticketMapper;

	@Override
	public void insertTicket(Tickets tickets) {
		String openId = tickets.getOpenId();
		String eventId = tickets.getEventId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", tickets.getOpenId());
		params.put("eventId", eventId);
		Tickets ticket = ticketMapper.findTicketByopenId(params);
		if(ticket == null) {
			ticketMapper.insertTicket(tickets);
		} else {
			System.out.println(openId + "==========已经生成过电子票，不应该生成======" + eventId);
		}
	}

	@Override
	public Tickets findTicketByopenId(String openId, String eventId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("eventId", eventId);
		return ticketMapper.findTicketByopenId(params);
	}

}
