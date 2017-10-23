public class QueueReferenceBased<E> implements QueueInterface<E> {

  private Node<E> lastNode;
  
  public QueueReferenceBased() {
      lastNode = null;   
  }  // end default constructor
  

 /** Determines whether a queue is empty.
  * @return Returns true if the queue is empty;
  * otherwise returns false.
  */
  public boolean isEmpty() {
    return lastNode == null;
  }  // end isEmpty

  /**  Removes all items of a queue.
   *  <li>Postcondition: The queue is empty.
   */
  public void dequeueAll() {
    lastNode = null;
  }  // end dequeueAll

   /**  Adds an item at the back of a queue.
    *  <li>Precondition: newItem is the item to be inserted. 
    *  <li>Postcondition: If the operation was successful, newItem
    *  is at the back of the queue. Some implementations
    *  may throw QueueException if newItem cannot be added
    *  to the queue.
    */
  public void enqueue(E newItem) {
      Node<E> newNode = new Node<E>(newItem);
      
      // insert the new node
      if (isEmpty()) {
	  // insertion into empty queue
	  newNode.setNext(newNode);
      }
      else {
	  // insertion into nonempty queue
	  newNode.setNext(lastNode.getNext());
	  lastNode.setNext(newNode);
      }  // end if
      
      lastNode = newNode;  // new node is at back
  }  // end enqueue
  
  
  
  /**  Retrieves and removes the front of a queue.
   *  @return If the queue is not empty, the item
   *  that was added to the queue earliest is returned and
   *  the item is removed. If the queue is empty, the
   *  operation is impossible and QueueException is thrown.
   */
  
  public E dequeue() throws QueueException {
      if (!isEmpty()) {
	  // queue is not empty; remove front
	  Node<E> firstNode = lastNode.getNext();
	  if (firstNode == lastNode) { // special case?
	      lastNode = null;           // yes, one node in queue
	  }
	  else {
	      lastNode.setNext(firstNode.getNext());
	  }  // end if
	  return firstNode.getItem();
      }
      else {
	  throw new QueueException("QueueException on dequeue:"
				   + "queue empty");
      }  // end if
  }  // end dequeue
  
  
  /**  Retrieves the item at the front of a queue.
   *  @return If the queue is not empty, the item
   *  that was added to the queue earliest is returned. 
   *  If the queue is empty, the operation is impossible
   *  and QueueException is thrown.
   */
  public E peek() throws QueueException {
      if (!isEmpty()) {  
	  // queue is not empty; retrieve front
	  Node<E> firstNode = lastNode.getNext();
	  return firstNode.getItem();
      }
      else {
	  throw new QueueException("QueueException on peek:"
				   + "queue empty");
      }  // end if
  }  // end peek
  
} // end QueueReferenceBased