package memento;

/*
 * A token that represents a state of system that allows us to roll-back to a state when token was generated. 
 */

class Memento{
	private int value;
	
	Memento(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

class BankAccount{
	private int balance;

	public int getBalance() {
		return balance;
	}

	public Memento setBalance(int balance) {
		this.balance = balance;
		return new Memento(balance);
	}

	public void revert(Memento m){
		this.balance = m.getValue();
	}
}

public class Demo {

	public static void main(String args []){
		BankAccount bankAccount = new BankAccount();
		Memento m1 = bankAccount.setBalance(100);
		Memento m2 = bankAccount.setBalance(200);
		
		System.out.println(bankAccount.getBalance());
		bankAccount.revert(m1);
		System.out.println(bankAccount.getBalance());
		
	}
}
