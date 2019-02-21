package nullObject;

import java.lang.reflect.Proxy;


/*
 * Performance impact will be there..
 * 
 *  what if we don't want to log info (line: 37) or what if someone wrote (line:47)
 *  solution: 
 * 1) put null check everywhere (line)
 * 2) create NullLog class with empty override methods
 */

interface Logging {
	void info(String msg);
}

class BankAccountInfo {
	private int balance = 0;
	private Logging log;

	public BankAccountInfo(Logging log) {
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

public class Dynamic {

	@SuppressWarnings("unchecked")
	public static <T> T noOp(Class<T> itf) {
		return (T) Proxy.newProxyInstance(
				itf.getClassLoader(), 
				new Class<?>[] { itf }, 
				(proxy, method, args) -> {
					if (method.getReturnType().equals(Void.TYPE))
						return null;
					else
						return method.getReturnType().getConstructor().newInstance();
				});
	}

	public static void main(String args[]) {
		Logging log = noOp(Logging.class);

		BankAccountInfo bankAccount = new BankAccountInfo(log);
		bankAccount.deposit(111);
	}
}
