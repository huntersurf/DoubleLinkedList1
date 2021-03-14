public class DoubleLinkedList {
    private static class Node<E> {
        private E data;
        private Node<E> next = null;
        private Node<E> tail = null;

        private Node(E dataItem) {
            this.data = dataItem;
        }

        private Node(E dataItem, Node<E> next) {
            this.data = dataItem;
            if(next != null) {
                this.next = next;
                this.tail = null;
            }

        }
    }
}
