package solid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Journal {
	private final List<String> entries = new ArrayList<>();

	public void addEntries(String text) {
		entries.add(text);
	}

	public void removeEntry(int index) {
		entries.remove(index);
	}

	@Override
	public String toString() {
		return String.join(System.lineSeparator(), entries);
	}

}

class Persistence {

	public void load(Journal journal, String fileName) {

	}

	public void saveFile(Journal journal, String filename, Boolean overwrite) throws FileNotFoundException {
		if (overwrite || new File(filename).exists()) {
			try (PrintStream out = new PrintStream(filename)) {
				out.println(journal.toString());
			}
		}
	}
}

public class SRP {

	public static void main(String args[]) throws Exception {

		Journal journal = new Journal();
		journal.addEntries("This is the beginning");
		journal.addEntries("This is the medium");
		journal.addEntries("This is the ending");

		System.out.println(journal);
		
		Persistence persistence = new Persistence();
		String filename = "journal.txt";
		persistence.saveFile(journal, filename, true);
	}

}
