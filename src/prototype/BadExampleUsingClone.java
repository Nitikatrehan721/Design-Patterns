package prototype;

class Address implements Cloneable{
	public String location;
	public int pinCode;
	
	public Address(String location, int pinCode){
		this.location = location;
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Address [location=" + location + ", pinCode=" + pinCode + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Address(location, pinCode);
	}
}

class Person implements Cloneable {
	public String name;
	public Address address;
	
	public Person(String name, Address address){
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + "]";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Person(name, (Address) address.clone());
	}
}


public class BadExampleUsingClone {
	
	public static void main (String args []) throws Exception{
		Address address = new Address("Ujjain", 456010);
		Person person = new Person("Peeyush", address);
		
		Person person2 = (Person) person.clone();
		person2.name = "Peeyush New";
		person2.address.pinCode = 560103;
		
		System.out.println(person);
		System.out.println(person2);		
	}

}
