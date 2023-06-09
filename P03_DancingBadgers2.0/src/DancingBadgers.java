import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PImage;

/**
 * This is the main class for the p03 Dancing Bangers II program
 *
 */
public class DancingBadgers {

  /**
   * backgound image
   */
  private static PImage backgroundImage; // backgound image
  /**
   * arraylist storing Badger objects
   */
  private static ArrayList<Badger> badgers; // arraylist storing Badger objects

  /**
   * arraylist storing Thing objects
   */
  private static ArrayList<Thing> things; // arraylist storing Thing objects

  /**
   * arraylist storing StarshipRobot objects
   */
  private static ArrayList<StarshipRobot> robots; // arraylist storing StarshipRobot objects

  /**
   * Generator of random numbers
   */
  private static Random randGen; // Generator of random numbers

  /**
   * Maximum number of Badger objects allowed in the basketball court
   */
  private static int badgersCountMax;

  /**
   * Driver method to run this graphic application
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Defines initial environment properties of this graphic application. This method initializes all
   * the data fields defined in this class.
   */
  public static void setup() {
    // set processing data fields for Thing and StarshipRobot classes
    Thing.setProcessing();
    StarshipRobot.setProcessing();

    // data fields initialization
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    badgers = new ArrayList<Badger>();
    randGen = new Random();
    badgersCountMax = 5;

    things = new ArrayList<Thing>();
    robots = new ArrayList<StarshipRobot>();

    // create 4 Things and add them to the things list
    things.add(new Thing(50, 50, "target.png"));
    things.add(new Thing(750, 550, "target.png"));
    things.add(new Thing(750, 50, "shoppingCounter.png"));
    things.add(new Thing(50, 550, "shoppingCounter.png"));

    // create 2 startship robots and add them to the robots list
    robots.add(new StarshipRobot(things.get(2), things.get(0), 3));
    robots.add(new StarshipRobot(things.get(3), things.get(1), 3));
  }


  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  public static void draw() {
    // set the background color and draw the background image to the center of the screen
    Utility.background(Utility.color(255, 218, 185));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);
    
    // draw things, robots, and then badgers

    
    for (int i = 0; i < robots.size(); i++)
      robots.get(i).draw();

    for (int i = 0; i < badgers.size(); i++)
      badgers.get(i).draw();

    
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    // traverse the list of badgers abd start dragging the first clicked one
    for (int i = 0; i < badgers.size(); i++)
      if (badgers.get(i).isMouseOver()) {
        badgers.get(i).startDragging();
        break;
      }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    // traverse the list of badgers and stop dragging any badger
    for (int i = 0; i < badgers.size(); i++)
      badgers.get(i).stopDragging();
  }


  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

    switch (Character.toUpperCase(Utility.key())) {
      case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
                // present in the field is not reached
        if (badgers.size() < badgersCountMax) {
          badgers
              .add(new Badger(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height())));
        }
        break;
      case 'R': // delete the badger being pressed
        for (int i = 0; i < badgers.size(); i++) {
          if (badgers.get(i).isMouseOver()) {
            badgers.remove(i);
            break;
          }
        }
        break;
    }
  }
}
