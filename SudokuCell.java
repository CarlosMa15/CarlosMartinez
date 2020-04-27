/*************************************************
 * Authors: Carlos Martinez and Patrick Leishman
 * Date: April 15, 2017
 * Assignment: Team Project
 * Description: Sudoku
 ************************************************/
package sudoku;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Textfield that only accepts a single digit. Used for populating sudoku board
 * and allowing user input.
 * 
 * @author Patrick Leishman Carlos Martinez
 */
public class SudokuCell extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4119144069513570997L;
	final static int cols = 1;

	private int solution;

	/**
	 * constructor. if you want to change values of all the text fields on the
	 * board to it here.
	 */
	public SudokuCell() {
		super(cols);
		this.setFont(new Font("Tahoma", Font.PLAIN, 24));
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setForeground(Color.RED);
	}

	public int getSolution() {
		return solution;
	}

	public void setSolution(int solution) {
		this.solution = solution;
	}

	protected Document createDefaultModel() {
		return new singleIntOnlyDocument();
	}

	/**
	 * Formatting class to limit input size and type to one digit
	 * 
	 * @author Patrick Leishman
	 *
	 */
	static class singleIntOnlyDocument extends PlainDocument {

		private static final long serialVersionUID = 7686380791622476463L;

		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

			if (str == null) {
				return;
			}
			char[] input = str.toCharArray();
			input[0] = input[input.length - 1];
			String digit = Character.toString(input[0]);
			if (Character.isDigit(input[0]) && super.getLength() < 1) {
				super.insertString(offs, new String(digit), a);
			}
		}
	}
}