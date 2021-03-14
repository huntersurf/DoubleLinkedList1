import java.util.List;

public class LinkedList<E> implements List<E> {
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
        }
        if (temp != null) {
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

    @Override
    private Node<E> getNode(int index) {
        Node<E>
    }
}
