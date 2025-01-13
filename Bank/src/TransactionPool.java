import java.util.LinkedList;
import java.util.Queue;


public class TransactionPool {
    private final Queue<Transaction> transactions; // Queue to store transactions

    
    //Initializes the pool with an array of transactions.
    public TransactionPool(Transaction[] transactionArray) {
        transactions = new LinkedList<>(); // Initialize the queue
        for (Transaction transaction : transactionArray) {
            transactions.add(transaction); // Add transactions to the queue
        }
    }

    
    //Pulls a transaction from the pool, or returns null if empty.
    public synchronized Transaction pullTransaction() {
        if (transactions.isEmpty()) {
            return null; // Return null if the queue is empty
        }
        Transaction transaction = transactions.poll(); // Remove and retrieve the next transaction
        System.out.println(Thread.currentThread().getName() + " pulled: " + transaction); // Log transaction
        return transaction;
    }

    //method to return if the transaction pool is empty
    public synchronized boolean isEmpty() {
        return transactions.isEmpty(); // Return true if queue is empty
    }
}
