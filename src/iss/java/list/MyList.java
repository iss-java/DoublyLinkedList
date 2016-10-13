package iss.java.list;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by wenke on 2016/9/16.
 * // TODO: Modify this class to meet requirement B and C.
 */
public class MyList {
    // two guards. Do not call remove() on them!!
    private Node head;
    private Node tail;
    private volatile int size;

    public MyList() {
        head = new Node(this).setData(0).setPrev(null);
        tail = new Node(this).setData(0).setNext(null).setPrev(head);
        head.setNext(tail);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    /**
     * To improve concurrency,
     * lock on each node and its previous node instead of locking a whole table.
     */
    public Node insert(Node _prev, int data) {
        if (_prev==tail)
            return tail;
        if (_prev.getOwner()!=this)
            return null;
        Node _next = _prev.getNext();
        Node newNode = new Node(this).setData(data).setNext(_next).setPrev(_prev);
        _prev.setNext(newNode);
        _next.setPrev(newNode);
        synchronized (this) {
            ++size;
        }
        return newNode;
    }

    /**
     * Remove the node <pre>target</pre>.
     *
     * @param target The node to remove.
     * @return the previous node of target.
     */
    public Node remove(Node target) {
        if (target == head || target == tail)
            return target;

        if (target == null)
            return null;

        /**
         * Validation of node against host.
         */
        if (target.getOwner()!=this)
            return null;


        Node prev = target.getPrev();
        Node next = target.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        /**
         * Set the owner to null, so that it won't be removed again
         */
        target.setOwner(null);
        synchronized (this) {
            --size;
        }
        return prev;
    }

    public String validate() {
        Node n;
        LinkedList<Integer> forw = new LinkedList<>();
        LinkedList<Integer> back = new LinkedList<>();
        for (n=head.getNext(); n!=tail; n=n.getNext()) {
            forw.addFirst(n.getData());
        }
        for (n=tail.getPrev(); n!=head; n=n.getPrev()) {
            back.addLast(n.getData());
        }
        boolean equal = true;
        if (forw.size()==back.size()){
            Iterator<Integer> itf = forw.iterator();
            Iterator<Integer> itb = back.iterator();
            while (itf.hasNext()) {
                int a = itf.next(), b = itb.next();
                if (a!=b)
                {
                    equal=false;
                    break;
                }
            }
        }
        else
            equal=false;

        return String.format("size=%d, forw.size=%d, back.size=%d, forw==back(%b)", size, forw.size(), back.size(), equal);
    }
}
