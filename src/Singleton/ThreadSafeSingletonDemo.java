package Singleton;

/*
 * Problems with basic singleton:
 * 1) we can beat this by using reflection (reflection doesnt care about private constructor) [Solution: using ENUM]
 * 2) using serialization and deserialization ( solution: provide hint to jvm while deserialization)
 * */

/*
 * No need to add synchronized inorder to make it thread safe
 * create a inner class and mark it as static
 */

class ThreadSafeSingleton {

	private ThreadSafeSingleton() {
	}

	private static class Impl{
		private static ThreadSafeSingleton INSTANCE = new ThreadSafeSingleton();
	}

	public static ThreadSafeSingleton getInstance() {		
		return Impl.INSTANCE;
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
		return "ThreadSafeSingleton [value=" + value + "]";
	}

}

public class ThreadSafeSingletonDemo {
	public static void main(String args[]) {

		ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();

		instance.setValue(5);

		ThreadSafeSingleton instance2 = ThreadSafeSingleton.getInstance();
		instance2.setValue(50);

		System.out.println(instance);
		System.out.println(instance2);
	}
}
