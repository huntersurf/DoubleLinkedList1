import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedList<E implements List<E> {
    Node<E> head = null;
    int size;

    private static class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E dataItem) {
            data = dataItem;
            next = null;
        }

        private Node(E data, Node<E> nodeRef) {
            this.data = data;
            this.next = nodeRef;
        }
    }

    private class ListIterator<E> {
        private Node position;
        private Node previous;

        public ListIterator(){
            position = head; // Instance variable of outer class
            previous = null;
        }

        public void restart() {
            position = head;
            previous = null;
        }

        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            E curData = (E) position.data;
            previous = position;
            position = position.next;
            return curData;
        }

        public boolean hasNext() {
            return (position != null);
        }

        public E peek() {
            if(!hasNext()) {
                throw new IllegalStateException();
            }
            return (E) position.data;
        }

        public void addHere(E data) {
            if(position == null && previous != null) {
                previous.next = new Node(data, null);
            } else if (position == null || previous == null) {
                E temp = (E) data;
                LinkedList.this.addFirst(temp);
            } else {
                // previous and position are consecutive nodes
                Node temp = new Node(data, position);
                previous.next = temp;
                previous = temp;
            }
        }

        public void changeHere(E newData) {
            if(position == null) {
                throw new IllegalStateException();
            } else {
                position.data = newData;
            }
        }

        /**
         * Deletes the node at location position and moves position to
         *  the "next" node. Throws an IllegalStateException if list is Empty
         */
        public void delete() {
            if(position == null) {
                throw new IllegalStateException();
            } else if (previous == null) {
                //remove node at head
                head = head.next;
                position = head;
            } else { // previous and position are consecutive nodes
                previous.next = position.next;
                position = position.next;
            }
        }
    }

    public Iterator<E> iterator() {
        return (Iterator<E>) new ListIterator();
    }

    public LinkedList() {
        head = null;
    }



    /**
     * addFirst() adds a node to the front of the list, or the
     * head.
     * @param item is the data stored in the new first node.
     */
    public void addFirst(E item) {
        head = new Node<E>(item, head);
        size++;
    }


    /**
     * Adds node after node input to param.
     * @param node new node inserted after node position
     * @param item data of new node
     */
    private void addAfter(Node<E> node, E item) {
        node.next = new Node<E>(item, node.next);
        size++;
    }


    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if(temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    private E removeFirst() {
        Node<E> temp = head;
        if (head != null) {
            head = head.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /**
     * Returns the size of said Linked-List
     * @return returns the number of Nodes in Linked-list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Overrides isEmpty from the List interface
     * @return boolean value on if list is empty or not
     */
    @Override
    public boolean isEmpty() {
        if (this.size <= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    /**
     * toString() method consolidates the data of each node by
     * iterating through the Linked-List and creates a String
     * of data in Nodes, returns said String
     * @return String value of data in Linked-List
     */
    @Override
    public String toString() {
        Node<E> nodeRef = head;
        StringBuilder result = new StringBuilder();
        while (nodeRef != null) {
            result.append(nodeRef.data);
            if (nodeRef.next != null) {
                result.append(" ==> ");
            }
            nodeRef = nodeRef.next;
        }
        return result.toString();
    }

    /**
     * Find the node at specified position
     * @param index The postion of the node sought
     * @returns The node at index or null if node does not exist.
     */
    private Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * Get the data value at index, could be helper method for
     * indicating within list range
     * @param index Position of element to return
     * @return data from index element.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    @Override
    public E get(int index) {
        if(index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    /**
     * Set the data value at index
     * @param index Position of the item to change
     * @param newValue The new value to set at desired index
     * @returns Data value previously at index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public E set(int index, E newValue) {
         if(index < 0 || index >= size) {
             throw new IndexOutOfBoundsException(Integer.toString(index));
         }
         Node<E> node = getNode(index);
         E result = node.data;
         node.data = newValue;
         return result;
    }

    /**
     * Insert new data node at desired position
     * @param index Position where the node will be inserted
     * @param item Data the node will have
     * @throws IndexOutOfBoundsException if index is not in range
     */
    @Override
    public void add(int index, E item) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if(index == 0) {
            addFirst(item);
        } else {
            Node<E> node = getNode(index-1);
            addAfter(node, item);
        }
    }

    /**
     *  Adds a new node to the end of the list
     * @param item Data for new node
     * @return If node was successful in creation
     */
    public boolean add(E item) {
        add(this.size, item);
        return true;
    }

    @Override
    public boolean contains() {
        return contains();
    }

    @Override
    public boolean contains(Object o) {
        Node<E> node = (Node<E>) o;
        Node<E> position = head;
        if(position == null) {
            return false;
        }
        while(position.next != null) {

        }
    }

    @Override
    public LinkedList<E> clone() {
        try {
            //noinspection unchecked
            LinkedList<E> copy = (LinkedList<E>)super.clone();
            if(head == null) {
                copy.head = null;
            } else {
                copy.head = copyOf(head);
                return copy;
            }
        } catch(CloneNotSupportedException e) {
            return null;
        }
        return null;
    }

    public Node<E> copyOf(Node<E> otherHead) {
        Node<E> position = head;
        Node<E> newHead;
        Node<E> end;
        // Create first node:
        newHead = new Node<E> ((E) (position.data).clone(), null);
        end = newHead;
        position = position.next;
        while(position != null) {
            end.next = new Node<E> ((E) (position.data).clone(), null);
        }
    }
}
