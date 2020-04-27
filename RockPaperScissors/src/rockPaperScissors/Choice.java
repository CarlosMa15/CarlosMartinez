/******************************************
 * Author: Carlos Martinez
 * Date: March 25, 2017
 * Assignment: Rock Paper Scissors
 *****************************************/
package rockPaperScissors;

import javax.swing.ImageIcon;

/**
 * This is an Enum that holds the choices of Rock
 * Paper and Scissors for the GUI to use
 * @author Carlos Martinez
 */
public enum Choice {
	ROCK(new ImageIcon(RockPaperScissorsGUI.class.getResource("/rockPaperScissors/Images/Rock.png")),1),
	PAPER(new ImageIcon(RockPaperScissorsGUI.class.getResource("/rockPaperScissors/Images/Paper.png")),2),
	SCISSORS(new ImageIcon(RockPaperScissorsGUI.class.getResource("/rockPaperScissors/Images/Scissors.png")),3);
	
	/**
	 * This is the image of the choice
	 */
	private ImageIcon image;
	
	/**
	 * This is a value representing the choice
	 */
	private int value;
	
	/**
	 * This constructor creates an object of Choice
	 * @param image the image of the Choice
	 * @param value the value representing the choice
	 */
	private Choice(ImageIcon image, int value) {
		this.image = image;
		this.value =value;
	}
	
	/**
	 * This Method compares the two Choices and returns
	 * a String that tells who won the game
	 * @param otherChoice the the other Choice 
	 * @return a String telling who won
	 */
	public String evaluates(Choice otherChoice) {
		if(this.value == otherChoice.value) {
			return "No Winner";
		}
		
		if(this.value == 1 && otherChoice.value == 2) {
			return "Computer Won";
		}
		if(this.value == 2 && otherChoice.value == 1) {
			return "You Won";
		}
		
		if(this.value == 3 && otherChoice.value == 1) {
			return "Computer Won";
		}
		if(this.value == 1 && otherChoice.value == 3) {
			return "You Won";
		}
		
		if(this.value == 3 && otherChoice.value == 2) {
			return "You Won";
		}
		if(this.value == 2 && otherChoice.value == 3) {
			return "Computer Won";
		}
		
		return "Option Not Covered"; // for debuging
	}
	
	/**
	 * The method returns the ImageIcon of the Choice
	 * @return returns the ImageIcon of the Choice
	 */
	public ImageIcon getImageIcon(){
		return this.image;
	}
}