/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package models;

import controls.TaskControl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TaskControlTest {

    /*
    TEST FUNCTIONALITY OF FOLLOWING REQUIREMENTS
        -A description shall be between 1 and 256 characters in length
        -A due date shall be a valid date within the Gregorian Calendar
        -A due date shall be displayed to users in the format: YYYY-MM-DD

        ** Editing of Description/Due Date handled by updating JavaFX component**
     */

    @Test
    void isDescriptionValid() {
        // verifies the description is between 1-456 characters
        // test with several cases that break / follow this restriction

        // 0 characters
        assertFalse(TaskControl.isDescriptionValid(""));

        // 457 characters
        assertFalse(TaskControl.isDescriptionValid("LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LONGERTHAN456LO"));
        assertTrue(TaskControl.isDescriptionValid(" "));
        assertTrue(TaskControl.isDescriptionValid("Hello"));
        assertTrue(TaskControl.isDescriptionValid("This is valid"));
        assertTrue(TaskControl.isDescriptionValid("This also appears to be a valid description"));
    }

    @Test
    void isDueDateValid() {
        // verifies the date is in the YYYY-MM-DD format and within the Gregorian calendar
        // test with several cases that break / follow this restriction

        assertFalse(TaskControl.isDueDateValid("1111-11-111"));
        assertFalse(TaskControl.isDueDateValid("2020-20-11")); // no 20th month
        assertFalse(TaskControl.isDueDateValid("Date-Da-Da")); // no letters valid
        assertFalse(TaskControl.isDueDateValid("Date-Da-Da"));

        assertFalse(TaskControl.isDueDateValid("2025-02-29")); // invalid leap year
        assertTrue(TaskControl.isDueDateValid("2024-02-29")); // valid leap year

        assertTrue(TaskControl.isDueDateValid("1111-11-11")); // old, but valid
        assertTrue(TaskControl.isDueDateValid("2020-02-02"));
    }
}