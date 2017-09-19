package com.z.core.service;

import java.util.List;

import com.z.core.entity.OrderInfo;

public interface OrderInfoService {
	
	int addOrderInfo(OrderInfo bean);
	
	void clearOrderInfo();
	
	List<String> getNameOfCommodityList();
	
	int getNumberOfTicketsSold(String nameOfCommodity);
}
