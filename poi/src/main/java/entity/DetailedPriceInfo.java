package entity;

public class DetailedPriceInfo {
	//单价
	private double price;
	//数量
	private int num;
	
	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	@Override
	public String toString() {
		return "PriceTotalInfo [price=" + price + ", num=" + num + "]";
	}
	
}
