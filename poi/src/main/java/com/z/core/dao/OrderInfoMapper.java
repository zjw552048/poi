package com.z.core.dao;

import java.util.List;

import com.z.core.entity.OrderInfo;

public interface OrderInfoMapper {
	
	/**
	 * 插入数据至OrderInfo表
	 * @param bean
	 * @return
	 */
	int addOrderInfo(OrderInfo bean);
	
	/**
	 * 清空OrderInfo表
	 */
	void clearOrderInfo();
	
	/**
	 * 获取演出名列表
	 * @return
	 */
	List<String> getNameOfCommodityList();
	
	/**
	 * 根据演出名获取已售张数
	 */
	int getNumberOfTicketsSold(String nameOfCommodity);
	
	/**
	 * 根据演出吗获取实收额
	 */
	double getTotalActualPayment(String nameOfCommodity);
}
