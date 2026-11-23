package lw06.unguided;

import java.util.Scanner;

public class Main {
    
    public static Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        }

        return node;
    }

    public static void preOrderHelper(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    public static void inOrderHelper(Node node) {
        if (node != null) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }

    public static void postOrderHelper(Node node) {
        if (node != null) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }

    public static boolean search(Node node, int target) {
        if (node == null) {
            return false;
        }
        if (target == node.data) {
            return true;
        }
        if (target < node.data) {
            return search(node.left, target);
        }
        return search(node.right, target);
    }

    public static int findMinHelper(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Cannot find the minimum of an empty tree");
        }
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    public static int findMaxHelper(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Cannot find the maximum of an empty tree");
        }
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        Node root = null;
       
        String[] numbers = sc.nextLine().trim().split(" ");

        for (String number : numbers) {
            int data = Integer.parseInt(number);
            root = insert(root, data);
        }

        int target = sc.nextInt();

        System.out.print("Preorder : ");
        preOrderHelper(root);
        System.out.println();

        System.out.print("Inorder : ");
        inOrderHelper(root);
        System.out.println();
        
        System.out.print("Postorder : ");
        postOrderHelper(root);
        System.out.println();
        
        System.out.println("Search " + target + " : " + search(root, target));

        System.out.println("Minimum : " + findMinHelper(root));
        System.out.println("Maximum : " + findMaxHelper(root));



        sc.close();
    }
}

