package solid;

import java.util.ArrayList;
import java.util.List;

//A. High-level modules should not depend on low-level modules. Both should depend on abstractions.
//B. Abstractions should not depend on details. Details should depend on abstractions.


// abstraction through which high level and low level modules will interact
interface Developer {
	void develop();
}

// low level module
class BackendDeveloper implements Developer {

	@Override
	public void develop() {
		writeInJava();

	}

	private void writeInJava() {
		System.out.println("in Java");
	}

}

//low level module
class FrontEndDeveloper implements Developer {

	@Override
	public void develop() {
		writeInReact();

	}

	private void writeInReact() {
		System.out.println("in React");
	}

}

//high level module
// interacting with developers through interfaces/abstractions rather than concrete class
class Project {
	private List<Developer> developers;

	public Project(List<Developer> developers) {
		this.developers = developers;
	}

	public void implement() {
		developers.forEach(d -> d.develop());
	}
}

public class DIP {

	public static void main(String args[]) {
		BackendDeveloper backendDeveloper = new BackendDeveloper();
		FrontEndDeveloper frontEndDeveloper = new FrontEndDeveloper();
		
		List<Developer> developers = new ArrayList<>();
		developers.add(backendDeveloper);
		developers.add(frontEndDeveloper);
		
		
		Project project = new Project(developers);
		project.implement();
	}
}
