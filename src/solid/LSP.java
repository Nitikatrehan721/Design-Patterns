package solid;

/*
 * solution : use factory for object creation
 * */


class Rectangle {
	public int width, height;

	public Rectangle() {
	}

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getArea() {
		return height * width;
	}

	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + "]";
	}
}

class Square extends Rectangle {

	public Square() {
	}

	public Square(int size) {
		width = height = size;
	}

	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		super.setHeight(width);
	}

	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		super.setWidth(height);
	}

}

public class LSP {

	// since it is a rectangle we can expect width*10 but it will return 10*10
	static void useIt(Rectangle r) {
		int width = r.getWidth();
		r.setHeight(10);
		System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
	}

	public static void main(String args[]) {

		Rectangle sq = new Square();
		sq.setHeight(10);
		sq.setWidth(3);
		
		System.out.println(sq.getArea());
		useIt(sq);
				
	}
}
