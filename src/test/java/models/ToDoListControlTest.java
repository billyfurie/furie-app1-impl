/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListControlTest {

    @Test
    void addTask() {
        // add tasks we create to a list
        // verify the list has these tasks
    }

    @Test
    void removeTask() {
        // remove tasks we added from a list
        // verify the removal worked
    }

    @Test
    void removeSelected() {
        // select tasks
        // call remove selected
        // verify all the select tasks we removed were actually removed
    }

    @Test
    void toggleComplete() {
        // toggle complete a task
        // verify it was toggled
    }

    @Test
    void markTaskComplete() {
        // mark task complete
        // verify it was added to the complete list
        // verify it was removed from the incomplete list
    }

    @Test
    void markTaskIncomplete() {
        // mark task incomplete
        // verify it was added to the incomplete list
        // verify it was removed from the complete list
    }

    @Test
    void clearAllTasks() {
        // verify all tasks were removed from a list after this is called on the list
    }

    @Test
    void getAllTaskContainerControl() {
        // verify that the AllTaskContainerControl works properly, per requirement user shall be able to see all tasks
    }

    @Test
    void getCompletedTaskContainerControl() {
        // verify that the AllTaskContainerControl works properly, per requirement user shall be able to see all complete tasks
    }

    @Test
    void getUncompletedTaskContainerControl() {
        // verify that the AllTaskContainerControl works properly, per requirement user shall be able to see all incomplete tasks
    }
}