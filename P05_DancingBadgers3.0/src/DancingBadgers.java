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

import processing.core.PApplet;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the main class for the p05 Dancing Bangers III program
 */
public class DancingBadgers extends PApplet {
  //background image
  private static processing.core.PImage backgroundImage;

  //Maximum number of Badger objects allowed in the basketball court
  private static int badgersCountMax;

  //Tells whether the dance show is on.
  private boolean danceShowOn;

  //Generator of random numbers
  private static Random randGen;

  //arraylist storing Thing objects
  private static ArrayList<Thing> things;

  // array storing badgers dance show steps
  private static DanceStep[] badgersDanceSteps =
      new DanceStep[] {DanceStep.LEFT, DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT,
          DanceStep.DOWN, DanceStep.LEFT, DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT,
          DanceStep.UP};

  // array storing the positions of the dancing badgers at the start of the dance show
  private static float[][] startDancePositions =
      new float[][] {{300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};

  /**
   * Empty constructor for DancingBadgers class
   */
  public DancingBadgers() {
  }

  /**
   * Driver method to run this graphic application
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    PApplet.main("DancingBadgers");
  }

  /**
   * Sets the size of the display window of this graphic application
   */
  @Override
  public void settings() {
    this.size(800, 600);
  }

  /**
   * Defines initial environment properties of this graphic application. This method initializes all
   * the data fields defined in this class.
   */
  @Override
  public void setup() {
    Thing.setProcessing(this);
    StarshipRobot.setProcessing(this);
    Badger.setProcessing(this);

    this.getSurface().setTitle("P5 Dancing Badgers"); // displays the title of the screen
    this.textAlign(3, 3); // sets the alignment of the text
    this.imageMode(3); // interprets the x and y position of an image to its center
    this.focused = true; // confirms that this screen is "focused", meaning
    // it is active and will accept mouse and keyboard input.
    backgroundImage = loadImage("images" + File.separator + "background.png");
    randGen = new Random();
    badgersCountMax = 5;
    things = new ArrayList<Thing>();
    //initialize the things
    things.add(new Thing(50, 50, "target.png"));
    things.add(new Thing(750, 550, "target.png"));
    things.add(new Thing(750, 50, "shoppingCounter.png"));
    things.add(new Thing(50, 550, "shoppingCounter.png"));

    // create 2 starship robots and add them to the robots list
    things.add(new StarshipRobot(things.get(0), things.get(2), 3));
    things.add(new StarshipRobot(things.get(1), things.get(3), 5));

    // create 2 basketballs
    things.add(new Basketball(50, 300));
    things.add(new Basketball(750, 300));

  }

  /**
   * Callback method that draws and updates the application display window.
   * This method runs in an infinite loop until the program exits.
   */
  @Override
  public void draw() {
    background(color(255, 218, 185));
    image(backgroundImage, this.width / 2, this.height / 2);

    for (int i = 0; i < things.size(); i++) {
      if(things.get(i) != null){
        things.get(i).draw();
      }
    }
  }

  /**
   * Callback method called each time the user presses the mouse. This method iterates through the
   * list of things. If the mouse is over a Clickable object, it calls its mousePressed method, then
   * returns.
   */
  @Override
  public void mousePressed() {
    try {
      for (Thing thing : things) {
        // check if is a Clickable object
        if (thing instanceof Clickable && thing.isMouseOver()) {
          ((Clickable) thing).mousePressed();
          return;
        }
      }
    } catch (NullPointerException e) {
      System.out.println("NullPointerException");
    }
  }

  /**
   * Callback method called each time the mouse is released. This method calls the mousePressed()
   * method on every Clickable object stored in the things list
   */
  @Override
  public void mouseReleased() {
    try {
      for (Thing thing : things) {
        if (thing instanceof Clickable) {
          ((Clickable) thing).mouseReleased();
        }
      }
    } catch (NullPointerException e) {
      System.out.println("NullPointerException");
    }
  }

  /**
   * Gets the number of Badger objects present in the basketball arena
   *
   * @return the number of Badger objects present in the basketball arena
   */
  public int badgersCount() {
    int count = 0;
    try {
      for (Thing thing : things) {
        if (thing instanceof Badger) {
          count++;
        }
      }
    } catch (NullPointerException e) {
      System.out.println("NullPointerException");
    }
    return count;
  }

  /**
   * Sets the badgers start dance positions. The start dance positions of the badgers are provided
   * in the startDancePositions array. The array startDancePositions contains badgersCountMax dance
   * positions. If there are fewer Badger objects in the basketball arena, they will be assigned the
   * first positions.
   */
  private void setStartDancePositions() {
    int positionIndex = 0;

    try {
      for (Thing thing : things) {
        if (thing instanceof Badger) {
          Badger badger = (Badger) thing;

          if (positionIndex < startDancePositions.length) {
            float[] startPosition = startDancePositions[positionIndex];
            badger.x = startPosition[0];
            badger.y = startPosition[1];
            positionIndex++;
            badger.startDancing();
          } else {
            // Stop setting positions if there are no more available start positions
            break;
          }
        }
      }
    } catch (NullPointerException e) {
      System.out.println("NullPointerException");
    }
  }

  /**
   * Callback method called each time the user presses a key. b-key: If the b-key is pressed and the
   * danceShow is NOT on, a new badger is added to the list of things. Up to badgersCountMax can be
   * added to the basketball arena.
   *
   * c-key: If the c-key is pressed, the danceShow is terminated (danceShowOn set to false), and ALL
   * MovingThing objects are removed from the list of things. This key removes MovingThing objects
   * ONLY.
   *
   * d-key: This key starts the dance show if the danceShowOn was false, and there is at least one
   * Badger object in the basketball arena. Starting the dance show, sets the danceShowOn to true,
   * sets the start dance positions of the Badger objects, and calls the startDancing() method on
   * every Badger object present in the list of things. Pressing the d-key when the danceShowOn is
   * true or when there are no Badger objects present in the basketball arena has no effect.
   *
   * r-key: If the danceShow is NOT on and the d-key is pressed when the mouse is over a Badger
   * object, this badger is removed from the list of things. If the mouse is over more than one
   * badger, the badger at the lowest index position will be deleted.
   *
   * s-key: If the s-key is pressed, the dancdShow is terminated and all the Badger objects stop
   * dancing. Pressing the s-key does not remove any thing.
   */
  @Override
  public void keyPressed() {
    // press the key b to create new badgers
    if (key == 'b' && !danceShowOn && badgersCount() < badgersCountMax) {
      things.add(
          new Badger(randGen.nextInt(this.width), randGen.nextInt(this.height), badgersDanceSteps));
    }
    //press the key c tp remove all the MovingThings
    if (key == 'c') {
      danceShowOn = false;
      things.removeIf(thing -> thing instanceof MovingThing);
    }
    //press the key d to start dance show
    if (key == 'd' && !danceShowOn && badgersCount() > 0) {
      danceShowOn = true;
      setStartDancePositions();
      for (Thing thing : things) {
        if (thing instanceof Badger) {
          ((Badger) thing).startDancing();
        }
      }
    }
    //press the key r to remove badgers
    if (key == 'r' && !danceShowOn) {
      for (int i = 0; i < things.size(); i++) {
        if (things.get(i) instanceof Badger && ((Badger) things.get(i)).isMouseOver()) {
          things.remove(i);
          break;
        }
      }
    }
    //press the key s to stop the dance show
    if (key == 's') {
      danceShowOn = false;
      for (Thing thing : things) {
        if (thing instanceof Badger) {
          ((Badger) thing).stopDancing();
        }
      }
    }
  }
}
