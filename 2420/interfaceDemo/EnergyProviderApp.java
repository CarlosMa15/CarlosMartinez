package interfaceDemo;

public class EnergyProviderApp {

	public static void main(String[] args) {
		PowerPlant powerPlant = new PowerPlant(2);
		System.out.println("PowerPlant: " + powerPlant);
		powerPlant.provideEnergy();
		System.out.println();
		
		Food tacos = new Food(true);
		System.out.println("Food: " +  tacos);
		tacos.provideEnergy();
		System.out.println();
		
		SolarPowerPlant sun = new SolarPowerPlant(3, "photovoltaic");
		System.out.println(sun);
		sun.provideEnergy();
		System.out.println();
		
		EnergyProvider[] EP = {powerPlant, tacos, sun};
		
		for(EnergyProvider el: EP){
			el.provideEnergy();
		}
	}
}