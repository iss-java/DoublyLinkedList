package iss.java.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenke on 2016/9/16.
 * // TODO: Modify this class to meet requirement B and C.
 */
public class MyList {
    // two guards. Do not call remove() on them!!
    private Node head;
    private Node tail;
    private int size;

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
     * Insert a node with <pre>data</pre> after <pre>_prev</pre>.
     *
     * @param _prev
     * @param data
     * @return The node just inserted.
     */
    public Node insert(Node _prev, int data) {
        if (_prev==tail)
            return null;
        if (_prev.getHost()!=this)
            return null;
        _prev.lock();
        try {
            Node _next = _prev.getNext();
            _next.lock();

            try {
                Node newNode = new Node(this).setData(data).setNext(_next).setPrev(_prev);
                _prev.setNext(newNode);
                _next.setPrev(newNode);
                synchronized (this){
                    ++size;
                }
                return newNode;
            } finally {
                _next.unlock();
            }
        } finally {
            _prev.unlock();
        }
    }

    /**
     * Remove the node <pre>target</pre>.
     *
     * @param target The node to remove.
     * @return the previous node of target.
     */
    public Node remove(Node target) {
        if (target == head || target == tail)
            return null;

        /**
         * Validation of node against host.
         */
        if (target.getHost()!=this)
            return null;

        target.lock();
        Node prev = target.getPrev();
        Node next = target.getNext();
        target.unlock();
        prev.lock();
        try {
            next.lock();
            try {
                prev.setNext(next);
                next.setPrev(prev);
                synchronized (this) {
                    --size;
                }
                return prev;
            } finally {
                next.unlock();
            }
        } finally {
            prev.unlock();
        }
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
