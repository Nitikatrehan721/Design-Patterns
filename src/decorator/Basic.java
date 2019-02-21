package decorator;

interface Shape {
	public String info();
}

class Circle implements Shape{
	
	@Override
	public String info(){
		return "Shape is a circle.";
	}
}

// decorating shape,  adding color functionality
class ColoredShape implements Shape{
	
	private String color;
	private Shape shape;
	
	public ColoredShape(Shape shape, String color){
		this.shape = shape;
		this.color = color;
	}
	
	@Override
	public String info(){
		return shape.info() + "It's color is " + color;
	}
}

public class Basic {
	
	public static void main(String args[]){
		Shape circle = new Circle();
		
		Shape coloredCircle = new ColoredShape(circle, "RED");
		System.out.println(coloredCircle.info());
	}
	
	
}
