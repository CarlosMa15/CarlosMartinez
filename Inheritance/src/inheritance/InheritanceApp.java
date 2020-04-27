/**********************************************
 * Author: Carlos Martinez
 * Date: Jan 28, 2017
 * Assignment: Inheritance
 *********************************************/
package inheritance;

public class InheritanceApp {

	public static void main(String[] args) {
		Rectangle myRectangle = new Rectangle(5,4);
		Square mySquare = new Square(4);
		IsoscelesRightTriangle myIsoscelesRightTriangle= new IsoscelesRightTriangle(5);
		Circle myCircle = new Circle(4);
		
		System.out.println(myRectangle);
		System.out.println("Length: " + myRectangle.getLength());
		System.out.println("Width: " + myRectangle.getWidth());
		System.out.println();
		System.out.println(mySquare);
		System.out.println("Side: " + mySquare.getSide());
		System.out.println();
		System.out.println(myIsoscelesRightTriangle);
		System.out.println("Leg: " + myIsoscelesRightTriangle.getLeg());
		System.out.printf("Hypotenuse: %.1f%n", myIsoscelesRightTriangle.hypotenuse());
		System.out.println();
		System.out.println(myCircle);
		System.out.println("Diameter: " + myCircle.diameter());
		System.out.printf("Circumference: %.1f%n",myCircle.circumference());
		System.out.println("Radius: " + myCircle.getRadius());
		System.out.println();
		
		Rectangle rectangle2 = mySquare;
		System.out.println("rectangle2:");
		System.out.println("-----------");
		System.out.println(rectangle2);
		System.out.println("Length: " + rectangle2.getLength());
		System.out.println("Width: " + rectangle2.getWidth());
		System.out.println();
		System.out.println("Rectangle Array:");
		System.out.println("----------------");
		
		Rectangle[] rectangles = {rectangle2,mySquare,myRectangle};
		
		for(Rectangle el: rectangles){
			System.out.println(el);
			System.out.println("Length: " + el.getLength());
			System.out.println("Width: " + el.getWidth());
			System.out.println();
		}
	}
}