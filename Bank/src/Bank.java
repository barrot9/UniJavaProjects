import java.util.HashMap;
import java.util.Map;

public class Bank {
	public static void main(String[] args) {
		
		BankAcc Acc0 = new  BankAcc(0,0);
		BankAcc Acc1 = new  BankAcc(1,0);
		BankAcc Acc2 = new  BankAcc(2,0);
		BankAcc Acc3 = new  BankAcc(3,0);
		BankAcc Acc4 = new  BankAcc(4,0);
		
		Map<Integer, BankAcc> accountDatabase = new HashMap<>();
		accountDatabase.put(0, Acc0);
		accountDatabase.put(1, Acc1);
		accountDatabase.put(2, Acc2);
		accountDatabase.put(3, Acc3);
		accountDatabase.put(4, Acc4);
		
		
		Transaction[] transactionArray = {
	            new Transaction(0, (int) (Math.random() * 2001) - 1000),
	            new Transaction(1, (int) (Math.random() * 2001) - 1000),
	            new Transaction(2, (int) (Math.random() * 2001) - 1000),
	            new Transaction(3, (int) (Math.random() * 2001) - 1000),
	            new Transaction(4, (int) (Math.random() * 2001) - 1000),
	            new Transaction(0, (int) (Math.random() * 2001) - 1000),
	            new Transaction(1, (int) (Math.random() * 2001) - 1000),
	            new Transaction(2, (int) (Math.random() * 2001) - 1000),
	            new Transaction(3, (int) (Math.random() * 2001) - 1000),
	            new Transaction(4, (int) (Math.random() * 2001) - 1000),
	            new Transaction(0, (int) (Math.random() * 2001) - 1000),
	            new Transaction(1, (int) (Math.random() * 2001) - 1000),
	            new Transaction(2, (int) (Math.random() * 2001) - 1000),
	            new Transaction(3, (int) (Math.random() * 2001) - 1000),
	            new Transaction(4, (int) (Math.random() * 2001) - 1000),
	            new Transaction(0, 1000),
	            new Transaction(1, 1000),
	            new Transaction(2, 1000),
	            new Transaction(3, 1000),
	            new Transaction(4, 1000),
	            new Transaction(0, 1000),
	            new Transaction(1, 1000),
	            new Transaction(2, 1000),
	            new Transaction(3, 1000),
	            new Transaction(4, 1000)
	        };
		
		TransactionPool transactionPool = new TransactionPool(transactionArray);
		
		Thread[] cashiers = new Thread[10];
	    for (int i = 0; i < cashiers.length; i++) {
	        cashiers[i] = new BankCashier(transactionPool, accountDatabase, "cashier" +i);
	        cashiers[i].start();
	        System.out.println("cashier" + i + " started");
	    }

	    // Join all cashier threads
	    for (Thread cashier : cashiers) {
	        try {
	            cashier.join();
	        } catch (InterruptedException e) {
	            System.out.println("Thread interrupted: " + cashier.getName());
	        }
	    }
	    
	    for (Map.Entry<Integer, BankAcc> entry : accountDatabase.entrySet()) {
	        System.out.println("Account " + entry.getKey() + " final balance: " + entry.getValue().currentBalance());
	    }
		
	}
}
