import java.util.LinkedList;
import java.util.Queue;

class Transaction{
    String action;
    int amount;

    public Transaction(String action, int amount) {
        this.action = action;
        this.amount = amount;
    }
}
public class TransactionProcess{
    public static void main(String[] args) {
        System.out.println("Enter transactions:");
        String transationsInput= "D 400 | W 300 | W 700 | D 200 | D 450 | W 120 ";

        Queue<Transaction> newTransaction = new LinkedList<>();
        int initialBalance = 500;
        int currentBalance = initialBalance;

        String[] transcationList = transationsInput.split("\\|");

        for (String transaction:transcationList) {

            transaction =transaction.trim();

            String[] parts = transaction.split(" ");
            String action = parts[0];
            int amount = Integer.parseInt(parts[1]);

            Transaction t = new Transaction(action,amount);
            newTransaction.add(t);

            System.out.print(action + " " + amount + " --> ");
        }
        System.out.println();
        System.out.println("Initial Balance: " + initialBalance);

        while(!newTransaction.isEmpty()) {
            Transaction transaction = newTransaction.poll();

            if (transaction.action.equals("D")) {
                currentBalance += transaction.amount;
                System.out.println("Deposit " + transaction.amount + "  New Balance " + currentBalance);
            } else if (transaction.action.equals("W")) {
                if (transaction.amount <= currentBalance) {
                    currentBalance -= transaction.amount;
                    System.out.println("Withdraw " + transaction.amount + "  New Balance " + currentBalance);
                } else {
                    System.out.println("Withdraw" + transaction.amount + "  Rejected");
                }
            }
        }
    }
}
