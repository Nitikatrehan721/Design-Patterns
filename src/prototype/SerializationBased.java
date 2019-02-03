package prototype;

import java.io.Serializable;
// import org.apache.commons.lang3.SerializationUtils;

class DemoClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String a;
	private int b;

	public DemoClass(String a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "DemoClass [a=" + a + ", b=" + b + "]";
	}

}

public class SerializationBased {

	public static void main(String args[]) {
		DemoClass demoClass = new DemoClass("a", 5);
		// DemoClass demoClass2 = SerializationUtils.roundtrip(demoClass);
		//
		// demoClass2.a= "xyz";
		// System.out.println(demoClass2);
		System.out.println(demoClass);

	}
}
