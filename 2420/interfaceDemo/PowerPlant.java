package interfaceDemo;

public class PowerPlant implements EnergyProvider{
	/**
	 * The energy provided in GigaWatts
	 */
	private int capacity;
	
	public PowerPlant(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public void provideEnergy() {
		System.out.println("Generating Electricity on a Large Scale");	
	}

	@Override
	public String toString() {
		return "PowerPlant [capacity = " + capacity + "]";
	}
}