package observer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<T> {

	private int count = 0;
	private Map<Integer, Consumer<T>> handlers = new HashMap<>();

	public void fire(T args) {
		for (Consumer<T> handler : handlers.values()) {
			handler.accept(args);
		}
	}

	public Subscription addHandler(Consumer<T> handler) {
		int i = count;
		handlers.put(count++, handler);
		return new Subscription(this, i);
	}

	public class Subscription implements AutoCloseable {
		private Event<T> event;
		private int subscriptionId;

		Subscription(Event<T> event, int subscriptionId) {
			this.event = event;
			this.subscriptionId = subscriptionId;
		}

		@Override
		public void close() {
			event.handlers.remove(subscriptionId);

		}

	}
}

class PropertyChangedEvent {
	public Object source;
	public String propertyName;
	public Object newValue;

	public PropertyChangedEvent(Object source, String propertyName, Object newValue) {
		this.source = source;
		this.newValue = newValue;
		this.propertyName = propertyName;
	}
}

class PersonInfo {

	public Event<PropertyChangedEvent> propertyChanged = new Event<>();

	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (this.age == age)
			return;
		this.age = age;
		propertyChanged.fire(new PropertyChangedEvent(this, "age", age));
	}

}

public class ObserverImproved {

	public static void main(String args[]) {
		PersonInfo personInfo = new PersonInfo();
		Event<PropertyChangedEvent>.Subscription subscription = personInfo.propertyChanged.addHandler(x -> {
			System.out.println("Person's " + x.propertyName + " has changed to " + x.newValue);
		});
		personInfo.setAge(1);
		personInfo.setAge(2);
		subscription.close();
		personInfo.setAge(3);
	}
}
