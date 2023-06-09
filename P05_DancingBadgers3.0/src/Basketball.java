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
 * This class models Basketball objects. When clicked, the basketball rotate.
 */
public class Basketball extends Thing implements Clickable {
  //Total number of rotations this Basketball object has made since it was created.
  private int rotations;
  //Defines the rotation angle in radians that this Basketball object make when clicked.
  public float rotation;

  /**
   * Creates a new Basketball object located at (x,y) position whose image filename is
   * "basketball.png", and sets its rotation angle to PApplet.PI/2. Initially, when created, the
   * basketball has made zero rotations.
   *
   * @param x x-position of this Basketball object in the display window
   * @param y y-position of this Basketball object in the display window
   */
  public Basketball(float x, float y) {
    super(x, y, "basketball.png");
    this.rotation = processing.PI / 2;
    this.rotations = 0;
  }

  /**
   * Draws this rotating Basketball object to the display window. The implementation details of this
   * method is fully provided in the write-up of p05.
   */
  @Override
  public void draw() {
    // draw this rotating Basketball object at its current position
    processing.pushMatrix();
    processing.translate(x, y);
    processing.rotate(this.rotations * rotation);
    processing.image(image(), 0.0f, 0.0f);
    processing.popMatrix();
  }

  /**
   * Defines the behavior of this basketball when the mouse is pressed. The basketball rotates when
   * it is clicked (the mouse is over it when pressed).
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      rotate();
    }
  }

  /**
   * Called when the mouse is released. A basketball object does nothing when the mouse is released.
   * This is a method with an empty body.
   */
  @Override
  public void mouseReleased() {
  }

  /**
   * This method rotates this basketball object by incrementing the number of its rotations by one.
   */
  public void rotate() {
    rotations++;
  }
}
