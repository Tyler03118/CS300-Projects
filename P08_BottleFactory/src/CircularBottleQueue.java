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
 * This class models a circular-indexing array queue which stores elements of type Bottle.
 */
public class CircularBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
  // a private instance field of type Bottle[], is the array of bottles
  private Bottle[] bottles;
  // indicating the number of bottles in the queue
  private int size;
  // indicting the earliest added bottle
  private int front;
  // indicting the recently added bottle
  private int back;

  /**
   * Constructs a CircularBottleQueue object, initializing its data fields as follows: the bottles
   * oversize array to an empty array of Bottle objects whose length is the input capacity, its size
   * to zero, and both its front and back to -1.
   *
   * @param capacity defining the number of bottles the queue can hold
   * @throws IllegalArgumentException when capacity is not positive
   */
  public CircularBottleQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
    bottles = new Bottle[capacity];
    size = 0;
    front = -1;
    back = -1;
  }

  /**
   * Returns an iterator to traverse the queue.
   *
   * @return An Iterator instance to traverse a deep copy of this CircularBottleQueue.
   */
  @Override
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this.copy());
  }

  /**
   * Add a bottle to the end of the queue
   *
   * @param bottle Bottle to add to the queue
   * @throws IllegalStateException when queue is full
   * @throws NullPointerException  when bottle to add is null
   */
  @Override
  public void enqueue(Bottle bottle) throws IllegalStateException, NullPointerException {
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    if (bottle == null) {
      throw new NullPointerException("Bottle cannot be null");
    }
    if (isEmpty()) {
      front = 0;
    }
    back = (back + 1) % bottles.length;
    bottles[back] = bottle;
    size++;
  }

  /**
   * Removes and returns the first bottle in the queue.
   *
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException when queue is empty
   */
  @Override
  public Bottle dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    Bottle dequeued = bottles[front];
    // sett the front position
    bottles[front] = null;
    front = (front + 1) % bottles.length;
    size--;
    if (isEmpty()) {
      front = -1;
      back = -1;
    }
    return dequeued;
  }

  /**
   * Returns the first bottle in the queue without removing it
   *
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException when queue is empty
   */
  @Override
  public Bottle peek() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return bottles[front];
  }

  /**
   * Checks and returns true if the queue is empty
   *
   * @return boolean value
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Checks and returns true if the queue is full
   *
   * @return boolean value
   */
  @Override
  public boolean isFull() {
    return size == bottles.length;
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
   * Returns a string representation of the queue from the front to its back with the string
   * representation of each Bottle in a separate line
   *
   * @return String in expected format, empty string when queue is empty
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < size; i++) {
      sb.append(bottles[(front + i) % bottles.length].toString());
      if (i < size - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  /**
   * Returns a deep copy of this Queue
   *
   * @return a deep copy of the queue
   */
  @Override
  public QueueADT<Bottle> copy() {
    CircularBottleQueue copiedQueue = new CircularBottleQueue(bottles.length);
    for (int i = 0; i < size; i++) {
      copiedQueue.enqueue(bottles[(front + i) % bottles.length]);
    }
    return copiedQueue;
  }
}
