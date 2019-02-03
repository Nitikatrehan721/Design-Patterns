package Singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * ENUM constructor is by default private we can't make it public 
 * IF we serialize enum it will only serialize it's name i.e INSTANCE not the other fields
 * 
 * value specified in Constructor is taken IFF we don't call setValue()
 */

enum Singleton {
	INSTANCE;

	Singleton() {
		value = 43;
	}

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

public class EnumBasedSingleton {
	static void saveToFile(Singleton instance, String filename) throws Exception {
		try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);) {
			out.writeObject(instance);
		}
	}

	static Singleton readFromFile(String filename) throws Exception {
		try (FileInputStream fileOutputStream = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileOutputStream);) {
			return (Singleton) in.readObject();
		}
	}
	
	public static void main(String args[]) throws Exception {

		Singleton instance = Singleton.INSTANCE;
		instance.setValue(5);

		String filename = "enumSingleton.bin";
		saveToFile(instance, filename);

		Singleton instance2 = readFromFile(filename);
		instance2.setValue(50);
		System.out.println(instance.getValue());
	    System.out.println(instance2.getValue());
	}
}
