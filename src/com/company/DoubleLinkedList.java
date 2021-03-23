package com.company;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoubleLinkedList<E> implements List {

    private static class Node<E> {
        private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem) {
            this.data = dataItem;
        }

        private Node(E dataItem, Node<E> next) {
            this.data = dataItem;
            if(next != null) {
                this.next = next;
                this.prev = null;
            }
        }

        private Node(E dataItem, Node<E> next, Node<E> prev) {
            this.data = dataItem;
            this.next = next;
            this.prev = prev;
        }



        @Override
        public boolean equals(Object other) {
            if(other == null || this.getClass() != other.getClass()) {
                return false;
            } else {
                Node<E> otherNode = (Node<E>) other;
                return this.data == otherNode.data && this.next == otherNode.next
                        && this.prev == otherNode.prev;
            }
        }
    }

    private class ListIterator<E> implements java.util.ListIterator {
        private Node<E> prev;
        private Node<E> next;
        private int size;

        public ListIterator(Node<E> prev, Node<E> next, int size) {
            this.prev = (Node<E>) head;
            this.next = prev.next;
            size = 0;
        }

        public boolean hasNext() {
            return (prev.next != null);
        }

        public void remove() {
            try{
                Node<E> temp = next();
                this.prev = next.next;
                this.next = prev.next;
                size--;
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void set(Object o) {
            E dataItem = (E) o;
            prev.next.data = dataItem;
        }

        public Node<E> next() {
            if(prev.next == null) {
                throw new NoSuchElementException();
            } else {
                prev = prev.next;
                next = next.next;
                return prev.next;
            }
        }

        @Override
        public boolean hasPrevious() {
            return (prev != null);
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        public void add(Node<E> node) {
            prev = prev.next;
            next = next.next;
            prev.prev = node;
            size++;
        }

        public void add(Object o) {

        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    @Override
    public String toString() {
        Node<E> node = head;
        String str = "";
        while(node != null) {
            str += node.data + ", ";
            node = node.next;
        }
        return str;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        E dataItem = (E) o;
        boolean contains = false;
        Node<E> node = head;
        while(node.next != null) {
            if(node.next.data.equals(dataItem)) {
                contains = true;
            }
            node = node.next;
        }
        return contains;
    }

    @Override
    public java.util.Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }


    public boolean add(Object o) {
        try{
            E dataItem = (E) o;
            head = new Node<E>(dataItem, head);
            size++;
            return true;
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int i) {
        return null;
    }

    @Override
    public Object set(int i, Object o) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleLinkedList<?> that = (DoubleLinkedList<?>) o;
        return size == that.size &&
                Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }

    @Override
    public void add(int i, Object o) {

    }

    @Override
    public Object remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int i) {
        return null;
    }

    @Override
    public List subList(int i, int i1) {
        return null;
    }

    @Override
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
    }

    @Override
    public boolean containsAll(Collection collection) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return new Object[0];
    }

    public boolean remove(Node<E> node) {
        int index = 0;
        Node<E> position = head;
        while(position.next != null && index < size) {
            if(position.next.equals(node)) {
                Node<E> temp = position;
                node = position.next;
                return true;
            }
            index++;
            position = position.next;
        }
        return false;
    }

    public Node<E> contains(Node<E> node) {
        return null;
    }

    public ListIterator getIterator() {
        return null;
    }
}
