
package lw04.unguided;

import java.util.*;

public class Main {
    static void selectionSort(int[] arr, int passes) {
        for (int i = 0; i < passes && i < arr.length; i++) {
            int idx = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[idx]) {
                    idx = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
    }

    static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt"));

        int n = sc.nextInt();
        int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        selectionSort(cards, 2);

        for (int i = 0; i < n; i++) {
            System.out.print(cards[i]);
            if (i < n - 1) System.out.print(" ");
        }
        System.out.println();

        for (int t : targets) {
            int idx = linearSearch(cards, t);

            if (idx != -1) {
                System.out.println(t + ": FOUND at index " + idx);
            } else {
                System.out.println(t + ": NOT FOUND");
            }
        }
    }
}
