package observer;

import java.util.List;
import java.util.ArrayList;

class PropertyChangedEventArgs<T> {

	public T source;
	public String propertyName;
	public Object newValue;

	PropertyChangedEventArgs(T source, String propertyName, Object newValue) {
		this.source = source;
		this.propertyName = propertyName;
		this.newValue = newValue;
	}
}

interface Observer<T> {
	void handle(PropertyChangedEventArgs<T> args);
}

class Observables<T> {
	private List<Observer<T>> observers = new ArrayList<>();

	public void subscribe(Observer<T> observer) {
		observers.add(observer);
	}

	public void propertyChanged(T source, String propertyName, Object newValue) {
		for (Observer<T> o : observers)
			o.handle(new PropertyChangedEventArgs<T>(source, propertyName, newValue));
	}
}

class Person extends Observables<Person>{
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if(this.age == age) return;
		this.age = age;
		propertyChanged(this, "age", age);
	}
}


// Observer
public class ObserverDemo implements Observer<Person> {

	public static void main(String args[]){
		Person person = new Person();
		
		ObserverDemo observerDemo = new ObserverDemo();		
		person.subscribe(observerDemo);
		
		for(int i=0; i< 5; i++){
			person.setAge(i);
		}
	}

	@Override
	public void handle(PropertyChangedEventArgs<Person> args) {
		System.out.println(args.propertyName + " has changed to " + args.newValue);
		
	}
}
