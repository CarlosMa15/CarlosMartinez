package interfaceDemo;

public class Food implements EnergyProvider{
	private boolean delicious;

	public Food(boolean delicious) {
		this.delicious = delicious;
	}

	@Override
	public void provideEnergy() {
		System.out.println("Provides Calories for Humans");
	}

	@Override
	public String toString() {
		return "Food [ delicious = " + delicious+ "]";
	}
}