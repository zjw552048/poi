package com.z.util;

public class StatusUtil {
	/**
	 * 判断订单状态
	 * 0=交易关闭 
	 * 1=已完成
	 * @param statusOfOrderString
	 * @return
	 */
	public static int getStatusOfOrder(String statusOfOrderString){
		int statusOfOrder = -999;
		switch (statusOfOrderString) {
		case "未完成":
			statusOfOrder = -2;
			break;
		case "系统关闭":
			statusOfOrder = -1;
			break;
		case "用户取消":
			statusOfOrder = 0;
			break;
		case "已完成":
			statusOfOrder = 1;
			break;
		default:
			break;
		}
		return statusOfOrder;
	}
	
	/**
	 * 判断支付状态
	 * 0=未支付 
	 * 1=已支付
	 * @param statusOfPaymentString
	 * @return
	 */
	public static int getStatusOfPayment(String statusOfPaymentString){
		int statusOfPayment = -999;
		switch (statusOfPaymentString) {
		case "未支付":
			statusOfPayment = 0;
			break;
		case "已支付":
			statusOfPayment = 1;
			break;
		default:
			break;
		}
		return statusOfPayment;
	}
	
	public static void main(String[] args) {
		System.out.println(getStatusOfOrder("已完成"));
	}
}
