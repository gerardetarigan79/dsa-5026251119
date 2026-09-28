package lw02.prelab;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        // Read file
        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("transactions.txt")
        );

        while(scanner.hasNext()){
            String[] transaction = new String[3];
            transaction[0] = scanner.next();
            transaction[1] = scanner.next();
            transaction[2] = scanner.next();
            transactions.add(transaction);
        }

        scanner.close();

        // LinkedList -> Queue
        queue.addAll(transactions);

        // Process transactions using FIFO
        while (!queue.isEmpty()) {

            String[] transaction = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            // Find customer
            String[] customer = null;

            for (String[] data : customers) {
                if (data[0].equals(name)) {
                    customer = data;
                    break;
                }
            }

            // Add new customer if not found
            if (customer == null) {
                customer = new String[]{name, "0"};
                customers.add(customer);
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {

                balance += amount;
                customer[1] = String.valueOf(balance);

            } else if (type.equals("WITHDRAW")) {

                if (amount <= balance) {

                    balance -= amount;
                    customer[1] = String.valueOf(balance);

                } else {

                    // Failed transaction -> Stack
                    failed.push(transaction);
                }
            }
        }

        // Final balances
        System.out.println("\n=== Final Balances ===");

        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        // Failed transactions
        System.out.println("\n=== Failed Transactions ===");

        while (!failed.isEmpty()) {

            String[] transaction = failed.pop();

            System.out.println(
                transaction[0] + " " +
                transaction[1] + " " +
                transaction[2]
            );
        }
    }
}