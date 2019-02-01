package solid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

enum Color {
	RED, GREEN, BLUE;
}

enum Size {
	SMALL, MEDIUM, LARGE;
}

class Product {
	public Size size;
	public String name;
	public Color color;

	public Product(Size size, String name, Color color) {
		this.size = size;
		this.name = name;
		this.color = color;
	}
}

// These interfaces are open for extension
interface Specification<T> {
	boolean isSatisfied(T item);
}

interface Filter<T> {
	Stream<T> filter(List<T> items, Specification<T> spec);
}


class ColorSpecification implements Specification<Product> {
	private Color color;

	public ColorSpecification(Color color) {
		this.color = color;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return item.color == color;
	}

}

class SizeSpecification implements Specification<Product> {
	private Size size;

	public SizeSpecification(Size size) {
		this.size = size;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return item.size == size;
	}

}

class AndSpecification<T> implements Specification<T> {
	private Specification<T> first, second;

	public AndSpecification(Specification<T> first, Specification<T> second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean isSatisfied(T item) {
		return first.isSatisfied(item) && second.isSatisfied(item);
	}

}

class ProductFilter implements Filter<Product> {

	@Override
	public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
		return items.stream().filter(p -> spec.isSatisfied(p));
	}

}

public class OCP {

	public static void main(String args[]) {
		Product ferrari = new Product(Size.LARGE, "ferrari", Color.BLUE);
		Product lamborghini = new Product(Size.MEDIUM, "lamborghini", Color.RED);
		Product oneplus = new Product(Size.SMALL, "5T", Color.RED);

		List<Product> productList = new ArrayList<Product>();
		productList.add(ferrari);
		productList.add(lamborghini);
		productList.add(oneplus);
		
		
		ProductFilter pf = new ProductFilter();
		pf.filter(productList, new ColorSpecification(Color.RED))
	      .forEach(p -> System.out.println(" - " + p.name + " is red"));


	    pf.filter(productList, new SizeSpecification(Size.LARGE))
	      .forEach(p -> System.out.println(" - " + p.name + " is large"));

	 
	    pf.filter(productList,
	      new AndSpecification<>(
	    	new SizeSpecification(Size.SMALL),
	        new ColorSpecification(Color.RED)       
	      ))
	      .forEach(p -> System.out.println(" - " + p.name + " is small and red"));

	}
}
