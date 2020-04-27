package labWrapperClassGui;

public class DemoWrapperClass {
	
	public String minMax() {
		String minMax = "Byte:\nMin: " + Byte.MIN_VALUE+ "\nMax: " + Byte.MAX_VALUE + "\n\n"
				+ "Short:\nMin: " + Short.MIN_VALUE + "\nMax: " + Short.MAX_VALUE 
				+ "\n\nInter:\nMin: " + Integer.MIN_VALUE + "\nMax: " + Integer.MAX_VALUE + "\n"
				+ "\nLong:\nMin: " + Long.MIN_VALUE + "\nMax: " + Long.MAX_VALUE;
		return minMax; // TODO
	}
	
	public String toBinary(int number) {
		String toBinary = "Binary: " + Integer.toBinaryString(number) + "\nOctal : " + 
				Integer.toOctalString(number)+ "\nHex   : " + Integer.toHexString(number);
		return toBinary;
	}
	
	public String charProperties(char c) {
		String letter = "false";
		
		if(Character.isLetter(c)) {
			String oppositeLetter = "";
			char oppositeChar = 'a';
			
			if (Character.isUpperCase(c)) {
				 oppositeChar = Character.toLowerCase(c);
			}
			else {
				oppositeChar = Character.toUpperCase(c);
			}
			
			letter = c + "|"+ oppositeChar;
		}
		
		String charProperties = "White space: " + Character.isWhitespace(c) + "\nDigit: "
				+ Character.isDigit(c) + "\nLetter: " + letter + "\n";
		return charProperties;
	}
	
}