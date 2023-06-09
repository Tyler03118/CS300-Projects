//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05_DancingBadgers3.0
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
// Online Sources:  https://www.geeksforgeeks.org/arraylist-removeif-method-in-java/
//                  https://www.w3schools.com/java/java_lambda.asp
//  (Learned how to use removeIf method; Learned how to write concise code via lambda expressions)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models moving thing objects. A moving thing is defined by
 * its speed and to which direction it is facing (right or left).
 */
public class MovingThing extends Thing implements Comparable<MovingThing> {
  //indicates whether this MovingThing is facing right or not
  protected boolean isFacingRight;
  //movement speed of this MovingThing
  protected int speed;

  /**
   * Creates a new graphic Thing located at a specific (x, y) position of the display window
   *
   * @param x             x-coordinate of this thing in the display window
   * @param y             y-coordinate of this thing in the display window
   * @param speed         speed of this moving object
   * @param imageFileName filename of the image of this thing, for instance "name.png"
   */
  public MovingThing(float x, float y, int speed, String imageFileName) {
    super(x, y, imageFileName);
    this.speed = speed;
    this.isFacingRight = true;
  }

  /**
   * Draws this MovingThing at its current position. The implementation details of this method is
   * fully provided in the write-up of p05.
   */
  @Override
  public void draw() {
    // draw this MovingThing at its current position
    processing.pushMatrix();
    processing.rotate(0.0f);
    processing.translate(x, y);
    if (!isFacingRight) {
      processing.scale(-1.0f, 1.0f);
    }
    processing.image(image(), 0.0f, 0.0f);
    processing.popMatrix();
  }

  /**
   * Compares this object with the specified MovingThing for order, in the increasing order of their
   * speeds.
   *
   * @param other the MovingThing object to be compared
   * @return zero if this object and other have the same speed, a negative integer if the speed of
   * this moving object is less than the speed of other, and a positive integer otherwise.
   */
  @Override
  public int compareTo(MovingThing other) {
    return Integer.compare(this.speed, other.speed);
  }
}
