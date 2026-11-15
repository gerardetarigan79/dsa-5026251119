
package lw05.prelab;

import java.util.*;
import java.io.*;

public class Main {

    static int size;
    static String[] names;
    static int[][] graph;
    static ArrayList<Integer> dfsOrder = new ArrayList<>();

    static void addEdge(int v1, int v2, int minutes) {
        graph[v1][v2] = minutes;
        graph[v2][v1] = minutes;
    }

    static void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int i = 0; i < size; i++) {
                if (graph[current][i] != 0 && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }

        return order;
    }

    static void dfs(int node, Set<Integer> visited) {
        visited.add(node);
        dfsOrder.add(node);

        for (int i = 0; i < size; i++) {
            if (graph[node][i] != 0 && !visited.contains(i)) {
                dfs(i, visited);
            }
        }
    }

    static int findTheNextNode(int[] dist, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int nextNode = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < minDistance) {
                minDistance = dist[i];
                nextNode = i;
            }
        }

        return nextNode;
    }

    static void route(int destination, int[] prev) {
        ArrayList<Integer> path = new ArrayList<>();
        int current = destination;

        while (current != -1) {
            path.add(current);
            current = prev[current];
        }

        Collections.reverse(path);
        printPath(path);
    }

    static void dijkstraAlg(int start, int destination) {
        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        dist[start] = 0;

        for (int i = 0; i < size; i++) {
            int current = findTheNextNode(dist, visited);

            if (current == -1) {
                break;
            }

            visited[current] = true;

            for (int j = 0; j < size; j++) {
                if (graph[current][j] != 0 && !visited[j]) {
                    int newDistance = dist[current] + graph[current][j];

                    if (newDistance < dist[j]) {
                        dist[j] = newDistance;
                        prev[j] = current;
                    }
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");

        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from " + names[start] + " to " + names[destination] + " ===");

        route(destination, prev);

        System.out.println("Total time: " + dist[destination] + " min");
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("campus.txt"));

        size = sc.nextInt();
        names = new String[size];
        graph = new int[size][size];

        for (int i = 0; i < size; i++) {
            names[i] = sc.next();
        }

        int e = sc.nextInt();

        for (int i = 0; i < e; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int minutes = sc.nextInt();

            addEdge(v1, v2, minutes);
        }

        int start = sc.nextInt();
        int destination = sc.nextInt();

        System.out.println("=== BFS from " + names[start] + " ===");
        printPath(bfs(start));

        System.out.println("=== DFS from " + names[start] + " ===");
        Set<Integer> visited = new HashSet<>();
        dfsOrder.clear();
        dfs(start, visited);
        printPath(dfsOrder);

        dijkstraAlg(start, destination);

        sc.close();
    }
}
