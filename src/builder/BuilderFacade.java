package builder;

class PersonInfo {
	// address
	public String streetAddress, postcode, city;

	// employment
	public String companyName, position;
	public int annualIncome;

	@Override
	public String toString() {
		return "Person{" + "streetAddress='" + streetAddress + '\'' + ", postcode='" + postcode + '\'' + ", city='"
				+ city + '\'' + ", companyName='" + companyName + '\'' + ", position='" + position + '\''
				+ ", annualIncome=" + annualIncome + '}';
	}
}

class PersonBuilder {

	protected PersonInfo person = new PersonInfo();

	public PersonJobBuilder works() {
		return new PersonJobBuilder(person);
	}

	public PersonAddressBuilder lives() {
		return new PersonAddressBuilder(person);
	}
	
	public PersonInfo build(){
		return person;
	}
}

class PersonAddressBuilder extends PersonBuilder {
	
	public PersonAddressBuilder(PersonInfo person) {
		this.person = person;
	}

	public PersonAddressBuilder at(String streetAddress) {
		person.streetAddress = streetAddress;
		return this;
	}

	public PersonAddressBuilder withPostcode(String postcode) {
		person.postcode = postcode;
		return this;
	}

	public PersonAddressBuilder in(String city) {
		person.city = city;
		return this;
	}
}

class PersonJobBuilder extends PersonBuilder {

	public PersonJobBuilder(PersonInfo person) {
		this.person = person;
	}

	public PersonJobBuilder at(String companyName) {
		person.companyName = companyName;
		return this;
	}

	public PersonJobBuilder asA(String position) {
		person.position = position;
		return this;
	}

	public PersonJobBuilder earning(int annualIncome) {
		person.annualIncome = annualIncome;
		return this;
	}
}

public class BuilderFacade {

	public static void main(String[] args) {

		PersonInfo person = new PersonBuilder()
			.lives()
				.at("streetAddress")
				.in("city")
			.works()
				.asA("position")
				.earning(10)
			.build();
		
		System.out.println(person);
	}
}
