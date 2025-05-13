package main;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>(new Node<>(12, "sibi"));

        bst.put(4, "net");bst.put(4, "net");
        bst.put(8, "pal");
        bst.put(13, "sib-ssam");
        bst.put(9, "ku");
        bst.put(1, "hana");
        bst.put(3, "sam");
        bst.put(22, "i-sibi");
        System.out.println("bst의 root : "+bst.getRoot().getValue());

        BSTTraversal bstTraversal = new BSTTraversal(bst);

        List<String> list = bstTraversal.inOrder();
        for (String item : list) {
            System.out.printf(item + " ");
        }System.out.println();

        bst.delete(4);
        bst.delete(1);
        bst.delete(3);
        bst.delete(22);
        list = bstTraversal.inOrder();
        for (String item : list) {
            System.out.printf(item + " ");
        }System.out.println();

        BST<Integer, String> copied = (BST<Integer, String>) bst.copy();
        BSTTraversal copiedbstTraversal = new BSTTraversal(copied);
        List<String> colist =  copiedbstTraversal.inOrder();
        for (String item : colist) {
            System.out.printf(item + " ");
        }System.out.println();


    }
}

