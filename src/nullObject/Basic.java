package nullObject;

/*
 *  what if we don't want to log info (line: 37) or what if someone wrote (line:47)
 *  solution: 
 * 1) put null check everywhere (line)
 * 2) create NullLog class with empty override methods
 */

interface Log {
	void info(String msg);
}

final class NullLog implements Log {
	@Override
	public void info(String msg) {
	}
}

class ConsoleLog implements Log {
	@Override
	public void info(String msg) {
		System.out.println(msg);
	}
}

class BankAccount {
	private int balance = 0;
	private Log log;

	public BankAccount(Log log) {
		this.log = log;
	}

	public void deposit(int amount) {
		balance += amount;
		log.info("Deposited -" + amount);
	}
}

public class Basic {

	public static void main(String args[]) {
		ConsoleLog log = new ConsoleLog();
		BankAccount bankAccount = new BankAccount(log);
		bankAccount.deposit(111);
		// BankAccount bankAccount = new BankAccount(null);

		NullLog nullLog = new NullLog();
		BankAccount bankAccount1 = new BankAccount(nullLog);
		bankAccount1.deposit(111);

	}
}
