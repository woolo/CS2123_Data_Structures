public interface QueueInterface<E> {

    /** Determines whether a queue is empty.
     * @return Returns true if the queue is empty;
     * otherwise returns false.
     */
    public boolean isEmpty();
    
    /**  Adds an item at the back of a queue.
     *  <li>Precondition: newItem is the item to be inserted. 
     *  <li>Postcondition: If the operation was successful, newItem
     *  is at the back of the queue. Some implementations
     *  may throw QueueException if newItem cannot be added
     *  to the queue.
     */
    public void enqueue(E newItem) throws QueueException;
    
    /**  Retrieves and removes the front of a queue.
     *  @return If the queue is not empty, the item
     *  that was added to the queue earliest is returned and
     *  the item is removed. If the queue is empty, the
     *  operation is impossible and QueueException is thrown.
     */

    public E dequeue() throws QueueException;

    /**  Removes all items of a queue.
     *  <li>Postcondition: The queue is empty.
     */
    public void dequeueAll();
    
    /**  Retrieves the item at the front of a queue.
     *  @return If the queue is not empty, the item
     *  that was added to the queue earliest is returned. 
     *  If the queue is empty, the operation is impossible
     *  and QueueException is thrown.
     */
    public E peek() throws QueueException;
    
} // end QueueInterface