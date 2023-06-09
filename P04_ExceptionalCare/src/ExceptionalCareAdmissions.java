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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is used to operate patient admissions with exceptions
 */
public class ExceptionalCareAdmissions {
  private PatientRecord[] patientsList;  //An oversize array containing the PatientRecords currently active in this object
  private int size;  //The number of values in the oversize array

  /**
   * Creates a new, empty ExceptionalCareAdmissions object with given capacity
   *
   * @param capacity the maximum number of patient records this ExceptionalCareAdmissions can hold
   * @throws IllegalArgumentException if capacity is negative
   */
  public ExceptionalCareAdmissions(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity must be bigger than 0");
    }
    this.patientsList = new PatientRecord[capacity];
    this.size = 0;
  }

  /**
   * An accessor method to determine if the patientList is currently full
   *
   * @return true if the patientList, false otherwise
   */
  public boolean isFull() {
    return size == patientsList.length;
  }

  /**
   * An accessor method to get the current number of patient records in the patientList
   *
   * @return the current number of patientRecords objects
   */
  public int size() {
    return size;
  }

  /**
   * Access the curent number of patient records in this patientListrepresenting patients who have
   * already been seen(and could be removed)
   *
   * @return the current couny of patientRecords for which the hasBeenSeen() method returns true
   */
  public int getNumberSeenPatients() {
    int numberSeen = 0;
    for (PatientRecord r : patientsList) {
      //check if the patient has been seen
      if (r != null && r.hasBeenSeen()) {
        numberSeen++;
      }
    }
    return numberSeen;
  }

  /**
   * A helper method to find the correct index to insert a new patient given a patient record
   *
   * @param rec the PatientRecord to be added to the list
   * @return the correct index of patientList at which rec should be added
   * @throws IllegalArgumentException if rec is null - with the message "cleanPatientsList()" if the
   *                                  patientsList contains any patients who have been seen, or -
   *                                  with the message "Cannot admit new patients" if there are NO
   *                                  seen patients in the patientsList
   */
  public int getAdmissionIndex(PatientRecord rec) throws IllegalStateException {
    if (size == 0) {
      return 0;
    }
    int index = 0;
    if (size == patientsList.length) {
      for (int i = 0; i < size; i++) {
        // throw exception if any patient has been seen
        if (patientsList[i].hasBeenSeen()) {
          throw new IllegalStateException("cleanPatientsList()");
        }
      }
      throw new IllegalStateException("Cannot admit new patients");
    }
    // start to find the index
    for (int i = 0; i < size; i++) {
      if (rec.getTriage() >= patientsList[i].getTriage()) {
        index++;
      }
    }
    return index;
  }

  /**
   * Adds the provided PatientRecord at the provided position in the oversize patientsList array.
   *
   * @param rec   the PatientRecord to be added
   * @param index the index at which the PatientRecord should be added to patientsList, which you
   *              may assume is the same as the output of getAdmissionIndex()
   * @throws IllegalStateException    if the PatientRecord is full - with the message
   *                                  "cleanPatientsList()" if the patientsList contains any
   *                                  patients who have been seen, or - with the message "Cannot
   *                                  admit new patients" if there are NO seen patients in the
   *                                  patientsList
   * @throws IllegalArgumentException with a descriptive error message if the patientsList is - NOT
   *                                  full and the index is not a valid index into the oversize
   *                                  array
   */
  public void addPatient(PatientRecord rec, int index)
      throws IllegalStateException, IllegalArgumentException {
    if (size == patientsList.length) {
      for (int i = 0; i < size; i++) {
        // throw exception if any patient has been seen
        if (patientsList[i].hasBeenSeen()) {
          throw new IllegalStateException("cleanPatientsList()");
        }
      }
      throw new IllegalStateException("Cannot admit new patients");
    }
    // throw exception if index is invalid
    if (index < 0 || index > size) {
      throw new IllegalArgumentException("index out of bounds");
    }
    // move the array and insert new patient
    for (int row = size - 1; row >= index; row--) {
      patientsList[row + 1] = patientsList[row];
    }
    patientsList[index] = rec;
    size++;
  }

  /**
   * Marks the patient with the given caseID as having been seen
   *
   * @param caseID the CASE_NUMBER of the PatientRecord to be marked as having been seen
   * @throws IllegalStateException    if the patientsList is currently empty
   * @throws IllegalArgumentException if no PatientRecord with the given caseID is found
   */
  public void seePatient(int caseID) throws IllegalStateException, IllegalArgumentException {
    if (size == 0) {
      throw new IllegalStateException("the list is empty");
    }
    // mark the patient as seen and return
    for (int i = 0; i < size; i++) {
      if (patientsList[i].CASE_NUMBER == caseID) {
        patientsList[i].seePatient();
        return;
      }
    }
    throw new IllegalArgumentException("the caseID is not in the list");
  }

  /**
   * Creates a formatted String summary of the current state of the patientsList, incorporating an
   * additional component from the PatientRecord class.
   *
   * @return a String summarizing the patientsList as shown in this comment
   */
  public String getSummary() {
    int red = 0;
    int yellow = 0;
    int green = 0;
    int seen = 0;
    for (int i = 0; i < size; i++) {
      if (patientsList[i].hasBeenSeen()) {
        seen++;
      }
      if (patientsList[i].getTriage() == 0) {
        red++;
      }
      if (patientsList[i].getTriage() == 1) {
        yellow++;
      }
      if (patientsList[i].getTriage() == 2) {
        green++;
      }
    }
    return "Total number of patients: " + size + '\n'
        + "Total number seen: " + seen + '\n'
        + "RED: " + red + '\n'
        + "YELLOW: " + yellow + '\n'
        + "GREEN: " + green + '\n';
  }

  /**
   * This method runs occasionally to record the current state of the patientsList and save any
   * records for seen patients to a file, while removing them from the patientsList to make more
   * room.
   *
   * @param file the file object to use for recording the data
   */
  public void cleanPatientsList(File file) {
    if (!file.exists() || size == 0) {
      return;
    }
    //create a list to collect the patients who have been seen
    ExceptionalCareAdmissions seenPatients = new ExceptionalCareAdmissions(size);
    for (int row = 0; row < size; row++) {
      if (patientsList[row] != null && patientsList[row].hasBeenSeen()) {
        seenPatients.addPatient(patientsList[row], seenPatients.size);
        // use a loop to move all the elements
        for (int i = row; i < size - 1; i++) {
          patientsList[i] = patientsList[i + 1];
        }
        patientsList[size - 1] = null;
        // if the element is removed, we have to go back to the beginning index
        // to check the new index at row == 0 position
        row--;
        size--;
      }
    }
    // output to the file
    try {
      PrintWriter pw = new PrintWriter(file);
      pw.print(getSummary());
      pw.println(seenPatients.toString());
      pw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * For testing purposes: this method creates and returns a string representation of the
   * patientsList, as the in-order string representation of each patient in the list on a separate
   * line. If patientsList is empty, returns an empty string.
   *
   * @return a string representation of the contents of patientsList
   */
  public String toString() {
    String returnValue = "";
    for (PatientRecord r : patientsList) {
      returnValue += (r != null) ? r.toString() + "\n" : "";
    }
    return returnValue.trim();
  }
}

