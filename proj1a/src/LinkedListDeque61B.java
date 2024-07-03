import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    public class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i) {
            this.item = i;
            this.next = null;
            this.prev = null;
        }
    }
    private Node sentinel;
    private int size;

    /* Constructor Function. */
    public LinkedListDeque61B() {
        size = 0;
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T x) {
        Node newFirst = new Node(x);
        Node first = sentinel.next;
        newFirst.next = first;
        first.prev = newFirst;
        sentinel.next = newFirst;
        newFirst.prev = sentinel;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newLast = new Node(x);
        Node last = sentinel.prev;
        newLast.prev = last;
        last.next = newLast;
        sentinel.prev = newLast;
        newLast.next = sentinel;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node currNode = sentinel;
        /* Note: we cannot use "currNode.next != null" as a loop condition here,
        * as DLList is a circular linked list and doesn't have null node at all.
        * */
        while (currNode.next != sentinel) {
            currNode = currNode.next;
            returnList.addLast(currNode.item);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        Node first = sentinel.next;
        Node newFirst = first.next;
        newFirst.prev = sentinel;
        sentinel.next = newFirst;
        size--;
        if (size > 0) {
            return newFirst.item;
        }
        return null;
    }

    @Override
    public T removeLast() {
        Node last = sentinel.prev;
        Node newLast = last.prev;
        newLast.next = sentinel;
        sentinel.prev = newLast;
        size--;
        if (size > 0) {
            return newLast.item;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return  null;
        }
        /* The sentinel node is excluded when calculating the index.
        * Index begins at zero.
        * */
        Node currNode = sentinel.next;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }

        return currNode.item;
    }

    public T getRecursiveFromSomeNode(Node someNode, int index) {
        /* Be careful of the base case. */
        if (someNode == sentinel) {
            return null;
        }
        if (index == 0) {
            return someNode.item;
        }
        return getRecursiveFromSomeNode(someNode.next, index - 1);
    }

    @Override
    public T getRecursive(int index) {
        return getRecursiveFromSomeNode(sentinel.next, index);
    }
}
