package builder;
class Person{
	
	private String name;
	private int age;
	private int salary;
	
	private Person(PersonBuilder builder){
		name = builder.name;
		age = builder.age;
		salary = builder.salary;
	}
	
	public static class PersonBuilder{
		
		private String name;
		private int age;
		private int salary;
		
		public PersonBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public PersonBuilder setAge(int age) {
			this.age = age;
			return this;
		}
		
		public PersonBuilder setSalary(int salary) {
			this.salary = salary;
			return this;
		}
		
		public Person build(){
			return new Person(this);
		}
			
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Person [name=")
		.append(name)
		.append(", age=")
		.append(age)
		.append(", salary=")
		.append(salary)
		.append("]");
		
		return sb.toString();
	}
}


public class BuilderBasicDemo {
	
	
	public static void main(String [] args){
		Person person = new Person.PersonBuilder()
				.setAge(0)
				.setName("Peeyush")
				.setSalary(100)
				.build();
		
		System.out.print(person);
	}
}
