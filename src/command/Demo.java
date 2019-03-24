package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * it's a single object or combination of objects which represent an instruction that needs to be performed. 
 * A command typically contains all the information for a particular action to be taken.  
 * 
 * Note: maintain the status of command in-case of failure handling
 */

class BankAccount {
	private int balance;

	public int getBalance() {
		return balance;
	}

	public boolean deposit(int amount) {
		this.balance += amount;
		return true;
	}

	public boolean withdraw(int amount) {
		if(balance < amount){
			return false;
		}		
		this.balance -= balance;
		return true;
	}

	@Override
	public String toString() {
		return "BankAccount [balance=" + balance + "]";
	}

}

interface Command {
	
	void call();
	void undo();
}

enum Action {
	DEPOSIT, WITHDRAW;
}

class BankAccountCommand implements Command {

	private Action action;
	private int amount;
	private BankAccount account;
	private boolean succeded;

	public BankAccountCommand(BankAccount account, Action action, int amount) {
		this.account = account;
		this.action = action;
		this.amount = amount;
	}

	@Override
	public void call() {
		switch (action) {
		case DEPOSIT:
			succeded = account.deposit(amount);
			break;

		case WITHDRAW:
			succeded = account.withdraw(amount);
			break;
		}
	}

	@Override
	public void undo() {
		// important
		if(!succeded) return;
		
		switch (action) {
		case DEPOSIT:
			account.withdraw(amount);
			break;

		case WITHDRAW:
			account.deposit(amount);
			break;
		}

	}
}

public class Demo {

	public static void main(String args[]) {
		BankAccount bankAccount = new BankAccount();
		List<BankAccountCommand> commands = new ArrayList<>();

		commands.add(new BankAccountCommand(bankAccount, Action.DEPOSIT, 100));
		commands.add(new BankAccountCommand(bankAccount, Action.WITHDRAW, 100));
		commands.add(new BankAccountCommand(bankAccount, Action.DEPOSIT, 100));
		commands.add(new BankAccountCommand(bankAccount, Action.WITHDRAW, 150));

		for (BankAccountCommand c : commands) {
			c.call();
			System.out.println(bankAccount.toString());
		}

		Collections.reverse(commands);
		System.out.println("Undo Operations");
		
		for (BankAccountCommand c : commands) {
			c.undo();
			System.out.println(bankAccount.toString());
		}
	}

}
