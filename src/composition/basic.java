package composition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GraphicObject {
	public String objName = "Group";
	public String color;
	public List<GraphicObject> children = new ArrayList<>();

	public GraphicObject() {
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String name) {
		this.objName = name;
	}

	private void print(StringBuilder stringBuilder, int depth) {

		stringBuilder.append(String.join("", Collections.nCopies(depth, "*"))).append(depth > 0 ? " " : "")
				.append((color == null || color.isEmpty()) ? "" : color + " ").append(getObjName())
				.append(System.lineSeparator());

		for (GraphicObject child : children)
			child.print(stringBuilder, depth + 1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		print(sb, 0);
		return sb.toString();
	}
}

class Circle extends GraphicObject {
	public Circle(String color) {
		objName = "Circle";
		this.color = color;
	}
}

class Square extends GraphicObject {
	public Square(String color) {
		objName = "Square";
		this.color = color;
	}
}

public class basic {

	public static void main(String args[]) {
		GraphicObject drawing = new GraphicObject();
		drawing.setObjName("Drawing Starts");
		drawing.children.add(new Square("Green"));
		drawing.children.add(new Circle("Brown"));

		GraphicObject group = new GraphicObject();
		group.children.add(new Circle("Blue"));
		group.children.add(new Square("Blue"));
		drawing.children.add(group);

		System.out.println(drawing);
	}

}
