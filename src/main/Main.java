package main;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>(new Node<>(0, "a"));
        bst.put(123, "Hello");
//        for (int i=1; i<26; i++) {
//            bst.put(i, ""+((char)('a'+i)));
//        }
        bst.put(0, ""+'a');
        System.out.println("bst의 root : "+bst.getRoot().getValue());

        BSTTraversal<Integer, String> bstTraversal = new BSTTraversal(bst);
        System.out.println("\n최초 생성");
        List<String> list = bstTraversal.inOrder();
        for (String item : list) {
            System.out.printf(item + " ");
        }System.out.println();

        System.out.println("\n삭제 후");
        bst.delete(50);

        list = bstTraversal.inOrder();
        for (String item : list) {
            System.out.printf(item + " ");
        }System.out.println();

        System.out.println("\n복사본");
        BST<Integer, String> copied = (BST<Integer, String>) bst.copy();
        BSTTraversal<Integer, String> copiedbstTraversal = new BSTTraversal(copied);
        List<String> colist =  copiedbstTraversal.inOrder();
        for (String item : colist) {
            System.out.printf(item + " ");
        }System.out.println();


    }
}

