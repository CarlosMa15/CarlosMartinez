package LabCoin;

public enum Coin {
	CENT(2.5,19.1),
	NICKEL(5,21.2),
	DIME(2.3,17.9),
	QUARTER(5.7,24.3);
	
	private double weight;
	private double diameter;
	
	private Coin(double weight, double diameter) {
		this.weight =weight;
		this.diameter = diameter;
	}
	
	@Override
	public String toString() {
		return super.toString() + " w:" + this.weight + " d:" + this.diameter;
	}
}