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
 * This class models StarshipRobot objects which can be triggered to move or do some actions.
 */
public class StarshipRobot extends MovingThing {
  //destination point of this StarshipRobot at its current journey delivering food to badgers
  private Thing destination;
  //source point of this StarshipRobot at its current journey delivering food to badgers
  private Thing source;

  /**
   * Creates a new StarshipRobot and sets its source, destination, and speed. The start point of
   * this StarshipRobot is set to the (x,y) position of source. When created, a StarshipRobot object
   * must face its destination.
   *
   * @param destination Thing object representing the destination point of this StarshipRobot
   * @param source      Thing object representing the source point of this StarshipRobot
   * @param speed       movement speed of this StarshipRobot
   */
  public StarshipRobot(Thing source, Thing destination, int speed) {
    super(source.x, source.y, speed, "starshipRobot.png");
    this.source = source;
    this.destination = destination;
    // make sure it faces the destination
    isFacingRight = destination.x > source.x;
  }

  /**
   * Draws this StarshipRobot to the display window while it is in motion delivering food. This
   * method first prompts this StarshipRobot to go. Then, it draws it to the display window. Think
   * of partial overriding to draw this StarshipRobot as its image is not directly accessed from
   * here.
   */
  @Override
  public void draw() {
    go();
    super.draw();
  }

  /**
   * Checks whether this StarshipRobot is over a specific Thing
   *
   * @param thing a given Thing object
   * @return true if this StarshipRobot is over the Thing object passed as input, otherwise, returns
   * false.
   */
  public boolean isOver(Thing thing) {
    float x1 = x - this.image().width / 2;
    float x2 = x + this.image().width / 2;
    float y1 = y - this.image().height / 2;
    float y2 = y + this.image().height / 2;

    float x3 = thing.x - thing.image().width / 2;
    float x4 = thing.x + thing.image().width / 2;
    float y3 = thing.y - thing.image().height / 2;
    float y4 = thing.y + thing.image().height / 2;

    return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
  }

  /**
   * Helper method to move this StarshipRobot towards its destination
   */
  private void moveTowardsDestination() {
    float dx = destination.x - this.x; // x-move towards destination
    float dy = destination.y - this.y; // y-move towards destination
    int d = (int) Math.sqrt(dx * dx + dy * dy); // distance to destination
    if (d != 0) { // move!
      this.x += speed * dx / d;
      this.y += speed * dy / d;
    }
  }

  /**
   * Implements the action of this StarshipRobot. By default, an StarshipRobot object moves
   * back-and-forth between its source and destination. If the starship robot is over its
   * destination, this method: - switches the source and destination, - switches the value of
   * isFacingRight to its opposite (!isFacingRight), so that the starship robot faces the opposite
   * direction.
   */
  public void go() {
    moveTowardsDestination();
    if (this.isOver(this.destination)) {
      Thing temp = source;
      source = destination;
      destination = temp;
      isFacingRight = !isFacingRight;
    }
  }
}


