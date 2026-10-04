package lw03.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> playlist = new ArrayList<>();
        
        try (Scanner sc1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"))) {
            while (sc1.hasNext()) {
                String op = sc1.next();
                if (op.equals("ADD")) {
                    String song = sc1.nextLine().trim();
                    playlist.add(song);
                } else if (op.equals("INSERT")) {
                    int index = sc1.nextInt();
                    String song = sc1.nextLine().trim();
                    playlist.add(index, song);
                } else if (op.equals("REMOVE")) {
                    String song = sc1.nextLine().trim();
                    playlist.remove(song); 
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading playlist.txt: " + e.getMessage());
        }

        System.out.println("===== Problem 1");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println();

        Set<String> uniqueParticipants = new LinkedHashSet<>();
        int duplicateCount = 0;

        try (Scanner sc2 = new Scanner(Main.class.getResourceAsStream("participants.txt"))) {
            while (sc2.hasNextLine()) {
                String name = sc2.nextLine().trim();
                if (name.isEmpty()) continue;
                
                boolean added = uniqueParticipants.add(name);
                if (!added) {
                    duplicateCount++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading participants.txt: " + e.getMessage());
        }

        System.out.println("Problem 2 =====");
        System.out.println("Unique participants: " + uniqueParticipants.size());
        int participantIndex = 1;
        for (String participant : uniqueParticipants) {
            System.out.println(participantIndex + ". " + participant);
            participantIndex++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);

        System.out.println();

        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        try (Scanner sc3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"))) {
            while (sc3.hasNext()) {
                String type = sc3.next();
                String product = sc3.next();
                int quantity = sc3.nextInt();

                if (type.equals("ADD")) {
                    inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
                } else if (type.equals("SELL")) {
                    if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                        inventory.put(product, inventory.get(product) - quantity);
                    } else {
                        failedSales++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading inventory.txt: " + e.getMessage());
        }

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}   