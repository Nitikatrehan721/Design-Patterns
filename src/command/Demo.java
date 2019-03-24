package command;

class BankAccount {
	private int balance;

	public int getBalance() {
		return balance;
	}

	public void deposit(int balance) {
		this.balance += balance;
	}

	public void withdraw(int balance) {
		this.balance -= balance;
	}
}

interface Command {
	void call();
}

enum Action {
	DEPOSIT, WITHDRAW;
}

class BankAccountCommand implements Command {

	private Action action;
	private int amount;
	private BankAccount account;

	public BankAccountCommand(BankAccount account, Action action, int amount) {
		this.account = account;
		this.action = action;
		this.amount = amount;
	}

	@Override
	public void call() {
		switch (action) {
		case DEPOSIT: {
			account.deposit(amount);
			break;
		}
		case WITHDRAW: {
			account.withdraw(amount);
			break;
		}

		}

	}

}

public class Demo {
	
	public static void main(String args[]){
		
	}

}
