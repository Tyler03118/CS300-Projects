///// DO NOT SUBMIT THIS FILE TO GRADESCOPE!
/**
 * LinkedNode class represents a generic class of a node in a singly linked list. This LinkedNode
 * class can also be used to build a linked stack or a linked queue.
 * 
 * @param<T> represents the type of the data carried by this node
 */
public class LinkedNode<T> {
  // Fields
  private T data; // item data field of any type T
  private LinkedNode<T> next; // reference to the next node in the list


  // Constructors

  /**
   * Creates a new LinkedNode object with a specific data and no next node in the list
   * 
   * @param data data of this LinkedNode
   */
  public LinkedNode(T data) {
    this.data = data;
  }



  /**
   * Creates an instance of LinkedNode with a given data and reference to a next node
   * 
   * @param data data stored within thos node
   * @param next reference to the next node in the list
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }


  /**
   * Accessor for data field
   * 
   * @return the data carried by this node
   */
  public T getData() {
    return data;
  }



  /**
   * Setter of the data field
   * 
   * @param data the data to set
   */
  public void setData(T data) {
    this.data = data;
  }



  /**
   * Accessor for the next field
   * 
   * @return the next node
   */
  public LinkedNode<T> getNext() {
    return next;
  }



  /**
   * Setter for next field
   * 
   * @param next the next to set
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
}
