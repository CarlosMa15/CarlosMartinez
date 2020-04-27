package unitTesting;

public class TeasureChest {
	
	//Fields
	int gold;

	public TeasureChest(int gold) {
		this.gold = gold;
	}

	public int getGold() {
		return gold;
	}

	@Override
	public String toString() {
		return "(" + gold + ")";
	}
	
	/**
	 * 
	 * @param amount
	 * @return returns true if the amount of gold was removed
	 * or returns false if the amount was not removed because 
	 * the amount is greater than the gold available.
	 */
	public boolean remove(int amount) {
		if(0 > amount) {
			throw new IllegalArgumentException("The amount has to be "
					+ "positive");
		}
		
		if(amount > gold){
			return false;
		}
		
		gold -= amount;
		return false;
	}
	
	public boolean addGold(int amount){
		if(amount< 0)
			throw new IllegalArgumentException("The amount has to be "
					+ "positive");
		
		gold += amount;
		return true;
	}
	
}