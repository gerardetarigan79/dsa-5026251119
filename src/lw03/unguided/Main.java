package lw03.unguided;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Map<String, Integer> enrollments = new HashMap<>();
        List<String> courseOrder = new LinkedList<>();
        List<String> checkResults = new LinkedList<>();
        int rejectedOperations = 0;

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("enrollment.txt")
        );

        while (scanner.hasNext()) {
            String operation = scanner.next();

            if (operation.equals("CHECK")) {
                String courseCode = scanner.next();
                
                if (enrollments.containsKey(courseCode)) {
                    checkResults.add(courseCode + ": " + enrollments.get(courseCode) + " students");
                } else {
                    checkResults.add(courseCode + ": Not found");
                }

            } else {
                String courseCode = scanner.next();
                int count = Integer.parseInt(scanner.next());

                if (count <= 0) {
                    rejectedOperations++;
                    continue;
                }

                if (operation.equals("REGISTER")) {
                    if (!enrollments.containsKey(courseCode)) {
                        enrollments.put(courseCode, count);
                        courseOrder.add(courseCode); 
                    } else {
                        int currentCount = enrollments.get(courseCode);
                        enrollments.put(courseCode, currentCount + count);
                    }

                } else if (operation.equals("WITHDRAW")) {
                    if (enrollments.containsKey(courseCode)) {
                        int currentCount = enrollments.get(courseCode);
                        
                        if (currentCount >= count) {
                            enrollments.put(courseCode, currentCount - count);
                        } else {
                            rejectedOperations++;
                        }
                    } else {
                        rejectedOperations++;
                    }
                }
            }
        }

        scanner.close();

        System.out.println("===== Enrollment Checks =====");
        for (String result : checkResults) {
            System.out.println(result);
        }

        System.out.println("");

        System.out.println("===== Final Enrollment =====");
        for (String courseCode : courseOrder) {
            System.out.println(courseCode + ": " + enrollments.get(courseCode) + " students");
        }

        System.out.println("");

        System.out.println("Rejected operations: " + rejectedOperations);
    }
}