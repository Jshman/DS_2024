package main;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>(new Node<>("sibi", 12));

        bst.put("net", 4);bst.put("net", 4);
        bst.put("pal", 8);
        bst.put("sib-ssam", 13);
        bst.put("ku", 9);
        bst.put("hana", 1);
        bst.put("sam", 3);
        bst.put("i-sibi", 22);
        System.out.println("bst의 root : "+bst.getRoot().getValue());

        BSTTraversal bstTraversal = new BSTTraversal(bst);

        List<Integer> list = bstTraversal.inOrder();
        for (Integer item : list) {
            System.out.printf(item + " ");
        }System.out.println();

        bst.delete("net");
        bst.delete("hana");
        bst.delete("sam");
        bst.delete("i-sibi");
        list = bstTraversal.inOrder();
        for (Integer item : list) {
            System.out.printf(item + " ");
        }System.out.println();

        BST<String, Integer> copied = (BST<String, Integer>) bst.copy();
        BSTTraversal copiedbstTraversal = new BSTTraversal(copied);
        List<Integer> colist =  copiedbstTraversal.inOrder();
        for (Integer item : colist) {
            System.out.printf(item + " ");
        }System.out.println();


    }
}

