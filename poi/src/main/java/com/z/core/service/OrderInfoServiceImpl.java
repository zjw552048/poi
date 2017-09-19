package com.z.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.z.core.dao.OrderInfoMapper;
import com.z.core.entity.OrderInfo;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	@Override
	public int addOrderInfo(OrderInfo bean) {
		return orderInfoMapper.addOrderInfo(bean);
	}

	@Override
	public List<String> getNameOfCommodityList() {
		return orderInfoMapper.getNameOfCommodityList();
	}

	@Override
	public void clearOrderInfo() {
		orderInfoMapper.clearOrderInfo();
	}

	@Override
	public int getNumberOfTicketsSold(String nameOfCommodity) {
		return orderInfoMapper.getNumberOfTicketsSold(nameOfCommodity);
	}

}
