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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
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

/*
 * It is all about building some sort of no object that conforms to the required
 * interface and satisfies a dependency requirement but doesn't do a single
 * thing. it just doesn't do anything it's an empty object. But it does satisfy
 * the interface and it can be passed in instead of NULL and invocations on it
 * don’t cause any exceptions. Motivation: 1. When component A uses B, it
 * assumes B is not null - We inject B, not Option<B> - We do not check for null
 * on every call 2. There is no option of telling A not to use an instance of B
 * - Its mostly hard coded
 *
 * Solution : Build no-op, non-functioning inheritor of B(or some interface that
 * B implements and pass it to A
 */