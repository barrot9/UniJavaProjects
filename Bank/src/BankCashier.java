import java.util.Map;

public class BankCashier extends Thread {
    private final TransactionPool transactionPool; // Pool of transactions to process
    private final Map<Integer, BankAcc> accountDatabase; // Map of account numbers to bank accounts

    //bank cashier constructor
    public BankCashier(TransactionPool transactionPool, Map<Integer, BankAcc> accountDatabase, String name) {
        super(name); // Set the cashier name
        this.transactionPool = transactionPool; // Initialize the transaction pool
        this.accountDatabase = accountDatabase; // Initialize the account database
    }

    @Override
    public void run() {
        while (true) {
            // Pull the next transaction from the pool
            Transaction transaction = transactionPool.pullTransaction();
            
            // Exit if no transactions are left
            if (transaction == null) {
                System.out.println(getName() + " found no more transactions. Finishing work.");
                break;
            }

            // Find the account for the transaction
            BankAcc account = accountDatabase.get(transaction.getAccountNumber());
            
            if (account != null) {
                // Process the transaction if the account exists
                System.out.println(getName() + " processing transaction: " + transaction);
                account.transaction(transaction.getValue());
            } else {
                // Log if the account is not found
                System.out.println(getName() + " could not find account: " + transaction.getAccountNumber());
            }

            // Simulate processing delay
            try {
                Thread.sleep(50); // Sleep for 50ms
            } catch (InterruptedException e) {
                // Handle thread interruption and exit the loop
                Thread.currentThread().interrupt();
                System.out.println(getName() + " was interrupted.");
                break;
            }
        }
    }
}
