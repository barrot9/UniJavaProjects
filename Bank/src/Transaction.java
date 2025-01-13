//class to represent a single transaction
public class Transaction {
	//transaction variables
	private final int accountNumber;
	private final int value;
	
	//transaction constructor
	public Transaction (int accountNumber, int value) {
		this.accountNumber = accountNumber;
		this.value = value;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public int getValue() {
		return value;
	}
	
	//method to return desired modified string of the object transaction.
	@Override 
	public String toString() {
		return "Transaction in account:" + accountNumber + " of value: "+ value;
	}

}
