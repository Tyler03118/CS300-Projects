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
import java.util.NoSuchElementException;

/**
 * This utility class implements tester methods to check the correctness of the implementation of
 * classes defined in p08 Bottle Factory program.
 */
public class BottleFactoryTester {

  /**
   * Ensures the correctness of the constructor and methods defined in the Bottle class
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean bottleTester() {
    Bottle.resetBottleCounter();
    Bottle b1 = new Bottle("Blue");
    String expectedSerial = "SN1Blue:Empty:Open";

    if (!b1.toString().equals(expectedSerial)) {
      return false;
    }

    // edge cases test
    try {
      Bottle b2 = new Bottle("");
      return false;
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("color cannot be empty")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    try {
      Bottle b3 = new Bottle(null);
      return false;
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("color cannot be empty")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // test toString method
    Bottle.resetBottleCounter();
    Bottle b4 = new Bottle("Red");
    b4.fillBottle();
    b4.sealBottle();
    String expectedString = "SN1Red:Filled:Capped";
    if (!b4.toString().equals(expectedString)) {
      return false;
    }

    // test equals method
    Bottle.resetBottleCounter();
    Bottle b5a = new Bottle("Green");
    Bottle b5b = new Bottle("Green");
    if (b5a.equals(b5b)) {
      return false;
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the LinkedBottleQueue class
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean linkedBottleQueueTester() {
    Bottle.resetBottleCounter();
    // test constructor - verify fields and exception behavior (when capacity is invalid)
    try {
      // valid parameter
      LinkedBottleQueue queue = new LinkedBottleQueue(5);
    } catch (Exception e) {
      return false;
    }

    try {
      // invalid parameter
      LinkedBottleQueue queue = new LinkedBottleQueue(-2);
      return false;
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("capacity must be positive")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }


    LinkedBottleQueue queue = new LinkedBottleQueue(3);

    /* test enqueue, dequeue and peek methods using different scenarios
     * 1) all methods on an empty queue
     * 2) all methods on a full queue
     * 3) all methods on a partially filled queue
     * 4) Verify queue contents (using peek and size) after a sequence of
     *    enqueue-dequeue and dequeue-enqueue
     * 5) Enqueue until queue is full and dequeue until queue is empty
     */

    // test isEmpty and isFull method
    if (!queue.isEmpty() || queue.isFull()) {
      return false;
    }

    // 1) Test enqueue, dequeue, and peek methods on an empty queue
    try {
      queue.peek();
      return false;
    } catch (NoSuchElementException e) {
      if (!e.getMessage().equals("Queue is empty")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    try {
      queue.dequeue();
      return false;
    } catch (NoSuchElementException e) {
      if (!e.getMessage().equals("Queue is empty")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // 2) Test enqueue, dequeue, and peek methods on a full queue
    Bottle.resetBottleCounter();
    Bottle red = new Bottle("Red");
    Bottle green = new Bottle("Green");
    Bottle blue = new Bottle("Blue");

    try {
      queue.enqueue(red);
    } catch (Exception e) {
      return false;
    }
    try {
      queue.enqueue(green);
    } catch (Exception e) {
      return false;
    }
    try {
      queue.enqueue(blue);
    } catch (Exception e) {
      return false;
    }


    if (!queue.isFull()) {
      return false;
    }

    try {
      queue.enqueue(new Bottle("Blue"));
      return false;
    } catch (IllegalStateException e) {
      if (!e.getMessage().equals("Queue is full")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // 3) Test enqueue, dequeue, and peek methods on a partially filled queue
    try {
      queue.dequeue();
    } catch (Exception e) {
      return false;
    }


    if (!queue.peek().equals(green)) {
      return false;
    }

    // 4) Verify queue contents (using peek and size) after a sequence of enqueue-dequeue and dequeue-enqueue

    try {
      queue.enqueue(new Bottle("Blue"));
    } catch (Exception e) {
      return false;
    }

    try {
      queue.dequeue(); // dequeue bottle with serial number 2
    } catch (Exception e) {
      return false;
    }

    if (!queue.peek().equals(blue) || queue.size() != 2) {
      return false;
    }

    // 5) Enqueue until queue is full and dequeue until queue is empty
    queue.enqueue(new Bottle("Blue"));
    while (!queue.isEmpty()) {
      queue.dequeue();
    }

    if (!queue.isEmpty() || queue.size() != 0) {
      return false;
    }
    Bottle.resetBottleCounter();
    try {
      queue.enqueue(new Bottle("White"));
    } catch (Exception e) {
      return false;
    }
    try {
      queue.enqueue(new Bottle("Black"));
    } catch (Exception e) {
      return false;
    }

    String expected = "SN1White:Empty:Open\n" + "SN2Black:Empty:Open";

    // test copy method
    QueueADT<Bottle> copiedQueue = queue.copy();
    if (copiedQueue.size() != queue.size() || !queue.toString().equals(copiedQueue.toString())) {
      return false;
    }

    // test toString method
    String queueStr = queue.toString();
    if (queueStr == null || queueStr.trim().isEmpty() || !queueStr.equals(expected)) {
      return false;
    }

    // Extra tests
    Bottle.resetBottleCounter();
    LinkedBottleQueue testQueue = new LinkedBottleQueue(3);
    Bottle test1 = new Bottle("test1");
    Bottle test2 = new Bottle("test2");
    Bottle test3 = new Bottle("test3");

    testQueue.enqueue(test1);
    testQueue.enqueue(test2);
    testQueue.enqueue(test3);

    if (!testQueue.dequeue().equals(test1) || !testQueue.dequeue()
        .equals(test2) || !testQueue.dequeue().equals(test3)) {
      return false;
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the CircularBottleQueue
   * class
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean circularBottleQueueTester() {
    Bottle.resetBottleCounter();
    // test constructor - verify fields and exception behavior
    try {
      // invalid parameters
      CircularBottleQueue invalidQueue = new CircularBottleQueue(0);
      return false;
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Capacity must be positive"))
        ;
    } catch (Exception e) {
      return false;
    }

    CircularBottleQueue queue = new CircularBottleQueue(3);
    if (queue.size() != 0 || !queue.isEmpty()) {
      return false;
    }

    Bottle blue = new Bottle("Blue");
    Bottle red = new Bottle("Red");
    Bottle green = new Bottle("Green");
    // 1) All 3 methods on an empty queue
    try {
      queue.peek();
      return false;
    } catch (NoSuchElementException e) {
      if (!e.getMessage().equals("Queue is empty")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    try {
      queue.dequeue();
      return false;
    } catch (NoSuchElementException e) {
      if (!e.getMessage().equals("Queue is empty")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // 2) All 3 methods on a full queue
    queue.enqueue(blue);
    queue.enqueue(red);
    queue.enqueue(green);

    if (!queue.isFull() || queue.size() != 3) {
      return false;
    }

    try {
      queue.enqueue(new Bottle("Yellow"));
      return false;
    } catch (IllegalStateException e) {
      if (!e.getMessage().equals("Queue is full")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    if (queue.peek() != blue) {
      return false;
    }

    if (queue.dequeue() != blue) {
      return false;
    }

    // 3) All 3 methods on a partially filled queue
    if (queue.peek() != red) {
      return false;
    }

    if (queue.dequeue() != red) {
      return false;
    }

    try {
      queue.enqueue(blue);
    } catch (Exception e) {
      return false;
    }


    // 4) Verify queue contents and size after a sequence of enqueue-dequeue and dequeue-enqueue
    if (queue.size() != 2) {
      return false;
    }

    if (queue.dequeue() != green || queue.dequeue() != blue) {
      return false;
    }

    // 5) Enqueue until the queue is full and dequeue until the queue is empty
    try {
      queue.enqueue(blue);
      queue.enqueue(red);
      queue.enqueue(green);
    } catch (Exception e) {
      return false;
    }

    if (!queue.isFull()) {
      return false;
    }

    try {
      queue.dequeue();
      queue.dequeue();
      queue.dequeue();
    } catch (Exception e) {
      return false;
    }


    if (!queue.isEmpty()) {
      return false;
    }

    queue.enqueue(blue);
    queue.enqueue(green);
    // test copy method
    QueueADT<Bottle> copiedQueue = queue.copy();
    if (copiedQueue.size() != queue.size() || !queue.toString().equals(copiedQueue.toString())) {
      return false;
    }
    // check if dequeue all the elements
    while (!queue.isEmpty()) {
      Bottle original = queue.dequeue();
      Bottle copied = copiedQueue.dequeue();
      if (!original.equals(copied)) {
        return false;
      }
    }
    // test toString method
    queue.enqueue(blue);
    queue.enqueue(red);
    queue.enqueue(green);
    String expected = blue.toString() + "\n" + red.toString() + "\n" + green.toString();
    if (!queue.toString().equals(expected)) {
      return false;
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the BottleQueueIterator
   * class
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean bottleQueueIteratorTester() {

    // invalid parameters
    try {
      BottleQueueIterator iterator = new BottleQueueIterator(null);
      return false;
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Queue cannot be null")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // valid parameters
    LinkedBottleQueue bottleQueue = new LinkedBottleQueue(3);
    try {
      BottleQueueIterator iterator = new BottleQueueIterator(bottleQueue);
    } catch (IllegalArgumentException | NullPointerException e) {
      return false;
    }

    /*
     *  test 01: Create a LinkedBottleQueue with at least n bottles and
     *  use the bottleQueueIterator to traverse the queue. Verify if all
     *  the bottles are returned correctly
     */
    Bottle.resetBottleCounter();
    LinkedBottleQueue bottles1 = new LinkedBottleQueue(3);
    Bottle b1 = new Bottle("beige");
    Bottle b2 = new Bottle("cyan");
    Bottle b3 = new Bottle("white");
    b2.fillBottle();
    bottles1.enqueue(b1);
    bottles1.enqueue(b2);
    bottles1.enqueue(b3);

    // Save the original queue's size and contents
    int originalQueueSize1 = bottles1.size();
    String originalQueueContents1 = bottles1.toString();

    BottleQueueIterator iterator1 = new BottleQueueIterator(bottles1);
    StringBuilder sb1 = new StringBuilder();

    // iterate and add the element to stringbuilder
    while (iterator1.hasNext()) {
      Bottle bottle = iterator1.next();
      sb1.append(bottle.toString());
    }
    String expected = b1.toString() + b2.toString() + b3.toString();

    if (!expected.equals(sb1.toString())) {
      return false;
    }

    if (bottles1.size() != originalQueueSize1 || !bottles1.toString()
        .equals(originalQueueContents1)) {
      return false;
    }

    /*
     *  test 02: Create a CircularBottleQueue with at least n bottles and
     *  use the bottleQueueIterator to traverse the queue. Verify if all
     *  the bottles are returned correctly
     */
    Bottle.resetBottleCounter();
    CircularBottleQueue bottles2 = new CircularBottleQueue(3);
    bottles2.enqueue(b1);
    bottles2.enqueue(b2);
    bottles2.enqueue(b3);

    // Save the original queue's size and contents
    int originalQueueSize2 = bottles1.size();
    String originalQueueContents2 = bottles1.toString();

    BottleQueueIterator iterator2 = new BottleQueueIterator(bottles2);
    StringBuilder sb2 = new StringBuilder();

    // iterate and add the element to stringbuilder
    while (iterator2.hasNext()) {
      Bottle bottle = iterator2.next();
      sb2.append(bottle.toString());
    }
    String expected2 = b1.toString() + b2.toString() + b3.toString();

    if (!expected2.equals(sb2.toString())) {
      return false;
    }

    if (bottles2.size() != originalQueueSize2 || !bottles2.toString()
        .equals(originalQueueContents2)) {
      return false;
    }

    // Test if using deep copy
    // Save the original state of the bottles in the queue
    String originalB1State = b1.toString();
    String originalB2State = b2.toString();
    String originalB3State = b3.toString();

    if (bottles1.size() != originalQueueSize1 || !bottles1.toString()
        .equals(originalQueueContents1)) {
      return false;
    }

    // Check if the state of the bottles in the original queue remains unchanged
    if (!b1.toString().equals(originalB1State) || !b2.toString()
        .equals(originalB2State) || !b3.toString().equals(originalB3State)) {
      return false;
    }

    return true;
  }

  /**
   * Runs all the tester methods defined in this class.
   *
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    System.out.println("bottleTester: " + (bottleTester() ? "Pass" : "Failed!"));
    System.out.println(
        "bottleQueueIterator: " + (bottleQueueIteratorTester() ? "Pass" : "Failed!"));
    System.out.println(
        "linkedBottleQueueTester: " + (linkedBottleQueueTester() ? "Pass" : "Failed!"));
    System.out.println(
        "circularBottleQueueTester: " + (circularBottleQueueTester() ? "Pass" : "Failed!"));

    return bottleTester() && bottleQueueIteratorTester() && linkedBottleQueueTester() && circularBottleQueueTester();
  }

  /**
   * Main method to run this tester class.
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }

}
