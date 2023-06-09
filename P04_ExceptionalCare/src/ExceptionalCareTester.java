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

/**
 * This class is used to test the PatientRecord and ExceptionalCareAdmissions
 */
public class ExceptionalCareTester {
  /**
   * This test method is provided for you in its entirety, to give you a model for testing an
   * instantiable class. This method verifies the correctness of your PatientRecord class.
   *
   * In this test, we create two PatientRecords with different information and use the accessor
   * methods to verify that both contain the correct information and have the correct String
   * representation.
   *
   * @return true if and only if all scenarios pass, false otherwise
   * @author hobbes
   */
  public static boolean testPatientRecord() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // (1) create two PatientRecords with different, valid input
    // no exceptions should be thrown, so let's be safe:
    PatientRecord test1 = null, test2 = null;
    try {
      test1 = new PatientRecord('M', 17, PatientRecord.YELLOW);
      test2 = new PatientRecord('X', 21, PatientRecord.GREEN);
    } catch (Exception e) {
      return false;
    }

    // (2) verify their data fields:
    {
      // CASE_NUMBER
      int expected1 = 21701;
      int expected2 = 32102;
      if (test1.CASE_NUMBER != expected1 || test2.CASE_NUMBER != expected2)
        return false;
    }
    {
      // triage
      int expected1 = PatientRecord.YELLOW;
      int expected2 = PatientRecord.GREEN;
      if (test1.getTriage() != expected1 || test2.getTriage() != expected2)
        return false;
    }
    {
      // gender
      char expected1 = 'M';
      char expected2 = 'X';
      if (test1.getGender() != expected1 || test2.getGender() != expected2)
        return false;
    }
    {
      // age
      int expected1 = 17;
      int expected2 = 21;
      if (test1.getAge() != expected1 || test2.getAge() != expected2)
        return false;
    }
    {
      // orderOfArrival
      int expected1 = 1;
      int expected2 = 2;
      if (test1.getArrivalOrder() != expected1 || test2.getArrivalOrder() != expected2)
        return false;
    }
    {
      // hasBeenSeen - try the mutator too
      if (test1.hasBeenSeen() || test2.hasBeenSeen())
        return false;
      test1.seePatient();
      if (!test1.hasBeenSeen() || test2.hasBeenSeen())
        return false;
    }

    // (3) verify their string representations
    {
      String expected1 = "21701: 17M (YELLOW)";
      String expected2 = "32102: 21X (GREEN)";
      if (!test1.toString().equals(expected1) || !test2.toString().equals(expected2))
        return false;
    }

    // (4) finally, verify that the constructor throws an exception for an invalid triage value
    try {
      new PatientRecord('F', 4, -17);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalArgumentException e) {
      // correct exception type, but it should have a message:
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) {
      // incorrect exception type
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * In this test, we test the functions of constructors, and make sure the operations of exceptions
   * work as expected.
   *
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testAdmissionsConstructorValid() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // (1) verify that a normal, valid-input constructor call does NOT throw an exception
    ExceptionalCareAdmissions test1 = null;
    ExceptionalCareAdmissions test2 = null;
    try {
      test1 = new ExceptionalCareAdmissions(5);
      test2 = new ExceptionalCareAdmissions(10);
    } catch (Exception e) {
      // it should not throw an exception
      return false;
    }

    // (2) verify that a just-created object has size 0, is not full, has no seen patients, and
    // its toString() is an empty string
    if (test1.size() != 0 || test2.size() != 0) {
      return false;
    }
    if (test1.isFull() || test2.isFull()) {
      return false;
    }
    if (test1.getNumberSeenPatients() != 0 || test2.getNumberSeenPatients() != 0) {
      return false;
    }
    if (!test1.toString().equals("") || !test2.toString().equals("")) {
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * In this test, we test the functions of constructors with errors, and make sure the operations
   * of exceptions work as expected.
   *
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testAdmissionsConstructorError() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();
    // (1) verify that calling the constructor with capacity <= 0 causes an IllegalArgumentException
    ExceptionalCareAdmissions test = null;
    try {
      test = new ExceptionalCareAdmissions(0);
      // we should return false if it is not catched
      return false;
    } catch (IllegalArgumentException e) {
      // it should not throw an exception
      // correct exception type, but it should have a message:
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) {
      // catch the wrong exception type
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * In this test, we test the functions of addPatient() method, and make sure the operations of
   * adding patients in different locations work as expected.
   *
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testAddPatientValid() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // (1) add a new patient to an empty list - since you cannot use Arrays.deepEquals() here
    // anymore, verify the contents of the patientsList using ExceptionalCareAdmissions.toString()
    ExceptionalCareAdmissions test1 = new ExceptionalCareAdmissions(3);
    PatientRecord testRecord1 = new PatientRecord('M', 66, 1);
    test1.addPatient(testRecord1, 0);
    String expected1 = "26601: 66M (YELLOW)";
    if (!expected1.equals(test1.toString()))
      return false;

    // (2) add a new patient to the end of the list
    PatientRecord testRecord2 = new PatientRecord('F', 88, 1);
    test1.addPatient(testRecord2, test1.size());
    String expected2 = "26601: 66M (YELLOW)" + "\n" + "18802: 88F (YELLOW)";
    if (!expected2.equals(test1.toString()))
      return false;

    // (3) add a new patient to the beginning of the list
    PatientRecord testRecord3 = new PatientRecord('X', 18, 0);
    test1.addPatient(testRecord3, 0);
    String expected3 =
        "31803: 18X (RED)" + "\n" + "26601: 66M (YELLOW)" + "\n" + "18802: 88F (YELLOW)";
    if (!expected3.equals(test1.toString()))
      return false;

    //if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * This test method is provided for you in its entirety, to give you a model for verifying a
   * method which throws exceptions. This method tests addPatient() with two different, full
   * patientsList arrays; one contains seen patients and one does not.
   *
   * We assume for the purposes of this method that the ExceptionalCareAdmissions constructor and
   * PatientRecord constructor are working properly.
   *
   * This method must NOT allow ANY exceptions to be thrown from the tested method.
   *
   * @return true if and only if all scenarios pass, false otherwise
   * @author hobbes
   */
  public static boolean testAddPatientError() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ////// (1) a full Admissions object that contains no seen patients

    // create a small admissions object and fill it with patients. i'm filling the list
    // in decreasing order of triage, so the addPatient() method has to do the least
    // amount of work.
    ExceptionalCareAdmissions full = new ExceptionalCareAdmissions(3);
    full.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
    full.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);

    // saving one patient in a local variable so we can mark them "seen" later
    PatientRecord seenPatient = new PatientRecord('F', 20, PatientRecord.GREEN);
    full.addPatient(seenPatient, 2);

    try {
      full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception, but for this method we expect a specific
      // error message so we have one more step to verify:
      String message = e.getMessage();
      String expected = "Cannot admit new patients";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and we can just fail the test now
      return false;
    }

    ////// (2) a full Admissions object that contains at least one seen patient
    // since we have a reference to the patient at index 2, we'll just mark them seen here
    seenPatient.seePatient();

    try {
      full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "cleanPatientsList()";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and the test fails here
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * In this test, we test the functions of getAdmissionIndex, and make sure the operations of
   * getting index at different locations work as expected.
   *
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testGetIndexValid() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // create an Admissions object and add a few Records to it, leaving some space
    ExceptionalCareAdmissions test1 = new ExceptionalCareAdmissions(7);
    test1.addPatient(new PatientRecord('M', 18, PatientRecord.YELLOW), 0);
    test1.addPatient(new PatientRecord('M', 5, PatientRecord.GREEN), 1);

    // (1) get the index of a PatientRecord that should go at the END
    PatientRecord indexRecord1 = new PatientRecord('F', 20, PatientRecord.GREEN);
    int expected1 = test1.size();
    int actual1 = test1.getAdmissionIndex(indexRecord1);
    if (expected1 != actual1)
      return false;

    // (2) get the index of a PatientRecord that should go at the BEGINNING
    PatientRecord indexRecord2 = new PatientRecord('X', 22, PatientRecord.RED);
    int expected2 = 0;
    int actual2 = test1.getAdmissionIndex(indexRecord2);
    if (expected2 != actual2)
      return false;

    // (3) get the index of a PatientRecord that should go in the MIDDLE
    PatientRecord indexRecord3 = new PatientRecord('M', 32, PatientRecord.YELLOW);
    int expected3 = 1;
    int actual3 = test1.getAdmissionIndex(indexRecord3);
    if (expected3 != actual3)
      return false;

    //if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * In this test, we test the functions of getAdmissionIndex, and make sure the operations of
   * getting index with invalid numbers work as expected.
   *
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testGetIndexError() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // create an Admissions object and add Records to it until it is full, as in testAddPatientError
    ExceptionalCareAdmissions test1 = new ExceptionalCareAdmissions(4);
    PatientRecord seenRecord = new PatientRecord('M', 26, PatientRecord.GREEN);
    test1.addPatient(new PatientRecord('M', 88, PatientRecord.RED), 0);
    test1.addPatient(new PatientRecord('F', 35, PatientRecord.YELLOW), 1);
    test1.addPatient(new PatientRecord('X', 5, PatientRecord.GREEN), 2);
    test1.addPatient(seenRecord, 3);

    // (1) verify the exception when there are no patients who have been seen in the list
    try {
      test1.getAdmissionIndex(new PatientRecord('F', 20, PatientRecord.GREEN));
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "Cannot admit new patients";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and the test fails here
      return false;
    }

    // (2) verify the exception when there is at least one patient who has been seen
    seenRecord.seePatient();
    try {
      test1.getAdmissionIndex(new PatientRecord('M', 22, PatientRecord.GREEN));
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "cleanPatientsList()";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and the test fails here
      return false;
    }

    //if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * In this test, we test the functions of basic constructors, and make sure the different
   * operations work as expected.
   *
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testAdmissionsBasicAccessors() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // (1) verify isFull() on a non-full and a full Admissions object
    ExceptionalCareAdmissions full = new ExceptionalCareAdmissions(3);
    full.addPatient(new PatientRecord('M', 88, PatientRecord.RED), 0);
    full.addPatient(new PatientRecord('F', 35, PatientRecord.YELLOW), 1);
    full.addPatient(new PatientRecord('X', 5, PatientRecord.GREEN), 2);
    if (!full.isFull())
      return false;

    ExceptionalCareAdmissions notFull = new ExceptionalCareAdmissions(3);
    notFull.addPatient(new PatientRecord('M', 88, PatientRecord.RED), 0);
    notFull.addPatient(new PatientRecord('F', 35, PatientRecord.YELLOW), 1);
    if (notFull.isFull())
      return false;

    // (2) verify size() before and after adding a PatientRecord
    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(3);
    PatientRecord seenRecord = new PatientRecord('F', 23, PatientRecord.GREEN);
    int beforeAdd = test.size();
    test.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
    test.addPatient(seenRecord, 1);
    int afterAdd = test.size();
    int expectedBeforeAdd = 0;
    int expectedAfterAdd = 2;
    if (beforeAdd != expectedBeforeAdd || afterAdd != expectedAfterAdd)
      return false;

    // (3) verify getNumberSeenPatients() before and after seeing a patient
    int beforeGet = test.getNumberSeenPatients();
    seenRecord.seePatient();
    int afterGet = test.getNumberSeenPatients();
    int expectedBeforeGet = 0;
    int expectedAfterGet = 1;
    if (beforeGet != expectedBeforeGet || afterGet != expectedAfterGet)
      return false;

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * In this test, we test the functions of seePatient(), and make sure the operations of seeing a
   * patient and hasBeenSeen() method work as expected.
   *
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testSeePatientValid() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();
    // create an Admissions object and add a few Records to it, saving a shallow copy of
    // at least one of the PatientRecord references
    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(4);
    PatientRecord copy1 = new PatientRecord('M', 28, PatientRecord.RED);
    test.addPatient(copy1, 0);
    PatientRecord copy2 = new PatientRecord('F', 35, PatientRecord.YELLOW);
    test.addPatient(copy2, 1);

    // (1) call seePatient() on the caseID of your saved reference and verify that its
    // hasBeenSeen() accessor return value changes
    copy1.seePatient();
    if (!copy1.hasBeenSeen())
      return false;

    // (2) verify getNumberSeenPatients() before and after seeing a different patient
    int beforeSeen = test.getNumberSeenPatients();
    copy2.seePatient();
    int afterSeen = test.getNumberSeenPatients();
    int expectedBeforeSeen = 1;
    int expectedAfterSeen = 2;
    if (beforeSeen != expectedBeforeSeen || afterSeen != expectedAfterSeen)
      return false;

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * In this test, we test the functions of seePatient() with invalid values, and make sure the
   * errors are correct catched
   *
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testSeePatientError() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ExceptionalCareAdmissions test1 = new ExceptionalCareAdmissions(3);
    PatientRecord patient1 = new PatientRecord('M', 88, PatientRecord.RED);
    PatientRecord patient2 = new PatientRecord('F', 35, PatientRecord.YELLOW);
    PatientRecord patient3 = new PatientRecord('X', 5, PatientRecord.GREEN);
    test1.addPatient(patient1, 0);
    test1.addPatient(patient2, 1);
    test1.addPatient(patient3, 2);
    // (1) verify that seeing a caseID for a patient not in the list causes an IllegalArgumentException
    try {
      test1.seePatient(54321);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalArgumentException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "the caseID is not in the list";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and the test fails here
      return false;
    }

    // (2) verify that seesing a caseID in a empty list causes an IllegalEmptyException
    ExceptionalCareAdmissions test2 = new ExceptionalCareAdmissions(3);
    try {
      test2.seePatient(12221);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "the list is empty";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and the test fails here
      return false;
    }

    //if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * In this test, we test the functions of testGetSummary() is working properly by comparing the
   * actual summary and the expected summary
   *
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testGetSummary() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();
    // (1) choose one getSummary() test from P01; this method has not changed much
    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);
    PatientRecord seenPatient1 = new PatientRecord('M', 28, PatientRecord.RED);
    PatientRecord seenPatient2 = new PatientRecord('M', 28, PatientRecord.GREEN);
    test.addPatient(seenPatient1, 0);
    test.addPatient(new PatientRecord('F', 35, PatientRecord.YELLOW), 1);
    test.addPatient(new PatientRecord('X', 5, PatientRecord.GREEN), 2);
    test.addPatient(seenPatient2, 3);
    seenPatient1.seePatient();
    String actualSummary1 = test.getSummary();
    String expectedSummary1 = """
        Total number of patients: 4
        Total number seen: 1
        RED: 1
        YELLOW: 1
        GREEN: 2
        """;
    if (!actualSummary1.equals(expectedSummary1))
      return false;
    // test for see more patient
    seenPatient2.seePatient();
    String actualSummary2 = test.getSummary();
    String expectedSummary2 = """
        Total number of patients: 4
        Total number seen: 2
        RED: 1
        YELLOW: 1
        GREEN: 2
        """;
    if (!actualSummary2.equals(expectedSummary2))
      return false;

    //if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * In this test, we test the functions of cleanPatientList() is working properly, including how it
   * handle lists with or without seen patients
   *
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testCleanList() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // create an Admissions object and add a few Records to it
    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);
    PatientRecord record1 = new PatientRecord('M', 28, PatientRecord.RED);
    PatientRecord record2 = new PatientRecord('F', 35, PatientRecord.YELLOW);
    PatientRecord record3 = new PatientRecord('X', 5, PatientRecord.GREEN);
    PatientRecord record4 = new PatientRecord('M', 24, PatientRecord.GREEN);
    test.addPatient(record1, 0);
    test.addPatient(record2, 1);
    test.addPatient(record3, 2);
    test.addPatient(record4, 3);
    // (1) using ExceptionalCareAdmissions.toString(), verify that a patientsList with NO seen 
    // patients does not change after calling cleanPatientsList()
    String beforeClean1 = test.toString();
    File file = new File("text.txt");
    try {
      test.cleanPatientsList(file);
    } catch (Exception e) {
      // if we get here with exceptions, return false
      return false;
    }
    String afterClean1 = test.toString();
    if (!beforeClean1.equals(afterClean1)){
      return false;
    }

    // (2) call seePatient() for at least two of the records in your patientsList, and use toString()
    // to verify that they have been removed after calling cleanPatientsList()
    record1.seePatient();
    record4.seePatient();

    try {
      test.cleanPatientsList(file);
    } catch (Exception e) {
      // if we get here, no exception was thrown and the test fails
      return false;
    }
    String actualCleaned = test.toString();
    String expectedCleaned = "13502: 35F (YELLOW)" + "\n" + "30503: 5X (GREEN)";
    if (!actualCleaned.equals(expectedCleaned.trim()))
      return false;

    // NOTE: you do NOT need to verify file contents in this test method; please do so manually
    return true;
  }

  /**
   * Runs each of the tester methods and displays the result. Methods with two testers have their
   * output grouped for convenience; a failed test is displayed as "X" and a passed test is
   * displayed as "pass"
   *
   * @param args unused
   * @author hobbes
   */
  public static void main(String[] args) {
    System.out.println("PatientRecord: " + (testPatientRecord() ? "pass" : "X"));
    System.out.println(
        "Admissions Constructor: " + (testAdmissionsConstructorValid() ? "pass" : "X") + ", " + (
            testAdmissionsConstructorError() ?
                "pass" :
                "X"));
    System.out.println("Add Patient: " + (testAddPatientValid() ? "pass" : "X") + ", " + (
        testAddPatientError() ?
            "pass" :
            "X"));
    System.out.println("Get Admission Index: " + (testGetIndexValid() ?
        "pass" :
        "X") + ", " + (testGetIndexError() ? "pass" : "X"));
    System.out.println("Basic Accessors: " + (testAdmissionsBasicAccessors() ? "pass" : "X"));
    System.out.println("See Patient: " + (testSeePatientValid() ? "pass" : "X") + ", " + (
        testSeePatientError() ?
            "pass" :
            "X"));
    System.out.println("Get Summary: " + (testGetSummary() ? "pass" : "X"));
    System.out.println("Clean List: " + (testCleanList() ? "pass" : "X"));
  }
}
