package com.company;

import java.util.*;

public class DoubleLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private class Node<E> {
        private Node<E> next;
        private Node<E> prev;
        private E data;

        public Node() {
            next = null;
            prev = null;
            data = null;
        }

        public Node(E dataItem) {
            next = null;
            prev = null;
            data = dataItem;
        }

        public Node(E dataItem, Node<E> next) {
            this.next = next;
            this.prev = null;
            this.data = dataItem;
        }
    }

    private class ListIter implements ListIterator<E> {
        private Node<E> nextItem;
        private Node<E> lastReturned;
        private int index;

        public ListIter(int i) {
            if(i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            lastReturned = null;
            if(i == size) {
                index = size;
                nextItem = null;
            } else {
                nextItem = head;
                for(index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }
            }
        }


        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            return ((nextItem == null && size != 0) || nextItem.prev != null);
        }

        @Override
        public E previous() {
            if(!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if(nextItem == null) {
                nextItem = tail;
            } else {
                nextItem = nextItem.prev;
            }
            lastReturned = nextItem;
            index--;
            return lastReturned.data;
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if(lastReturned == null) {
                throw new NoSuchElementException();
            } else if(index == 0) {
                removeFirst();
            } else if(index > 0 && index <= size) {
                removeAfter(getNode(index - 2));
            }
        }

        @Override
        public void set(E e) {
            if(lastReturned == null) {
                throw new NoSuchElementException();
            } else {
                lastReturned.data = e;
            }
        }

        @Override
        public void add(E e) {
            if(head == null) {
                head = new Node<E>(e);
                tail = head;
            } else if(nextItem == head) {
                Node<E> newNode = new Node<E>(e);
                newNode.next = nextItem;
                nextItem.prev = newNode;
                head = newNode;
            } else if(nextItem == null) {
                Node<E> newNode = new Node<E>(e);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else {
                Node<E> newNode = new Node<E>(e);
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }
            size++;
            index++;
            lastReturned = null;
        }
    }

    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        E dataItem = (E) o;
        Node<E> nodeRef = new Node<E>(dataItem, head);
        int index = indexOf(dataItem);
        Node<E> node = getNode(index);
        while(nodeRef != null) {
            if(nodeRef.data == dataItem) {
                return true;
            }
            nodeRef = nodeRef.next;
        }
        return false;
    }

    private void addFirst(E dataItem) {
        Node<E> node = new Node<E>(dataItem);
        head = new Node<E>(dataItem, head);
        size++;
    }

    private void addAfter(Node<E> node, E dataItem) {
        node.next = new Node<E>(dataItem, node.next);
        node.next.prev = node;
        size++;
    }

    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if(temp != null) {
            node.next = temp.next;
            node.next.prev = node;
            size--;
            return temp.data;
        } else {
            throw new NoSuchElementException();
        }
    }

    private E removeFirst() {
        Node<E> temp = head;
        if(head != null) {
            head = head.next;
            head.next.prev = head;
        }
        if(temp != null) {
            size--;
            return temp.data;
        } else {
            throw new NoSuchElementException();
        }
    }

    public String toString() {
        Node<E> nodeRef = head;
        String result = "[";
        if(size == 0) {
            result = result + "]";
        };
        while(nodeRef != null) {
            result = result + nodeRef.data;
            if(nodeRef.next != null) {
                result = result + ", ";
            } else {
                result = result + "]";
            }
            nodeRef = nodeRef.next;
        }
        return result;
    }

    private Node<E> getNode(int index) {
        Node<E> node = head;
        for(int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIter(0);
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
    public boolean add(E dataItem) {
        if(size == 0) {
            addFirst(dataItem);
            return true;
        } else if(size > 0) {
            addAfter(getNode(size - 1), dataItem);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        E data = (E) o;
        Node<E> node = head;
        while(node != null) {
            if(node.data == o) {
                node = node.next;
                node.prev = node.prev.prev;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    @Override
    public E set(int index, E element) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = element;
        return result;
    }

    @Override
    public void add(int index, E element) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if(index == 0) {
            addFirst(element);
        } else {
            Node<E> node = getNode(index);
            addAfter(node, element);

        }
    }

    @Override
    public E remove(int index) {
        Node<E> temp = getNode(index - 1);
        Node<E> node = temp.next;
        removeAfter(temp);
        return node.next.data;
    }

    @Override
    public int indexOf(Object o) {
        E obj = (E) o;
        Node<E> node = head;
        int index = 0;
        while(node != null) {
            if(node.data == obj) {
                return index;
            }
            index++;
            node = node.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIter(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIter(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

}
