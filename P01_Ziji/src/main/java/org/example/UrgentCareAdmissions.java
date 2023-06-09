//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    UrgentCareAdmissionTester
// Course:   CS 300 Spring 2023
//
// Author:   Ziji Li
// Email:    zli2296@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:   None      (identify each by name and describe how they helped)
// Online Sources: https://www.educba.com/2d-arrays-in-java/
//          (It helped me on creating the add/removePatient method)
//
///////////////////////////////////////////////////////////////////////////////


/**
 * This class models the process of urgent care clinic operation
 *
 * @author ziji
 */
public class UrgentCareAdmissions {
  public static final int RED = 0;
  public static final int YELLOW = 1;
  public static final int GREEN = 2;

  /**
   * A helper method to find the correct index to insert a new patient at the given triage level.
   * This should be the index AFTER the last index currently occupied by a patient at that level.
   *
   * @param triage       the urgency level of the next patient's issue, RED YELLOW or GREEN
   * @param patientsList the current, active list of patient records
   * @param size         the number of patients currently in the list
   * @return the index at which the patient should be inserted into the list, or -1 if the list is
   * full
   */
  public static int getAdmissionIndex(int triage, int[][] patientsList, int size) {
    //set a local variable for getting index
    int index = 0;
    if (size == patientsList.length) {
      //the patient list is full
      return -1;
    }
    for (int i = 0; i < size; i++) {
      if (triage >= patientsList[i][2]) {
        index++;
      }
    }
    return index;
  }

  /**
   * Adds the patient record, a three-element perfect size array of ints, to the patients list in
   * the given position. This method must maintain the ordering of the rest of the array, so any
   * patients in higher index positions must be shifted out of the way.
   *
   * @param patientRecord a three-element perfect size array of ints, containing the patient's
   *                      5-digit case ID, their admission order number, and their triage level
   * @param index         the index at which the patientRecord should be added to patientsList,
   *                      assumed to correctly follow the requirements of getAdmissionIndex()
   * @param patientsList  the current, active list of patient records
   * @param size          the number of patients currently in the list
   * @return the number of patients in patientsList after this method has finished running
   */
  public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
    //return size when the index is invalid and when the array is full
    if (index == -1 || index > size || size == patientsList.length) {
      return size;
    }
    for (int row = size - 1; row >= index; row--) {
      patientsList[row + 1] = patientsList[row];
    }
    //add the patient to the index position
    patientsList[index] = patientRecord;
    return size + 1;
  }

  /**
   * Removes the patient record at index 0 of the patientsList, if there is one, and updates the
   * rest of the list to maintain the oversize array in its current ordering.
   *
   * @param patientsList the current, active list of patient records
   * @param size         the number of patients currently in the list
   * @return the number of patients in patientsList after this method has finished running
   */
  public static int removeNextPatient(int[][] patientsList, int size) {
    //reture 0 when the array is empty
    if (size == 0) {
      return 0;
    }
    // Delete the array at index 0
    for (int row = 0; row < size - 1; row++) {
      patientsList[row] = patientsList[row + 1];
    }
    patientsList[size - 1] = null;
    return size - 1;
  }

  /**
   * Finds the index of a patient given their caseID number. This method must not modify
   * patientsList in any way.
   *
   * @param caseID       the five-digit case number assigned to the patient record to find
   * @param patientsList the current, active list of patient records
   * @param size         the number of patients currently in the list
   * @return the index of the patient record matching the given caseID number, or -1 if the list is
   * empty or the caseID is not found
   */
  public static int getPatientIndex(int caseID, int[][] patientsList, int size) {
    //return -1 when it's empty
    if (size == 0) {
      return -1;
    }
    //set the patient index as -1, and find the caseID through loop
    int pIndex = -1;
    for (int i = 0; i < size; i++) {
      if (patientsList[i][0] == caseID) {
        pIndex = i;
      }
    }
    //when it returns -1, it means the caseId is not found
    return pIndex;
  }

  /**
   * Finds the patient who arrived the earliest still currently present in the patientsList, and
   * returns the index of their patient record within the patientsList. The arrival value is
   * strictly increasing for each new patient, so you will not need to handle the case where two
   * values are equal
   *
   * @param patientsList the current, active list of patient records
   * @param size         the number of patients currently in the list
   * @return the index of the patient record with the smallest value in their arrival integer, or -1
   * if the list is empty
   */
  public static int getLongestWaitingPatientIndex(int[][] patientsList, int size) {
    if (size == 0) {
      return -1;
    }
    //find the smallest number by moving arrays
    int earliestIndex = 0;
    for (int i = 1; i < size; i++) {
      if (patientsList[earliestIndex][1] > patientsList[i][1]) {
        earliestIndex = i;
      }
    }
    return earliestIndex;
  }

  /**
   * Creates a formatted String summary of the current state of the patientsList array
   *
   * @param patientsList the current, active list of patient records
   * @param size         the number of patients currently in the list
   * @return a String summarizing the patientsList as shown in this comment
   */
  public static String getSummary(int[][] patientsList, int size) {
    int red = 0;
    int yellow = 0;
    int green = 0;
    for (int i = 0; i < size; i++) {
      if (patientsList[i][2] == 0) {
        red++;
      }
      if (patientsList[i][2] == 1) {
        yellow++;
      }
      if (patientsList[i][2] == 2) {
        green++;
      }
    }
    return "Total number of patients: " + size + '\n' +
        "RED: " + red + '\n' +
        "YELLOW: " + yellow + '\n' +
        "GREEN: " + green + '\n';
  }
}
