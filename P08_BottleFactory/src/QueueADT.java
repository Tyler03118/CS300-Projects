///// DO NOT SUBMIT THIS FILE TO GRADESCOPE!
/**
 * This interface models the Abstract Data Type Queue. New elements are added to the back of the
 * queue. Elements are removed from the front of the queue.
 *
 * @param <T> Type of elements stored in the queue
 */
public interface QueueADT<T> {

  /**
   * Returns true if and only if the queue contains no elements
   * 
   * @return boolean value
   */
  public boolean isEmpty();

  /**
   * Returns true if and only if the queue has reached its capacity
   * 
   * @return boolean value
   */
  public boolean isFull();
  
  /**
   * 
   * Returns the number of elements in the queue
   * 
   * @return the size of the queue
   */
  public int size();

  /**
   * Adds one element to the back of the queue
   * 
   * @param element element of type T to add to queue
   */
  public void enqueue(T element);

  /**
   * Removes and returns the element at the front of the queue
   * 
   * @return Top of the stack (type T) - null if empty
   */
  public T dequeue();


  /**
   * Returns without removing the element at the front of the queue
   * 
   * @return Top of the stack (type T) - null if empty
   */
  public T peek();

  
  /**
   * Returns a deep copy of this Queue
   * 
   * @return a deep copy of the queue
   */
  public QueueADT<T> copy();

}
