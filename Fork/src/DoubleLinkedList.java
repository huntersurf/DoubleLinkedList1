import java.util.List;

public class DoubleLinkedList<E> implements List {
    Node<E> head = null;
    int size;
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

    private class Iterator implements Iterator {
        public boolean hasNext() {
            if(this)
            return false;
        }

        public void remove() {

        }
        public E next() {
            if(head.next != null) {
                return
            }
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return head.next == null;
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

    public Node<E> contains(Node<E>) {

    }

    public Iterator getIterator() {
        return Iterator;
    }
}
