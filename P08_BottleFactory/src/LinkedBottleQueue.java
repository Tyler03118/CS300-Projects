//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 Bottle Factory
// Course:   CS 300 Spring 2023
//
// Author:   Ziji Li
// Email:    zli2296@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a linked queue storing objects of type Bottle. Bottle are added and removed
 * with respect to the First In First Out (FIFO) scheduling policy.
 */
public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
  // indicating the beginning bottles in the linked list queue
  private LinkedNode<Bottle> front;
  // indicating the end bottles in the linked list queue
  private LinkedNode<Bottle> back;
  // indicating the number of bottles in the queue
  private int size;
  // defining the max number of bottles the queue can hold
  private int capacity;

  /**
   * Initializes the fields of this queue including its capacity. A newly created queue must be
   * empty, meaning that both its front and back are null and its size is zero.
   *
   * @param capacity Positive integer defining the max number of bottles the queue can hold
   * @throws IllegalArgumentException when the capacity is not positive (meaning less or equal to
   *                                  zero)
   */
  public LinkedBottleQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity must be positive");
    }
    this.capacity = capacity;
    this.front = null;
    this.back = null;
    this.size = 0;
  }

  /**
   * Returns a string representation of the queue from the front to its back with the string
   * representation of each Bottle in a separate line.
   *
   * @return String in expected format, empty string when queue is empty
   */
  @Override
  public String toString() {
    if (isEmpty()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (Bottle b : this) {
      sb.append(b.toString());
      sb.append("\n");
    }
    // delete the last "\n" element
    if (!isEmpty()) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  /**
   * Returns the first bottle in the queue without removing it
   *
   * @return Top/First bottle in the queue
   * @throw NoSuchElementException When queue is empty
   */
  @Override
  public Bottle peek() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return front.getData();
  }

  /**
   * Checks and returns true if the queue is empty
   *
   * @returns boolean value
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Checks and returns true if the queue is full
   *
   * @returns boolean value
   */
  @Override
  public boolean isFull() {
    return size == capacity;
  }

  /**
   * Removes and returns the first bottle in the queue
   *
   * @throws NoSuchElementException when queue is empty
   * @returns Top/First bottle in the queue
   */
  @Override
  public Bottle dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    Bottle bottle = front.getData();
    front = front.getNext();
    size--;
    if (isEmpty()) {
      back = null;
    }
    return bottle;
  }

  /**
   * Add a bottle to the end of the queue
   *
   * @param bottle bottle to add to the queue
   * @throws IllegalStateException when queue is full
   * @throws NullPointerException  when bottle to add is null
   */
  @Override
  public void enqueue(Bottle bottle) throws IllegalArgumentException, NullPointerException {
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    if (bottle == null) {
      throw new NullPointerException("Bottle to add is null.");
    }
    LinkedNode<Bottle> newNode = new LinkedNode<>(bottle);
    if (isEmpty()) {
      front = newNode;
    } else {
      back.setNext(newNode);
    }
    back = newNode;
    size++;
  }

  /**
   * Returns the number of bottles in the queue
   *
   * @return size of the queue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns an iterator for traversing the queue's items
   *
   * @return iterator to traverse the LinkedListQueue
   */
  @Override
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this.copy());

  }

  /**
   * Returns a deep copy of this queue
   *
   * @return deep copy of this queue
   */
  @Override
  public QueueADT<Bottle> copy() {
    LinkedBottleQueue copiedQueue = new LinkedBottleQueue(capacity);
    LinkedNode<Bottle> currentNode = front;
    while (currentNode != null) {
      copiedQueue.enqueue(currentNode.getData());
      currentNode = currentNode.getNext();
    }
    return copiedQueue;
  }
}
