package by.it.group251002.nikonorova.lesson11; // элементы хранятся в связном списке


import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    static class Node<E> {
        E data;
        public Node<E> next;
        public Node<E> after, prev;

        Node(E e) {
            data = e;
        }
    }

    final private int defaultSize = 64;

    private Node<E>[] set;
    private Node<E> head, tail;
    private int size = 0;

    private int getHash(Object o) {
        return (o.hashCode() & 0x7FFFFFFF) % set.length;
    }

    MyLinkedHashSet() {
        set = new Node[defaultSize];
    }

    MyLinkedHashSet(int size) {
        set = new Node[size];
    }

    private void resize() {

        Node<E>[] newMap = new Node[set.length * 2 + 1];

        for (Node<E> node : set) {
            Node<E> cur = node;

            while (cur != null) {
                Node<E> next = cur.next;

                int hash = (cur.data.hashCode() & 0x7FFFFFFF) % newMap.length;
                cur.next = newMap[hash];
                newMap[hash] = cur;

                cur = next;
            }
        }
        set = newMap;
    }

    private void addNode(Node<E> node) {

        if (head == null) {
            head = node;
        } else {
            tail.after = node;
            node.prev = tail;
        }

        tail = node;
    }

    private void removeNode(Node<E> node) {

        if (node.after != null) {
            node.after.prev = node.prev;
        } else {
            tail = node.prev;
        }

        if (node.prev != null){
            node.prev.after = node.after;
        } else {
            head = node.after;
        }
    }

    public String toString() {

        StringBuilder SB = new StringBuilder("[");
        Node<E> curr = head;

        if (curr != null) {
            SB.append(curr.data);
            curr = curr.after;
        }

        while (curr != null) {
            SB.append(", ").append(curr.data);
            curr = curr.after;
        }

        return SB.append("]").toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {

        int hash = getHash(o);
        Node<E> cur = set[hash];

        while (cur != null) {
            if (cur.data.equals(o))
                return true;
            cur = cur.next;
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {

        int hash = getHash(e);
        Node<E> cur = set[hash];

        while (cur != null) {
            if (cur.data.equals(e))
                return false;
            cur = cur.next;
        }

        Node<E> newNode = new Node<>(e);

        newNode.next = set[hash];
        set[hash] = newNode;
        addNode(newNode);
        size++;

        if (size > set.length * 0.75)
            resize();

        return true;
    }

    @Override
    public boolean remove(Object o) {

        int hash = getHash(o);
        Node<E> cur = set[hash];
        Node<E> prev = null;

        while (cur != null) {

            if (cur.data.equals(o)) {
                removeNode(cur);

                if (prev == null) {
                    set[hash] = cur.next;
                }
                else {
                    prev.next = cur.next;
                }

                size--;
                return true;
            }

            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c){
            if (!contains(o))
                return false;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        boolean added = false;
        for (E e : c) {
            added = add(e) || added;
        }

        return added;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean retained = false;
        MyLinkedHashSet<E> tmp = new MyLinkedHashSet<E>(set.length);
        Node<E> cur = head;

        while (cur != null) {
            if (c.contains(cur.data)) {
                tmp.add(cur.data);
                retained = true;
            }
            cur = cur.after;
        }

        set = tmp.set;
        head = tmp.head;
        tail = tmp.tail;
        size = tmp.size;

        return retained;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        boolean removed = false;
        for (Object o : c)
            removed = remove(o) || removed;

        return removed;
    }

    @Override
    public void clear() {

        for (int i = set.length - 1; i >= 0; i--) {
            set[i] = null;
        }

        head = null;
        tail = null;
        size = 0;
    }
}
