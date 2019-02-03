package solid;

class Document {
}

/*
 * Create multiple client specific  interfaces. It's bad to have a single large interface as client has to 
 * implement all of them
 */


interface Printer {
	void print(Document d);
}

interface IScanner {
	void scan(Document d);
}

interface MultiPurposeDevice extends Printer, IScanner {
}

class MultiPurposeMachine implements MultiPurposeDevice {

	@Override
	public void print(Document d) {
	}

	@Override
	public void scan(Document d) {
	}

}

class JustPrinter implements Printer {

	@Override
	public void print(Document d) {
	}

}

public class ISP {

}
