package prototype;

class Address1{
	public String location;
	public int pinCode;
	
	public Address1(String location, int pinCode){
		this.location = location;
		this.pinCode = pinCode;
	}
	
	public Address1(Address1 other){
		this(other.location, other.pinCode);
	}

	@Override
	public String toString() {
		return "Address [location=" + location + ", pinCode=" + pinCode + "]";
	}
}

class Person1{
	public String name;
	public Address1 address;
	
	public Person1(String name, Address1 address){
		this.name = name;
		this.address = address;
	}
	
	public Person1(Person1 other){
		this(other.name, new Address1(other.address));
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + "]";
	}

}


public class CopyConstructor {
	
	public static void main (String args []) throws Exception{
		Address1 address = new Address1("Ujjain", 456010);
		Person1 person = new Person1("Peeyush", address);
		
		Person1 person2 = new Person1(person);
		person2.name = "Peeyush New";
		person2.address.pinCode = 560103;
		
		System.out.println(person);
		System.out.println(person2);		
	}

}

