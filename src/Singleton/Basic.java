package Singleton;


/*
 * Problems with basic singleton:
 * 1) we can beat this by using reflection (reflection doesnt care about private constructor) [Solution: using ENUM]
 * 2) using serialization and deserialization ( solution: provide hint to jvm while deserialization)
 * */

/*
 * ADD synchronized inorder to make it thread safe
 */


class BasicSingleton {

	private BasicSingleton() {
	}

	private static BasicSingleton INSTANCE;

	public static synchronized BasicSingleton getInstance() {
		if(INSTANCE == null){
			INSTANCE = new BasicSingleton();
		}
		return INSTANCE;
	}

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "BasicSingleton [value=" + value + "]";
	}

}

public class Basic {
	public static void main(String args[]) {

		BasicSingleton instance = BasicSingleton.getInstance();

		instance.setValue(5);

		BasicSingleton instance2 = BasicSingleton.getInstance();
		instance2.setValue(50);

		System.out.println(instance);
		System.out.println(instance2);
	}
}
