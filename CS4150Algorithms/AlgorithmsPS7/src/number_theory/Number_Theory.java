/*************************************************************
 * Author: Carlos Martinez
 * Date: October 20, 2018
 * Assignment: Number Theory
 ************************************************************/
package number_theory;

import java.util.Scanner;

/**
 * This class does the Number Theory from Kattis assignment
 * @author Carlos Martinez
 *
 */
public class Number_Theory {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Process all the commands
		while (input.hasNextLine()) {
			String[] commandArguments = input.nextLine().split(" ");
			String command = commandArguments[0];
			
			//Does the the greatest common divisor
			if (command.equals("gcd")) {
				int numberA = Integer.parseInt(commandArguments[1]);
				int numberB = Integer.parseInt(commandArguments[2]);
				System.out.println(gcd(numberA, numberB));
			} 
			
			// does the (number^exponent) % mod
			else if (command.equals("exp")) {
				int number = Integer.parseInt(commandArguments[1]);
				int exponent = Integer.parseInt(commandArguments[2]);
				int mod = Integer.parseInt(commandArguments[3]);
				System.out.println(exp(number, exponent, mod));
			} 
			
			//does the (number ^ -1) % mod
			else if (command.equals("inverse")) {
				int number = Integer.parseInt(commandArguments[1]);
				int mod = Integer.parseInt(commandArguments[2]);
				System.out.println(modInverse(number, mod));
			} 
			
			//does the isprime
			else if (command.equals("isprime")) {
				int number = Integer.parseInt(commandArguments[1]);
				System.out.println(isPrime(number));
			} 
			
			//does the RSA
			else {
				int numberP = Integer.parseInt(commandArguments[1]);
				int numberQ = Integer.parseInt(commandArguments[2]);
				System.out.println(rsa(numberP, numberQ));
			}
		}
	}
	
	/**
	 * This method finds the the greatest common divisor
	 * @param numberA the first number
	 * @param numberB the second number
	 * @return the greatest common divisor of the given 2 numbers
	 */
	public static long gcd(long numberA, long numberB) {
		
		if (numberB == 0) {
			return numberA;
			
		}
		
		return gcd(numberB, numberA % numberB);
	}

	/**
	 * Print (x^y) mod N, which must be non-negative and less than N
	 * @param number the number
	 * @param exponent the exponent
	 * @param mod the mod
	 * @return (number^exponent) % mod
	 */
	public static int exp(int number, int exponent, int mod) {
		long x = 1;
		long y = number;
		
		while (exponent > 0) {
			if (exponent % 2 == 1) {
				x = (x * y) % mod;
			}
			y = (y * y) % mod;
			exponent /= 2;
		}
		
		return ((int) x % mod);
	}
	
	/**
	 * Print (a^−1) mod N, which must be positive and less than N.
	 * If the inverse does not exist, print “none”.
	 * @param number the number
	 * @param mod the mod
	 * @return (number ^ -1) % mod
	 */
	public static String modInverse(long number, long mod) {
		long i = mod, v = 0, d = 1;
		
		if (gcd(number, mod) != 1) {
			return "none";
		}
		
		while (number > 0) {
			long t = i / number, x = number;
			number = i % x;
			i = x;
			x = d;
			d = v - t * x;
			v = x;
		}
		
		v %= mod;
		
		if (v < 0) {
			v = (v + mod) % mod;
		}
		
		return v + "";
	}
	
	/**
	 * This method takes a number and returns the answer
	 * of weather the number is prime or not
	 * @param number the number
	 * @return is the number prime yes or no
	 */
	public static String isPrime(int number) {
		
		if (number < 2) {
			return "no";
		}
		
		if (number == 2) {
			return "yes";
		}
		
		if (number % 2 == 0) {
			return "no";
		}
		
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0) {
				return "no";
			}
		}
		
		return "yes";
	}
	
	/**
	 * Print the modulus, public exponent,
	 * and private exponent of the RSA key pair derived from p and q.
	 * The public exponent must be the smallest positive integer that works; 
	 * q must be positive and less than N.
	 * @param numberP the first number
	 * @param numberQ the second number
	 * @return Print the modulus, public exponent,
	 * and private exponent of the RSA key pair derived from p and q.
	 * The public exponent must be the smallest positive integer that works; q 
	 * must be positive and less than N.
	 */
	public static String rsa(long numberP, long numberQ)
	{
		long product = numberP * numberQ;
		long Euler = (numberP - 1) * (numberQ - 1);
		long exponent = 0;
		
		for(int index = 2; index <= Euler; index++)
		{
			if(gcd(index, Euler) == 1)
			{
				exponent = index;
				break;
			}
		}
		
		long modInverse = Long.parseLong(modInverse(exponent, Euler));
		
		return product + " " + exponent + " " + modInverse;
	}
}