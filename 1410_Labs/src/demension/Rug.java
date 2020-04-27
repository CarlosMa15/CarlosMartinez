package demension;

/**
 * The rug class represents a rug. It provides a method to
 * Calculate the area of the rug. 
 * @author Carlos Martinez
 */
public class Rug {
	
	//Fields
	private Dimension length;
	private Dimension width;
	
	//Constructor
	/**
	 * Constructs a new Rug object based on the dimensions of length and width
	 * @param length Length of the rug
	 * @param width Width of the rug
	 */
	public Rug(Dimension length, Dimension width) {
		this.length = length;
		this.width = width;
	}
    //Methods
	
	/**
	 * Return the area of the rug in square feet.
	 * @return the area of the rug
	 */
	public double area(){
		return length.asInches() * width.asInches() / 144d;
	}
	@Override
	public String toString() {
		return length + " x " + width;
	}
}