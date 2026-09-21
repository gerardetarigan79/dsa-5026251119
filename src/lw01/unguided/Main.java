package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Rental[] rental = new Rental[100]; 
        int rentCount = 0;

        try (Scanner scanner = new Scanner(new File("src/lw01/unguided/rentals.txt"))) {
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                if (type.equalsIgnoreCase("Laptop")) {
                    rental[rentCount++] = new LaptopRental(id, days, units);
                } else if (type.equalsIgnoreCase("Projector")) {
                    rental[rentCount++] = new ProjectorRental(id, days, units);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: rentals.txt file not found.");
            return;
        }

        
        for (int i = 0; i < rentCount; i++) {
            System.out.println(rental[i].summary());
        }
    }
}