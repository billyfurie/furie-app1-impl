/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    /*
    TEST FUNCTIONALITY OF FOLLOWING REQUIREMENTS
        -A user shall be able to add a new item to the list
        -A user shall be able to remove an item from the list
        -A user shall be able to clear the list of all items
        -A user shall be able to mark an item in the list as either complete or incomplete
        -A user shall be able to display all existing items in the list
        -A user shall be able to display only the incomplete items in the list
        -A user shall be able to display only the completed items in the list

        Additionally
        -Tests whether a user can select / deselect a task
     */

    @Test
    void addTask() {
        ToDoList toDoList = new ToDoList();
        Task taskToAdd = new Task("Test", "2021-11-01", false, false);

        // check the task is not in there already
        ArrayList<Task> tasks = (ArrayList<Task>) toDoList.getTasks();

        assertEquals(0, tasks.size());

        // add the task
        toDoList.addTask(taskToAdd);

        // check the task has been added
        Task fromList = tasks.get(0);

        assertEquals(taskToAdd, fromList);
    }

    @Test
    void removeTask() {
        ToDoList toDoList = new ToDoList();
        Task taskToRemove = new Task("Test", "2021-11-01", false, false);

        // add the task
        toDoList.addTask(taskToRemove);

        // remove the task
        toDoList.removeTask(taskToRemove);

        // check the task has been removed
        assertFalse(toDoList.containsTask(taskToRemove));
    }

    @Test
    void clearAllTasks() {
        ToDoList toDoList = new ToDoList();

        // create tasks
        Task task1 = new Task("Test1", "2021-11-01", false, false);
        Task task2 = new Task("Test2", "2019-01-21", true, false);
        Task task3 = new Task("Test3", "2001-11-05", false, true);
        Task task4 = new Task("Test4", "1999-04-07", true, true);

        // add tasks
        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        toDoList.addTask(task4);

        // clear all the tasks
        toDoList.clearAllTasks();

        // check that the list is empty
        assertTrue(toDoList.getTasks().isEmpty());
    }

    @Test
    void markTaskComplete() {
        ToDoList toDoList = new ToDoList();

        // create task, incomplete
        Task task = new Task("Test1", "2021-11-01", false, false);

        // make sure task starts as not complete
        assertFalse(task.isComplete());

        toDoList.addTask(task);
        // mark task as complete
        toDoList.markTaskComplete(task);

        // make sure task is now marked complete
        assertTrue(task.isComplete());
    }

    @Test
    void markTaskIncomplete() {
        ToDoList toDoList = new ToDoList();

        // create task, complete
        Task task = new Task("Test1", "2021-11-01", true, false);

        // make sure task starts as complete
        assertTrue(task.isComplete());

        toDoList.addTask(task);
        // mark task as complete
        toDoList.markTaskIncomplete(task);

        // make sure task is now marked complete
        assertFalse(task.isComplete());
    }

    @Test
    void selectTask() {
        ToDoList toDoList = new ToDoList();

        // create task
        Task task = new Task("Test1", "2021-11-01", false, false);

        // make sure task starts as not selected
        assertFalse(task.isSelected());

        toDoList.addTask(task);
        // select task
        toDoList.selectTask(task);

        // make sure task is now selected
        assertTrue(task.isSelected());
    }

    @Test
    void deselectTask() {
        ToDoList toDoList = new ToDoList();

        // create task
        Task task = new Task("Test1", "2021-11-01", false, true);

        // make sure task starts as selected
        assertTrue(task.isSelected());

        toDoList.addTask(task);
        // deselect task
        toDoList.deselectTask(task);

        // make sure task is now deselected
        assertFalse(task.isSelected());
    }


    @Test
    void getCompletedTasks() {
        ToDoList toDoList = new ToDoList();

        // create tasks
        Task task1 = new Task("Test1", "2021-11-01", false, false);
        Task task2 = new Task("Test2", "2019-01-21", true, false);
        Task task3 = new Task("Test3", "2001-11-05", false, true);
        Task task4 = new Task("Test4", "1999-04-07", true, true);

        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        toDoList.addTask(task4);

        // get our completed tasks from the ToDoList
        ArrayList<Task> completed = (ArrayList<Task>) toDoList.getCompletedTasks();

        // We expect task2 and task4 to be the objects of completed
        assertTrue(completed.contains(task2));
        assertTrue(completed.contains(task4));

        // We expect task1 and task3 to not be a part of completed since they are not complete
        assertFalse(completed.contains(task1));
        assertFalse(completed.contains(task3));
    }

    @Test
    void getUncompletedTasks() {
        ToDoList toDoList = new ToDoList();

        // create tasks
        Task task1 = new Task("Test1", "2021-11-01", false, false);
        Task task2 = new Task("Test2", "2019-01-21", true, false);
        Task task3 = new Task("Test3", "2001-11-05", false, true);
        Task task4 = new Task("Test4", "1999-04-07", true, true);

        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        toDoList.addTask(task4);

        // get our completed tasks from the ToDoList
        ArrayList<Task> uncompleted = (ArrayList<Task>) toDoList.getUncompletedTasks();

        // We expect task2 and task4 to not be a part of uncompleted since they are complete
        assertFalse(uncompleted.contains(task2));
        assertFalse(uncompleted.contains(task4));

        // We expect task1 and task3 to be a part of uncompleted since they are not complete
        assertTrue(uncompleted.contains(task1));
        assertTrue(uncompleted.contains(task3));
    }

    @Test
    void getTasks() {
        ToDoList toDoList = new ToDoList();

        // create tasks
        Task task1 = new Task("Test1", "2021-11-01", false, false);
        Task task2 = new Task("Test2", "2019-01-21", true, false);
        Task task3 = new Task("Test3", "2001-11-05", false, true);
        Task task4 = new Task("Test4", "1999-04-07", true, true);

        toDoList.addTask(task1);
        toDoList.addTask(task2);
        toDoList.addTask(task3);
        toDoList.addTask(task4);

        // get all of our tasks
        ArrayList<Task> tasks = (ArrayList<Task>) toDoList.getTasks();

        // We expect all tasks to be a part of the list
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
        assertTrue(tasks.contains(task3));
        assertTrue(tasks.contains(task4));
    }
}