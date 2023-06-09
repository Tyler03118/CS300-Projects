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
////////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an iterator that moves through tweets in reverse chronological order by high like ratio
 * only
 */
public class RatioTwiterator implements Iterator<Tweet> {
  // The TweetNode containing the next tweet to be returned in the iteration
  private TweetNode next;
  // The minimum threshold value for the ratio of likes to total engagement
  private final double THRESHOLD;

  /**
   * Constructs a new twiterator at the given starting node.
   *
   * @param startNode the node to begin the iteration at
   * @param threshold the minimum threshold value for the ratio of likes to total engagement
   */
  public RatioTwiterator(TweetNode startNode, double threshold) {
    this.THRESHOLD = threshold;
    // Find the first qualified node
    while (startNode != null && startNode.getTweet().getLikesRatio() < THRESHOLD) {
      startNode = startNode.getNext();
    }
    this.next = startNode;
  }

  /**
   * Checks whether there is a next tweet to return
   *
   * @return true if there is a next tweet
   */
  @Override
  public boolean hasNext() {
    return next != null;
  }

  /**
   * Returns the next tweet in the iteration if one exists, and advances next to the next tweet with
   * a likes ratio in excess of the given threshold
   *
   * @throws NoSuchElementException if there is not a next tweet to return
   */
  @Override
  public Tweet next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("No more tweets with a high enough likes ratio.");
    }
    Tweet tweet = next.getTweet();
    next = next.getNext();
    // Advance to the next qualified node
    while (next != null && next.getTweet().getLikesRatio() < THRESHOLD) {
      next = next.getNext();
    }
    return tweet;
  }
}
