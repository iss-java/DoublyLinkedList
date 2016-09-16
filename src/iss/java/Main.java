package iss.java;

import iss.java.list.MyList;
import iss.java.list.Node;

/**
 * Created by wenke on 2016/9/16.
 * NOTE: This is an example of how to iterate MyList.
 */
public class Main {
    public static void main(String[] args) {
        MyList list = new MyList();
        list.insert(list.getHead(), 5);
        list.insert(list.getHead(), 4);
        list.insert(list.getHead(), 3);
        list.insert(list.getHead(), 2);
        list.insert(list.getHead(), 1);

        // Traverse from head to tail, exclude head and tail
        for (Node n = list.getHead().getNext(); n!=list.getTail(); n=n.getNext()) {
            System.out.printf("%d ", n.getData());
        }
        System.out.println();

        list.remove(list.getHead().getNext().getNext().getNext());

        // Traverse from tail to head, exclude tail and head.
        for (Node n = list.getTail().getPrev(); n!=list.getHead(); n=n.getPrev()) {
            System.out.printf("%d ", n.getData());
        }
        System.out.println();
    }
}
