package com.company;

import java.util.*;

public class DoubleLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node() {
            E data = null;
            next = null;
            prev = null;
        }

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
                return this.data == otherNode.data;
            }
        }
    }

    /**private class ListIterator<E> implements java.util.ListIterator {
        private Node<E> pre;
        private Node<E> post;
        private int index;

        public ListIterator(Node<E> pre, Node<E> post, int size) {
            this.pre = (Node<E>) head;
            this.post = pre.next;
            this.index = 0;
        }

        public boolean hasNext() {
            return (pre.next != null);
        }

        public void remove() {
            try{
                Node<E> temp = next();
                this.pre = post.next;
                this.post = pre.next;
                index--;
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void set(Object o) {
            E dataItem = (E) o;
            if(dataItem != null && this.getClass() == dataItem.getClass()) {
                pre.next.data = dataItem;
            }
        }

        public Node<E> next() {
            if(pre.next == null) {
                throw new NoSuchElementException();
            } else if(hasNext()) {
                pre = pre.next;
                post = post.next;
                return pre.next;
            }
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return (pre != null);
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
            pre = pre.next;
            post = post.next;
            pre.prev = node;
            index++;
        }

        public void add(Object o) {
            E dataItem = (E) o;
            if(dataItem != null || this.getClass() != dataItem.getClass()) {
                head = new Node(o, head);
                index++;
            }
        }
    }*/

    private class ListIterator<E> implements java.util.ListIterator<E> {
        private Node<E> current;
        private Node<E> lastAccessed;
        private int index;

        private ListIterator() {
            current = (Node<E>) head;
            lastAccessed = null;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            lastAccessed = current;
            E data = current.data;
            current = current.next;
            index++;
            return data;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public E previous() {
            if(!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = current.prev;
            lastAccessed = current;
            index--;
            return current.data;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if (lastAccessed == null) {
                throw new IllegalStateException();
            }
            Node<E> pre = lastAccessed.prev;
            Node<E> post = lastAccessed.next;
            pre.next = post;
            post.prev = pre;
            size--;
            if (current == lastAccessed)
                current = post;
            else
                index--;
            lastAccessed = null;
        }

        @Override
        public void set(E e) {
            if(lastAccessed == null) {
                throw new IllegalStateException();
            }
            lastAccessed.data = e;
        }

        @Override
        public void add(E e) {
            Node<E> pre  = current.prev;
            Node<E> temp = new Node();
            Node<E> post = current;
            temp.data = e;
            pre.next = temp;
            temp.next = post;
            post.prev = temp;
            temp.prev = pre;
            size++;
            index++;
            lastAccessed = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (E data : this) {
            str.append(data + " ");
        }
            str.append("]");
        return str.toString();
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
        return new ListIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }


    public boolean add(Object o) {
        E dataItem = (E) o;
        if(dataItem == null) {
            return false;
        }
        try{
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
        E dataItem = (E) o;
        if(dataItem == null) {
            return false;
        }
        try{
            Node<E> node = head;
            while(node.next != null) {
                node = node.next;
                if(node.data == dataItem) {
                    node.prev = node.next.next;
                    node.next = node.prev.next;
                    return true;
                }
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
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
        Node<E> node = new Node(head.data, head);
        while(node.next != null) {
            head = node.next;
            node = node.next;
        }
        size = 0;
    }

    @Override
    public E get(int i) {
        int index = 0;
        Node<E> node = head;
        while(index <= size() && i <= size() && node.next != null) {
            node = node.next;
            if(index == i) {
                return (E) node.data;
            }
            index++;
        }
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
    public E remove(int i) {
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
        return new ListIterator();
    }

    @Override
    public java.util.ListIterator listIterator(int i) {
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

    public java.util.ListIterator getIterator() {
        return null;
    }
}
