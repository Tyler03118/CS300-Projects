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
 * This class models a Badger object in the P05 Dancing Badgers III programming assignment
 */
public class Badger extends MovingThing implements Clickable {
  // array storing this Badger's dance show steps
  private DanceStep[] danceSteps;
  //indicates whether this badger is dancing or not
  private boolean isDancing;
  //indicates whether this badger is being dragged or not
  private boolean isDragging;
  //stores the next dance (x, y) position of this Badger.
  private float[] nextDancePosition;
  //old x-position of the mouse
  private static int oldMouseX;
  //old y-position of the mouse
  private static int oldMouseY;
  //index position of the current dance step of this badger
  private int stepIndex;

  /**
   * Creates a new Badger object positioned at a specific position of the display window and whose
   * moving speed is 2. When created, a new badger is not dragging and is not dancing. This
   * constructor also sets the danceSteps of the created Badger to the one provided as input and
   * initializes stepIndex to 1.
   *
   * @param x          x position of the Badger object within the display window
   * @param y          y position of the Badger object within the display window
   * @param danceSteps perfect-size array storing the dance steps of this badger
   */
  public Badger(float x, float y, DanceStep[] danceSteps) {
    super(x, y, 2, "badger.png");
    this.danceSteps = danceSteps;
    this.isDancing = false;
    this.isDragging = false;
    this.nextDancePosition = new float[2];
    this.stepIndex = 1;
  }

  /**
   * Draws this badger to the display window. Also, this method: - calls the drag() behavior if this
   * Badger is dragging - calls the dance() behavior if this Badger is dancing
   */
  @Override
  public void draw() {
    if (isDragging) {
      drag();
    }
    if (isDancing) {
      dance();
    }
    super.draw();
  }

  /**
   * Checks whether this badger is being dragged
   *
   * @return true if the badger is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Helper method to drag this Badger object to follow the mouse moves
   */
  private void drag() {
    // same code version from P03
    int dx = processing.mouseX - oldMouseX;
    int dy = processing.mouseY - oldMouseY;
    x += dx;
    y += dy;

    if (x > 0)
      x = Math.min(x, processing.width);
    else
      x = 0;
    if (y > 0)
      y = Math.min(y, processing.height);
    else
      y = 0;
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }

  /**
   * Starts dragging this badger
   */
  public void startDragging() {
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
    this.isDragging = true;
    drag();
  }

  /**
   * Stops dragging this badger
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Defines the behavior of this Badger when it is clicked. If the mouse is over this badger and
   * this badger is NOT dancing, this method starts dragging this badger.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver() && !isDancing) {
      startDragging();
    }
  }

  /**
   * Defines the behavior of this Badger when the mouse is released. If the mouse is released, this
   * badger stops dragging.
   */
  @Override
  public void mouseReleased() {
    stopDragging();
  }

  /**
   * This helper method moves this badger one speed towards its nextDancePosition. Then, it checks
   * whether this Badger is facing right and updates the isFacingRight data field accordingly. After
   * making one move dance, a badger is facing right if the x-move towards its next dance position
   * is positive, otherwise, it is facing left.
   *
   * @return true if this Badger almost reached its next dance position, meaning that the distance
   * to its next dance position is less than 2 times its speed. Otherwise, return false.
   */
  private boolean makeMoveDance() {
    float dx = nextDancePosition[0] - this.x;
    float dy = nextDancePosition[1] - this.y;
    float distance = (float) Math.sqrt(dx * dx + dy * dy);
    // Move one step towards nextDancePosition
    this.x += speed * dx / distance;
    this.y += speed * dy / distance;

    // Check if facing right or left
    if (dx > 0) {
      isFacingRight = true;
    } else {
      isFacingRight = false;
    }

    // Check if almost reached the next dance position
    if (distance < 2 * speed) {
      return true;
    }

    return false;
  }

  /**
   * Implements the dance behavior of this Badger. This method prompts the Badger to make one move
   * dance. If the makeMoveDance method call returns true (meaning the badger almost reached its
   * nextDancePosition), this method MUST: - update its next dance position (see
   * DanceStep.getPositionAfter()), - increment the stepIndex.
   */
  private void dance() {
    if (makeMoveDance()) {
      // Make sure it's circular
      stepIndex = (stepIndex + 1) % danceSteps.length;
      nextDancePosition = danceSteps[stepIndex].getPositionAfter(nextDancePosition[0], nextDancePosition[1]);
    }
  }

  /**
   * Prompts this badger to start dancing. This method: - updates the isDancing data field - stops
   * dragging this badger - sets stepIndex to zero - Resets the nextDancePosition
   */
  public void startDancing() {
    isDancing = true;
    stopDragging();
    stepIndex = 0;
    nextDancePosition = danceSteps[stepIndex].getPositionAfter(x, y);
  }

  /**
   * Prompts this badger to stop dancing. Sets the isDancing data field to false.
   */
  public void stopDancing() {
    isDancing = false;
  }
}
