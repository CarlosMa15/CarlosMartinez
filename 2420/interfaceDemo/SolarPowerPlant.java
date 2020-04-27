package interfaceDemo;

public class SolarPowerPlant extends PowerPlant{
	private String type;

	public SolarPowerPlant(int capacity, String type) {
		super(capacity);
		this.type = type;
	}
	
	@Override
	public void provideEnergy() {
		System.out.println("Provide Energy on a Large Scale From the Sun");
	}

	@Override
	public String toString() {
		return "SolarPowerPlant [type=" + type + "]";
	}
}
