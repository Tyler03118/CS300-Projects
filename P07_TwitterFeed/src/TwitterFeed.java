//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Twitter Feed
// Course:   CS 300 Spring 2023
//
// Author:   Ziji Li
// Email:    zli2296@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources:  learned how to use repeat() method and use of iterators:
// https://www.studytonight.com/java-examples/how-to-repeat-string-n-times-in-java
// https://www.geeksforgeeks.org/iterators-in-java/
//////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *This class models a reverse-chronological Twitter feed using a singly-linked list. By default,
 * new tweets are added at the head of the list. Note that while we CALL this "reverse chronological"
 * order, this is NOT enforced. You can add Tweets anywhere in the list relative to each other,
 * regardless of their respective timestamps.
 */
public class TwitterFeed implements ListADT<Tweet>, Iterable<Tweet> {
  // The node containing the most recent tweet
  private TweetNode head;
  // The node containing the oldest tweet
  private TweetNode tail;
  // The number of tweets in this linked list
  private int size;
  // The iteration mode for the timeline display
  private TimelineMode mode;
  // The ratio of likes to retweets that we want to see; set to .5 by default
  private static double ratio = 0.5;

  /**
   * Default constructor; creates an empty TwitterFeed by setting all data fields to
   * their default values, and the timeline mode to CHRONOLOGICAL.
   */
  public TwitterFeed() {
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.mode = TimelineMode.CHRONOLOGICAL;
  }

  /**
   * Accessor for the size of the feed
   *
   * @return the number of TweetNodes in this TwitterFeed
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Determines whether this feed is empty
   *
   * @return true if there are NO TweetNodes in this TwitterFeed, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Determines whether a given Tweet is present in the TwitterFeed.
   *
   * @param findObject the Tweet to search for
   * @return true if the Tweet is present, false otherwise
   */
  @Override
  public boolean contains(Tweet findObject) {
    TweetNode currentNode = head;
    while (currentNode != null) {
      if (currentNode.getTweet().equals(findObject)) {
        return true;
      }
      currentNode = currentNode.getNext();
    }
    return false;
  }

  /**
   * Accessor method for the index of a given Tweet in the TwitterFeed.
   *
   * @param findObject the Tweet to search for
   * @return the index of the Tweet in the TwitterFeed if present, -1 if not
   */
  @Override
  public int indexOf(Tweet findObject) {
    TweetNode currentNode = head;
    int index = 0;
    while (currentNode != null) {
      if (currentNode.getTweet().equals(findObject)) {
        return index;
      }
      currentNode = currentNode.getNext();
      index++;
    }
    return -1;
  }

  /**
   * Accessor method for the Tweet at a given index
   *
   * @param index the index of the Tweet in question
   * @return the Tweet object at that index (NOT its TweetNode!)
   * @throws IndexOutOfBoundsException - if the index is negative or greater than the
   * largest index of the TwitterFeed
   */
  @Override
  public Tweet get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    TweetNode currentNode = head;
    for (int i = 0; i < index; i++) {
      currentNode = currentNode.getNext();
    }
    return currentNode.getTweet();
  }

  /**
   * Accessor method for the first Tweet in the TwitterFeed
   *
   * @return the Tweet object at the head of the linked list
   * @throws NoSuchElementException - if the TwitterFeed is empty
   */
  public Tweet getHead() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("TwitterFeed is empty");
    }
    return head.getTweet();
  }

  /**
   * Accessor method for the last Tweet in the TwitterFeed
   *
   * @return the Tweet object at the tail of the linked list
   * @throws NoSuchElementException - if the TwitterFeed is empty
   */
  public Tweet getTail() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("TwitterFeed is empty");
    }
    return tail.getTweet();
  }

  /**
   * Adds the given Tweet to the tail of the linked list
   *
   * @param newObject the Tweet to add
   */
  @Override
  public void addLast(Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject);
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      tail.setNext(newNode);
      tail = newNode;
    }
    size++;
  }

  /**
   * Adds the given Tweet to the head of the linked list
   *
   * @param newObject the Tweet to add
   */
  @Override
  public void addFirst(Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject);
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.setNext(head);
      head = newNode;
    }
    size++;
  }

  /**
   * Adds the given Tweet to a specified position in the linked list
   *
   * @param index the position at which to add the new Tweet
   * @param newObject the Tweet to add
   * @throws IndexOutOfBoundsException if the index is negative or greater than the size of the linked list
   */
  @Override
  public void add(int index, Tweet newObject) throws IndexOutOfBoundsException {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    if (index == 0) {
      addFirst(newObject);
    } else if (index == size) {
      addLast(newObject);
    } else {
      TweetNode newNode = new TweetNode(newObject);
      TweetNode currentNode = head;
      // move to the index position
      for (int i = 0; i < index - 1; i++) {
        currentNode = currentNode.getNext();
      }
      newNode.setNext(currentNode.getNext());
      currentNode.setNext(newNode);
      size++;
    }
  }

  /**
   * Removes and returns the Tweet at the given index
   *
   * @param index the position of the Tweet to remove
   * @return the Tweet object that was removed from the list
   * @throw IndexOutOfBoundsException if the index is negative or greater than the largest index
   * currently present in the linked list
   */
  @Override
  public Tweet delete(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    Tweet removedTweet;
    if (index == 0) {
      removedTweet = head.getTweet();
      head = head.getNext();
      if (size == 1) {
        tail = null;
      }
    } else {
      TweetNode currentNode = head;
      // move to the index position
      for (int i = 0; i < index - 1; i++) {
        currentNode = currentNode.getNext();
      }
      removedTweet = currentNode.getNext().getTweet();
      currentNode.setNext(currentNode.getNext().getNext());
      if (index == size - 1) {
        tail = currentNode;
      }
    }
    size--;
    return removedTweet;
  }

  /**
   * Sets the iteration mode of this TwitterFeed
   *
   * @param m the iteration mode; any value from the TimelineMode enum
   */
  public void setMode(TimelineMode m) {
    mode = m;
  }

  /**
   * Creates and returns the correct type of iterator based on the current mode of this TwitterFeed
   *
   * @return any of ChronoTwiterator, VerifiedTwiterator, or RatioTwiterator, initialized
   * to the head of this TwitterFeed list
   */
  @Override
  public Iterator<Tweet> iterator() {
    if (mode == TimelineMode.CHRONOLOGICAL) {
      return new ChronoTwiterator(this.head);
    } else if (mode == TimelineMode.VERIFIED_ONLY) {
      return new VerifiedTwiterator(this.head);
    } else if (mode == TimelineMode.LIKE_RATIO){
      return new RatioTwiterator(this.head, ratio);
    } else {
      return null;
    }
  }
}
