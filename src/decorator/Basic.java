package decorator;

/* Dynamic Decorator */

public class Basic {

	interface Shape {
		public String info();
	}

	class Circle implements Shape {

		@Override
		public String info() {
			return "Shape is a circle.";
		}
	}

	// decorating shape, adding color functionality
	class ColoredShape implements Shape {

		private String color;
		private Shape shape;

		public ColoredShape(Shape shape, String color) {
			this.shape = shape;
			this.color = color;
		}

		@Override
		public String info() {
			return shape.info() + "Its color is " + color;
		}
	}

	class TransparentShape implements Shape {
		
		private Shape shape;
		private int transparency;

		public TransparentShape(Shape shape, int transparency) {
			this.shape = shape;
			this.transparency = transparency;
		}

		@Override
		public String info() {
			return shape.info() + " and has " + transparency + "% transparency";
		}
	}

	public static void main(String args[]) {
		Basic basic = new Basic();
		Shape circle = basic.new Circle();

		Shape coloredCircle = basic.new ColoredShape(circle, "RED");
		System.out.println(coloredCircle.info());
		
		Shape transparentColoredCircle = basic.new TransparentShape(coloredCircle, 49);
		System.out.println(transparentColoredCircle.info());
	}

}

/*
 * Decorator design pattern facilitates the addition of behaviors to individual
 * objects without directly inheriting from them.
 * 
 * Motivation
 * 
 * 1. We want to augment existing objects with additional functionality. 2. you
 * don't want to go into those classes and kind of rewrite them or change their
 * existing code (open closed principle violation). 3. want to keep the new
 * functionality entirely separate (Single Responsibility Principle) 4. two
 * choices two options on how to do this. a) First of all if the class isn't
 * final then you can just inherit from that class and then you can build on top
 * of that. b) decorator
 * 
 */
