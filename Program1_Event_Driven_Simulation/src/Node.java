public class Node<E> {
  private E item;
  private Node<E> next;

  public Node(E newItem) {
    item = newItem;
    next = null;
  } // end constructor

  public Node(E newItem, Node<E> nextNode) {
    item = newItem;
    next = nextNode;
  } // end constructor

  public void setItem(E newItem) {
    item = newItem;
  } // end setItem

  public E getItem() {
    return item;
  } // end getItem

  public void setNext(Node<E> nextNode) {
    next = nextNode;
  } // end setNext

  public Node<E> getNext() {
    return next;
  } // end getNext
} // end class Node

