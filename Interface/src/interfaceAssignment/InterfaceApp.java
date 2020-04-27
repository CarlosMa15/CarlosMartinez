/************************************************
 * Author: Carlos Martinez
 * Date: February 5, 2017
 * Assignment: Interface
 ***********************************************/

package interfaceAssignment;

public class InterfaceApp {

	public static void main(String[] args) {
		Shape[] shapes = { new Rectangle(6,3) , new Rectangle(5,4)
				,new Square(4), new Square(3), new IsoscelesRightTriangle(5)
				,new IsoscelesRightTriangle(3), new Circle(7), new Circle(2)};
		
		System.out.println("Shape Array:");
		System.out.println("------------");
		
		for(Shape el: shapes){
			System.out.println(el);
			System.out.printf("Perimeter: %.1f%n",el.perimeter());
			System.out.printf("Area: %.1f%n",el.area());
			
			if(el instanceof Printable){
				((Printable) el).print();
			}
			
			System.out.println();
		}
	}
}