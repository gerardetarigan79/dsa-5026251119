package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        
        try {
            Scanner scanner = new Scanner(new File("src/lw02/prelab/transactions.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    transactions.add(line.split("\\s+"));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("transactions.txt not found.");
            return;
        }

        LinkedList<String[]> customers = new LinkedList<>();
        for (String[] t : transactions) {
            String name = t[0];
            boolean found = false;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                customers.add(new String[]{name, "0"});
            }
        }

        Queue<String[]> queue = new LinkedList<>(transactions);
        Stack<String[]> failedStack = new Stack<>();

        while (!queue.isEmpty()) {
            String[] t = queue.poll();
            String name = t[0];
            String type = t[1];
            int amount = Integer.parseInt(t[2]);

            String[] customer = null;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }

            if (customer != null) {
                int balance = Integer.parseInt(customer[1]);
                if (type.equals("DEPOSIT")) {
                    balance += amount;
                    customer[1] = String.valueOf(balance);
                } else if (type.equals("WITHDRAW")) {
                    if (amount > balance) {
                        failedStack.push(t);
                    } else {
                        balance -= amount;
                        customer[1] = String.valueOf(balance);
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + ": " + c[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] f = failedStack.pop();
            System.out.println(f[0] + " " + f[1] + " " + f[2]);
        }
    }
}