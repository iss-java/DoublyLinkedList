package iss.java.list;

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
        head = new Node().setData(0);
        tail = new Node().setData(0).setNext(null).setPrev(head);
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
        Node node = new Node().setData(data).setNext(_prev.getNext()).setPrev(_prev);
        _prev.getNext().setPrev(node);
        _prev.setNext(node);
        ++size;
        return node;
    }

    /**
     * Remove the node <pre>target</pre>.
     *
     * @param target The node to remove.
     * @return the previous node of target.
     */
    public Node remove(Node target) {
        if (target == head || target == tail)
            throw new RuntimeException("DO NOT remove the head or tail node. They are guards.");
        // shortcut "target"
        Node prev = target.getPrev();
        Node next = target.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        --size;

        // Unlike C/C++, the memory of "target" is automatically recycled by GC
        // return the previous one because it is quite likely to insert a new node after prev;
        return prev;
    }

}
