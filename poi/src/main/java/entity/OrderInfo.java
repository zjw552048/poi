package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单类
 * @author Z
 *
 */
public class OrderInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 下单来源
	private String sourceOfOrder;
	// 内部订单号
	private String orderId;
	// 用户订单号
	private String customOrderId;
	// 下单日期
	private Date dateOfOrder;
	// 发货日期
	private Date dateOfIssuance;
	// 订单状态 0=交易关闭 1=已完成
	private int statusOfOrder;
	// 支付方式
	private String methodOfPayment;
	// 支付状态 0=未支付 1=已支付
	private int statusOfPayment;
	// 配送方式 
	private String methodOfDistribution;
	// 收货人
	private String consignee;
	// 收货人手机号
	private String phoneNumberOfConsignee;
	// 下单人手机号
	private String phoneNumberOfCustom;
	// 下单会员等级折扣
	private String discountGrade;
	// 订单会员权益
	private String perksOfMembershi;
	// 商品名称
	private String nameOfCommodity;
	// 场次Id
	private String performanceId;
	// 场次名称
	private String nameOfPerformance;
	// 演出馆厅
	private String ieldOfPerformance;
	// 演出日期
	private Date dateOfPerformance;
	// 票价
	private double priceOfTicket;
	// 张数
	private int numberOfTicket;
	// 应收
	private double shouldPayment;
	// 实收=应收*折扣
	private double actualPayment;
	
	public String getSourceOfOrder() {
		return sourceOfOrder;
	}


	public void setSourceOfOrder(String sourceOfOrder) {
		this.sourceOfOrder = sourceOfOrder;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getCustomOrderId() {
		return customOrderId;
	}


	public void setCustomOrderId(String customOrderId) {
		this.customOrderId = customOrderId;
	}


	public Date getDateOfOrder() {
		return dateOfOrder;
	}


	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}


	public Date getDateOfIssuance() {
		return dateOfIssuance;
	}


	public void setDateOfIssuance(Date dateOfIssuance) {
		this.dateOfIssuance = dateOfIssuance;
	}


	public int getStatusOfOrder() {
		return statusOfOrder;
	}


	public void setStatusOfOrder(int statusOfOrder) {
		this.statusOfOrder = statusOfOrder;
	}


	public String getMethodOfPayment() {
		return methodOfPayment;
	}


	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}


	public int getStatusOfPayment() {
		return statusOfPayment;
	}


	public void setStatusOfPayment(int statusOfPayment) {
		this.statusOfPayment = statusOfPayment;
	}


	public String getMethodOfDistribution() {
		return methodOfDistribution;
	}


	public void setMethodOfDistribution(String methodOfDistribution) {
		this.methodOfDistribution = methodOfDistribution;
	}


	public String getConsignee() {
		return consignee;
	}


	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}


	public String getPhoneNumberOfConsignee() {
		return phoneNumberOfConsignee;
	}


	public void setPhoneNumberOfConsignee(String phoneNumberOfConsignee) {
		this.phoneNumberOfConsignee = phoneNumberOfConsignee;
	}


	public String getPhoneNumberOfCustom() {
		return phoneNumberOfCustom;
	}


	public void setPhoneNumberOfCustom(String phoneNumberOfCustom) {
		this.phoneNumberOfCustom = phoneNumberOfCustom;
	}


	public String getDiscountGrade() {
		return discountGrade;
	}


	public void setDiscountGrade(String discountGrade) {
		this.discountGrade = discountGrade;
	}


	public String getPerksOfMembershi() {
		return perksOfMembershi;
	}


	public void setPerksOfMembershi(String perksOfMembershi) {
		this.perksOfMembershi = perksOfMembershi;
	}


	public String getNameOfCommodity() {
		return nameOfCommodity;
	}


	public void setNameOfCommodity(String nameOfCommodity) {
		this.nameOfCommodity = nameOfCommodity;
	}


	public String getPerformanceId() {
		return performanceId;
	}


	public void setPerformanceId(String performanceId) {
		this.performanceId = performanceId;
	}


	public String getNameOfPerformance() {
		return nameOfPerformance;
	}


	public void setNameOfPerformance(String nameOfPerformance) {
		this.nameOfPerformance = nameOfPerformance;
	}


	public String getIeldOfPerformance() {
		return ieldOfPerformance;
	}


	public void setIeldOfPerformance(String ieldOfPerformance) {
		this.ieldOfPerformance = ieldOfPerformance;
	}


	public Date getDateOfPerformance() {
		return dateOfPerformance;
	}


	public void setDateOfPerformance(Date dateOfPerformance) {
		this.dateOfPerformance = dateOfPerformance;
	}


	public double getPriceOfTicket() {
		return priceOfTicket;
	}


	public void setPriceOfTicket(double priceOfTicket) {
		this.priceOfTicket = priceOfTicket;
	}


	public int getNumberOfTicket() {
		return numberOfTicket;
	}


	public void setNumberOfTicket(int numberOfTicket) {
		this.numberOfTicket = numberOfTicket;
	}


	public double getShouldPayment() {
		return shouldPayment;
	}


	public void setShouldPayment(double shouldPayment) {
		this.shouldPayment = shouldPayment;
	}


	public double getActualPayment() {
		return actualPayment;
	}


	public void setActualPayment(double actualPayment) {
		this.actualPayment = actualPayment;
	}


	@Override
	public String toString() {
		return "Order [sourceOfOrder=" + sourceOfOrder + ", orderId=" + orderId + ", customOrderId=" + customOrderId
				+ ", dateOfOrder=" + dateOfOrder + ", dateOfIssuance=" + dateOfIssuance + ", statusOfOrder="
				+ statusOfOrder + ", methodOfPayment=" + methodOfPayment + ", statusOfPayment=" + statusOfPayment
				+ ", methodOfDistribution=" + methodOfDistribution + ", consignee=" + consignee
				+ ", phoneNumberOfConsignee=" + phoneNumberOfConsignee + ", phoneNumberOfCustom=" + phoneNumberOfCustom
				+ ", discountGrade=" + discountGrade + ", perksOfMembershi=" + perksOfMembershi + ", nameOfCommodity="
				+ nameOfCommodity + ", performanceId=" + performanceId + ", nameOfPerformance=" + nameOfPerformance
				+ ", ieldOfPerformance=" + ieldOfPerformance + ", dateOfPerformance=" + dateOfPerformance
				+ ", priceOfTicket=" + priceOfTicket + ", numberOfTicket=" + numberOfTicket + ", shouldPayment="
				+ shouldPayment + ", actualPayment=" + actualPayment + "]";
	}

}
