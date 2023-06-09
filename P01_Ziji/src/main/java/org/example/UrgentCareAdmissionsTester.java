import java.util.Arrays; // consider using Arrays.deepEquals() to test the contents of a 2D array
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
 * This class is the tester for urgent care operation methods
 *
 * @author ziji
 */
public class UrgentCareAdmissionsTester {

  /**
   * This test method is provided for you in its entirety, to give you a model for the rest of this
   * class. This method tests the getAdmissionIndex() method on a non-empty, non-full array of
   * patient records which we create and maintain here.
   * <p>
   * This method tests three scenarios:
   * <p>
   * 1. Adding a patient with a HIGHER triage priority than any currently present in the array. To
   * do this, we create an array with no RED priority patients and get the index to add a RED
   * priority patient. We expect this to be 0, so if we get any other value, the test fails.
   * <p>
   * 2. Adding a patient with a LOWER triage priority than any currently present in the array. To do
   * this, we create an array with no GREEN priority patients and get the index to add a GREEN
   * priority patient. We expect this to be the current size of the oversize array, so if we get any
   * other value, the test fails.
   * <p>
   * 3. Adding a patient with the SAME triage priority as existing patients. New patients at the
   * same priority should be added AFTER any existing patients. We test this for all three triage
   * levels on an array containing patients at all three levels.
   *
   * @return true if and only if all test scenarios pass, false otherwise
   * @author hobbes
   */
  public static boolean testGetIndex() {

    // The non-empty, non-full oversize arrays to use in this test.
    // Note that we're using the UrgentCareAdmissions named constants to create these test records,
    // rather than their corresponding literal int values. 
    // This way if the numbers were to change in UrgentCareAdmissions, our test will still be valid
    int[][] patientsListAllLevels = new int[][]
        {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
        {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
    int allLevelsSize = 5;

    int[][] patientsListOnlyYellow = new int[][]
        {{21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW}, null, null, null, null, null};
    int onlyYellowSize = 3;

    int[][] patientsListFull = new int[][]
        {{21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW}};
    int patientsFullSize = 3;

    // scenario 1: add a patient with a higher priority than any existing patient
    {
      int expected = 0;
      int actual =
          UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, patientsListOnlyYellow,
              onlyYellowSize);
      if (expected != actual)
        return false;
    }

    // scenario 2: add a patient with a lower priority than any existing patient
    {
      int expected = onlyYellowSize;
      int actual =
          UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, patientsListOnlyYellow,
              onlyYellowSize);
      if (expected != actual)
        return false;
    }

    // scenario 3: verify that a patient with the same priority as existing patients gets
    // added after all of those patients
    {
      int expected = 1;
      int actual =
          UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, patientsListAllLevels,
              allLevelsSize);
      if (expected != actual)
        return false;

      expected = 4;
      actual =
          UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, patientsListAllLevels,
              allLevelsSize);
      if (expected != actual)
        return false;

      expected = allLevelsSize;
      actual =
          UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, patientsListAllLevels,
              allLevelsSize);
      if (expected != actual)
        return false;
    }

    // scenario 4: verify what's happening when it's full
    {
      int expected = -1;
      int actual =
          UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, patientsListFull,
              patientsFullSize);
      if (expected != actual)
        return false;
    }

    // and finally, verify that the arrays were not changed at all
    {
      int[][] allLevelsCopy = new int[][]
          {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
          {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;

      int[][] onlyYellowCopy = new int[][]
          {{21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, null, null, null, null, null};
      if (!Arrays.deepEquals(patientsListOnlyYellow, onlyYellowCopy))
        return false;
    }
    return true;
  }

  /**
   * Tests the behavior of the addPatient method using a non-empty, non-full array. Each test should
   * verify that the returned size is as expected and that the array has been updated (or not)
   * appropriately
   *
   * @return the boolean value of the test: if it is false, then the test failed, if it is true in
   * the end, then the test passed
   */
  public static boolean testAddPatient() {
    int[][] patientsListAllLevels = new int[][]
        {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
        {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
    int[] patientRecordEnd1 = new int[] {38888, 6, UrgentCareAdmissions.GREEN};
    int[] patientRecordMid1 = new int[] {35555, 6, UrgentCareAdmissions.YELLOW};
    int allLevelsSize = 5;

    int[][] patientsListTwoLevels = new int[][]
        {{21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.GREEN}, null, null, null, null, null};
    int[] patientRecordEnd2 = new int[] {38889, 6, UrgentCareAdmissions.GREEN};
    int[] patientRecordMid2 = new int[] {34444, 6, UrgentCareAdmissions.YELLOW};
    int twoLevelSize = 3;

    int[][] patientsListFull= new int[][]
        {{21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
            {11901, 5, UrgentCareAdmissions.GREEN}};
    int[] patientRecordEnd3 = new int[] {34999, 6, UrgentCareAdmissions.YELLOW};
    int fullLevelSize = 3;
    //(1) add a patient to the END of the patientsList
    {
      int expected = 6;
      int actual = UrgentCareAdmissions.addPatient(patientRecordEnd1, 5, patientsListAllLevels,
          allLevelsSize);
      if (expected != actual)
        return false;

      expected = 4;
      actual = UrgentCareAdmissions.addPatient(patientRecordEnd2, 3, patientsListTwoLevels,
          twoLevelSize);
      if (expected != actual)
        return false;
    }
    // (2) add a patient to the MIDDLE of the patientsList
    {
      int expected = 6;
      int actual = UrgentCareAdmissions.addPatient(patientRecordMid1, 4, patientsListAllLevels,
          allLevelsSize);
      if (expected != actual)
        return false;

      expected = 4;
      actual = UrgentCareAdmissions.addPatient(patientRecordMid2, 2, patientsListTwoLevels,
          twoLevelSize);
      if (expected != actual)
        return false;
    }
    // (3) add a patient using an invalid (out-of-bounds) index
    {
      int expected = allLevelsSize;
      int actual = UrgentCareAdmissions.addPatient(patientRecordEnd1, 9, patientsListAllLevels,
          allLevelsSize);
      if (expected != actual)
        return false;

      expected = twoLevelSize;
      actual = UrgentCareAdmissions.addPatient(patientRecordMid2, 10, patientsListTwoLevels,
          twoLevelSize);
      if (expected != actual)
        return false;
    }
    // (3) add a patient using a full array
    {
      int expected = fullLevelSize;
      int actual = UrgentCareAdmissions.addPatient(patientRecordEnd3, 3, patientsListFull,
          fullLevelSize);
      if (expected != actual)
        return false;
    }
    // and finally, verify that the elements of arrays are correctly added
    {
      int[][] allLevelsCopy = new int[][]
          {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
          {35555, 6, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN},
          null, null};
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;

      int[][] twoLevelCopy = new int[][]
          {{21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {34444, 6, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.GREEN}, null, null, null, null};
      if (!Arrays.deepEquals(patientsListTwoLevels, twoLevelCopy))
        return false;
    }
    return true;
  }

  /**
   * Tests the behavior of the removeNextPatient method using a non-empty, non-full array. Each test
   * should verify that the returned size is as expected and that the array has been updated (or
   * not) appropriately
   *
   * @return the boolean value of the test: if it is false, then the test failed, if it is true in
   * the end, then the test passed
   */
  public static boolean testRemovePatient() {
    int[][] patientsListRecords = new int[][]
        {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
        {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
    int patientListRecordsSize = 5;

    int[][] patientsListOneRecord = new int[][]
        {{21801, 2, UrgentCareAdmissions.YELLOW}, null, null, null, null, null};
    int patientsListOneRecordSize = 1;

    // (1) remove a patient from a patientsList containing more than one record
    {
      int expected = 4;
      int actual =
          UrgentCareAdmissions.removeNextPatient(patientsListRecords, patientListRecordsSize);
      if (expected != actual)
        return false;
    }
    // (2) remove a patient from a patientsList containing only one record
    {
      int expected = 0;
      int actual =
          UrgentCareAdmissions.removeNextPatient(patientsListOneRecord, patientsListOneRecordSize);
      if (expected != actual)
        return false;
    }
    // and finally, verify that the elements of arrays are correctly removed
    {
      int[][] patientsListRecordsCopy = new int[][]
          {{21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN},
          null, null, null, null};
      if (!Arrays.deepEquals(patientsListRecords, patientsListRecordsCopy))
        return false;

      int[][] patientsListOneRecordCopy = new int[][] {null, null, null, null, null, null};
      if (!Arrays.deepEquals(patientsListOneRecord, patientsListOneRecordCopy))
        return false;
    }
    return true;
  }

  /**
   * Tests the behavior of the getPatientIndex method using a non-empty, non-full array.Each test
   * should verify that the returned index is as expected and that the array stays the same
   *
   * @return the boolean value of the test: if it is false, then the test failed, if it is true in
   * the end, then the test passed
   */
  public static boolean testGetPatientIndex() {
    int[][] patientsListAllLevels = new int[][]
        {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
        {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
    int allLevelsSize = 5;

    int[][] patientsListOnlyYellow = new int[][]
        {{21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW}, null, null, null, null, null};
    int onlyYellowSize = 3;

    //(1) look for a patient at the end of the list
    {
      int expected = 4;
      int actual =
          UrgentCareAdmissions.getPatientIndex(31501, patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;

      expected = 2;
      actual = UrgentCareAdmissions.getPatientIndex(11901, patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual)
        return false;
    }
    // (2) look for a patient in the middle of the list
    {
      int expected = 2;
      int actual =
          UrgentCareAdmissions.getPatientIndex(22002, patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;

      expected = 1;
      actual = UrgentCareAdmissions.getPatientIndex(22002, patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual)
        return false;
    }
    // (3) look for a patient not present in the list
    {
      int expected = -1;
      int actual =
          UrgentCareAdmissions.getPatientIndex(66666, patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;

      expected = -1;
      actual = UrgentCareAdmissions.getPatientIndex(88888, patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual)
        return false;
    }
    // and finally, verify that the arrays were not changed at all
    {
      int[][] patientsListAllLevelsCopy = new int[][]
          {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
          {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
      if (!Arrays.deepEquals(patientsListAllLevels, patientsListAllLevelsCopy))
        return false;

      int[][] patientsListOnlyYellowCopy = new int[][]
          {{21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, null, null, null, null, null};
      if (!Arrays.deepEquals(patientsListOnlyYellow, patientsListOnlyYellowCopy))
        return false;
    }
    return true;
  }

  /**
   * Tests the behavior of the getLongestWaitingPatientIndex method using a non-empty, non-full
   * array. Each test should verify that the returned index is as expected and that the array stays
   * the same
   *
   * @return the boolean value of the test: if it is false, then the test failed, if it is true in
   * the end, then the test passed
   */
  public static boolean testLongestWaitingPatient() {
    int[][] patientsListOne = new int[][]
        {{32702, 3, UrgentCareAdmissions.RED}, null, null, null};
    int patientsListOneSize = 1;

    int[][] patientsListFour = new int[][]
        {{21801, 3, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 2, UrgentCareAdmissions.YELLOW}, {11905, 8, UrgentCareAdmissions.GREEN},
        null, null, null, null, null};
    int patientsListFourSize = 4;

    // (1) call the method on a patientsList with only one patient
    {
      int expected = 0;
      int actual =
          UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListOne, patientsListOneSize);
      if (expected != actual)
        return false;
    }
    // (2) call the method on a patientsList with at least three patients
    {
      int expected = 2;
      int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListFour,
          patientsListFourSize);
      if (expected != actual)
        return false;
    }
    // and finally, verify that the arrays were not changed at all
    {
      int[][] patientsListOneCopy =
          new int[][] {{32702, 3, UrgentCareAdmissions.RED}, null, null, null};
      if (!Arrays.deepEquals(patientsListOne, patientsListOneCopy))
        return false;

      int[][] patientsListFourCopy = new int[][] {{21801, 3, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 2, UrgentCareAdmissions.YELLOW},
          {11905, 8, UrgentCareAdmissions.GREEN}, null, null, null, null, null};
      if (!Arrays.deepEquals(patientsListFour, patientsListFourCopy))
        return false;
    }
    return true;
  }

  /**
   * Tests the edge case behavior of the UrgentCareAdmissions methods using empty and full arrays
   *
   * @return the boolean value of the test: if it is false, then the test failed, if it is true in
   * the end, then the test passed
   */
  public static boolean testEmptyAndFullArrays() {
    int[] patientRecord = new int[] {23203, 9, UrgentCareAdmissions.YELLOW};
    int[][] patientsListEmpty = new int[][] {null, null, null};
    int patientsListEmptySize = 0;

    int[][] patientsListFull = new int[][]
        {{21801, 3, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 2, UrgentCareAdmissions.YELLOW}, {11905, 8, UrgentCareAdmissions.GREEN}};
    int patientsListFullSize = 4;
    // (1) test getAdmissionIndex using an empty patientsList array and any triage level
    {
      int expected = 0;
      int actual =
          UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, patientsListEmpty,
              patientsListEmptySize);
      if (expected != actual)
        return false;

      expected = 0;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, patientsListEmpty,
          patientsListEmptySize);
      if (expected != actual)
        return false;
    }
    // (2) test getAdmissionIndex using a full patientsList array and any triage level
    {
      int expected = -1;
      int actual =
          UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, patientsListFull,
              patientsListFullSize);
      if (expected != actual)
        return false;

      expected = -1;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, patientsListFull,
          patientsListFullSize);
      if (expected != actual)
        return false;
    }
    // (3) test addPatient using a full patientsList array
    {
      int expected = patientsListFullSize;
      int actual =
          UrgentCareAdmissions.addPatient(patientRecord, 3, patientsListFull, patientsListFullSize);
      if (expected != actual)
        return false;
    }
    // (4) test removeNextPatient using an empty patientsList array
    {
      int expected = patientsListEmptySize;
      int actual = UrgentCareAdmissions.removeNextPatient(patientsListEmpty, patientsListEmptySize);
      if (expected != actual)
        return false;
    }

    // (5) test getPatientIndex using an empty patientsList array
    {
      int expected = -1;
      int actual =
          UrgentCareAdmissions.getPatientIndex(22002, patientsListEmpty, patientsListEmptySize);
      if (expected != actual)
        return false;
    }
    // (6) test getLongestWaitingPatientIndex using an empty patientsList array
    {
      int expected = -1;
      int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListEmpty,
          patientsListEmptySize);
      if (expected != actual)
        return false;
    }
    // and finally, verify that the arrays were not changed at all
    {
      int[][] patientsListEmptyCopy = new int[][] {null, null, null};
      if (!Arrays.deepEquals(patientsListEmpty, patientsListEmptyCopy))
        return false;

      int[][] patientsListFullCopy = new int[][] {{21801, 3, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 2, UrgentCareAdmissions.YELLOW},
          {11905, 8, UrgentCareAdmissions.GREEN}};
      if (!Arrays.deepEquals(patientsListFull, patientsListFullCopy))
        return false;
    }
    return true;
  }

  /**
   * Tests the getSummary method using arrays in various states. Each test should verify that the
   * returned index is as expected and that the array stays the same
   *
   * @return the boolean value of the test: if it is false, then the test failed, if it is true in
   * the end, then the test passed
   */
  public static boolean testGetSummary() {
    int[][] patientsListEmpty = new int[][] {null, null, null};
    int patientsListEmptySize = 0;

    int[][] patientsListSingle = new int[][]
        {{21801, 3, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 2, UrgentCareAdmissions.YELLOW}};
    int patientsListSingleSize = 3;

    int[][] patientsListAll = new int[][]
            {{21861, 2, UrgentCareAdmissions.RED}, {22092, 1, UrgentCareAdmissions.YELLOW},
            {11981, 3, UrgentCareAdmissions.GREEN}, {11331, 2, UrgentCareAdmissions.GREEN}};
    int patientsListAllSize = 4;

    // (1) test getSummary using an empty patientsList
    {
      String expected = """
          Total number of patients: 0
          RED: 0
          YELLOW: 0
          GREEN: 0
          """;
      String actual = UrgentCareAdmissions.getSummary(patientsListEmpty, patientsListEmptySize);
      if (!expected.equals(actual))
        return false;
    }
    // (2) test getSummary using a patientsList with multiple patients at a single triage level
    {
      String expected = """
          Total number of patients: 3
          RED: 0
          YELLOW: 3
          GREEN: 0
          """;
      String actual = UrgentCareAdmissions.getSummary(patientsListSingle, patientsListSingleSize);
      if (!expected.equals(actual))
        return false;
    }
    // (3) test getSummary using a patientsList with patients at all triage levels
    {
      String expected = """
          Total number of patients: 4
          RED: 1
          YELLOW: 1
          GREEN: 2
          """;
      String actual = UrgentCareAdmissions.getSummary(patientsListAll, patientsListAllSize);
      if (!expected.equals(actual))
        return false;
    }
    // and finally, verify that the arrays were not changed at all
    {
      int[][] patientsListEmptyCopy = new int[][] {null, null, null};
      if (!Arrays.deepEquals(patientsListEmpty, patientsListEmptyCopy))
        return false;

      int[][] patientsListSingleCopy = new int[][]
          {{21801, 3, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 2, UrgentCareAdmissions.YELLOW}};
      if (!Arrays.deepEquals(patientsListSingle, patientsListSingleCopy))
        return false;

      int[][] patientsListAllCopy = new int[][]
          {{21861, 2, UrgentCareAdmissions.RED}, {22092, 1, UrgentCareAdmissions.YELLOW},
          {11981, 3, UrgentCareAdmissions.GREEN}, {11331, 2, UrgentCareAdmissions.GREEN}};
      if (!Arrays.deepEquals(patientsListAll, patientsListAllCopy))
        return false;
    }
    return true;
  }

  /**
   * Runs each of the tester methods and displays their result
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("get index: " + testGetIndex());
    System.out.println("add patient: " + testAddPatient());
    System.out.println("remove patient: " + testRemovePatient());
    System.out.println("get patient: " + testGetPatientIndex());
    System.out.println("longest wait: " + testLongestWaitingPatient());
    System.out.println("empty/full: " + testEmptyAndFullArrays());
    System.out.println("get summary: " + testGetSummary());
  }
}
