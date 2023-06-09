//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04_ExceptionalCare
// Course:   CS 300 Spring 2023
//
// Author:   Ziji Li
// Email:    zli2296@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  StackOverFlow: helped me to output files via PrintWriter
// ("https://stackoverflow.com/questions/29750678/
//  how-do-i-write-string-arrays-to-a-text-file-in-java-using-printwriter")
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class is used to create a new instance of Patient Record
 */
public class PatientRecord {
  private static int patientCounter;  //Counts the number of patient case IDs created
  public static final int RED = 0;   //One of the triage levels
  public static final int YELLOW = 1;   //One of the triage levels
  public static final int GREEN = 2;    //One of the triage levels
  public final int CASE_NUMBER;     //The generated case number associated with this patient record
  private int triage;   //This patient's triage level, one of [RED, YELLOW, GREEN]
  private char gender;   //This patient's single-character gender marker
  private int age;  //This patient's age
  private int orderOfArrival;  //The order in which this patient arrived
  private boolean hasBeenSeen;  //Whether this patient has been marked as "seen"

  /**
   * Creates a new patient record and assigns it a CASE_NUMBER using the provided information.
   *
   * @param gender a single-character representation of this patient's reported gender
   * @param age    the age of this patient in years
   * @param triage the triage level of this patient
   * @throws IllegalArgumentException with a descriptive error message if the provided triage value
   *                                  is not one of the class constants
   */
  public PatientRecord(char gender, int age, int triage) throws IllegalArgumentException {
    if (triage != 0 && triage != 1 && triage != 2) {
      throw new IllegalArgumentException("provide a invalid triage");
    }
    this.gender = gender;
    this.age = age;
    this.triage = triage;
    this.orderOfArrival = patientCounter;
    this.CASE_NUMBER = generateCaseNumber(gender, age);
    this.hasBeenSeen = false;
  }

  /**
   * Generates a five-digit case number for this patient using their reported gender and age.
   *
   * @param gender a single-character representation of this patient's reported gender
   * @param age    the age of this patient in years
   * @return a five-digit case number for the patient
   */
  public static int generateCaseNumber(char gender, int age) {
    int caseNumber = 0;
    // check the gender
    if (gender == 'M') {
      caseNumber = 20000;
    } else if (gender == 'F') {
      caseNumber = 10000;
    } else if (gender == 'X') {
      caseNumber = 30000;
    } else {
      caseNumber = 40000;
    }
    // check the age
    caseNumber += age % 100 * 100;
    // count the number of patients on list
    caseNumber += patientCounter++ % 100;
    return caseNumber;
  }

  /**
   * For tester class purposes only: resents PatientRecord.patientCounter to 1. This method should
   * be called at the beginning of EACH tester method to ensure that the methods are not dependent
   * on being called in a particular order.
   */
  public static void resetCounter() {
    patientCounter = 1;
  }

  /**
   * Accessor method for triage
   *
   * @return this PatientRecord's triage value
   */
  public int getTriage() {
    return triage;
  }

  /**
   * Accessor method for gender
   *
   * @return this PatientRecord's gender marker
   */
  public char getGender() {
    return gender;
  }

  /**
   * Accessor method for age
   *
   * @return this PatientRecord's age value
   */
  public int getAge() {
    return age;
  }

  /**
   * Accessor method for order of arrival
   *
   * @return this PatientRecord's  orderOfArrival value
   */
  public int getArrivalOrder() {
    return orderOfArrival;
  }

  /**
   * Accessor method for hasBeenSeen
   *
   * @return true if this patient has been seen, false otherwis
   */
  public boolean hasBeenSeen() {
    return hasBeenSeen;
  }

  /**
   * Marks this patient as having been seen. There is no way to undo this action.
   */
  public void seePatient() {
    hasBeenSeen = true;
  }

  /**
   * Creates and returns a String representation of this PatientRecord using its data field values:
   * [CASE_NUMBER]: [AGE][GENDER] ([TRIAGE])
   *
   * @return a String representation of this PatientRecord
   */
  public String toString() {
    String triageLevel = "";
    if (triage == 0) {
      triageLevel = "RED";
    } else if (triage == 1) {
      triageLevel = "YELLOW";
    } else {
      triageLevel = "GREEN";
    }
    String patientRecord = CASE_NUMBER + ": " + age + gender + " " + "(" + triageLevel + ")";
    return patientRecord;
  }
}
