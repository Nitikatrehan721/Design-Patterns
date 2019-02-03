package Singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// COMMENT OUT lines: 47-49 to see problem

/*
 * Problems with basic singleton:
 * 1) we can beat this by using reflection (reflection doesnt care about private constructor) [Solution: using ENUM]
 * 2) using serialization and deserialization ( solution: provide hint to jvm while deserialization)
 * */



class BasicSingleton1 implements Serializable{

	private static final long serialVersionUID = -3894661990042855849L;

	private BasicSingleton1() {
	}

	private final static BasicSingleton1 INSTANCE = new BasicSingleton1();

	public static BasicSingleton1 getInstance() {
		return INSTANCE;
	}

	private int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "BasicSingleton1 [value=" + value + "]";
	}
	
	protected Object readResolve(){
		return INSTANCE;
	}

}

public class BasicSingletonSerializationProblem {

	static void saveToFile(BasicSingleton1 instance, String filename) throws Exception {
		try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);) {
			out.writeObject(instance);
		}
	}

	static BasicSingleton1 readFromFile(String filename) throws Exception {
		try (FileInputStream fileOutputStream = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileOutputStream);) {
			return (BasicSingleton1) in.readObject();
		}
	}

	public static void main(String args[]) throws Exception {

		BasicSingleton1 instance = BasicSingleton1.getInstance();

		instance.setValue(5);

		String filename = "singletonObject.bin";
		saveToFile(instance, filename);

		BasicSingleton1 instance2 = readFromFile(filename);
		instance.setValue(50);
		System.out.println(instance);
	    System.out.println(instance2);
	}
}
