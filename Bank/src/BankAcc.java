public class BankAcc extends Thread {
    
    // Account number and balance variables
    private final int accountNumber; 
    private int accountBalance;
   
    // Constructor to initialize account number and balance
    public BankAcc(int number, int balance) {
        accountNumber = number; // Initialize account number
        accountBalance = balance; // Initialize account balance
    }
    
    // Synchronized method to handle transactions
    public synchronized void transaction(int value) {
        // Check if the transaction is a withdrawal and if balance is insufficient
        while (value < 0 && accountBalance + value < 0) {
            try {
                // Inform that the account balance is too low
                System.out.println("Account balance is too low to perform transaction of " + value + " from account: " + accountNumber);
                // Wait for another transaction to potentially increase balance
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Transaction incomplete.");
            }
        }
        // Update the account balance after the transaction
        accountBalance += value; 
        // Print transaction success and new balance
        System.out.println("Transaction of : " + value + " on account: " + accountNumber + " was successful.");
        System.out.println("New account balance: " + accountBalance);
        notifyAll();
    }
    
    // Synchronized method to return the current balance of the account
    public synchronized int currentBalance() {
        return accountBalance;
    }
}
