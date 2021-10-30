/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package models;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ToDoListControl extends VBox {

    private static final int LIST_CAPACITY = 100;
    private int size = 0;

    // header for where the list title is
    private ListHeaderControl listHeaderControl;
    // where we insert our TaskContainerControl
    private ScrollPane tasksScrollPane;

    // views we could show to the user
    // this allows us to just hide one then show the other without having to calculate anything
    private TaskContainerControl allTaskContainerControl;
    private TaskContainerControl selectedTaskContainerControl;
    private TaskContainerControl completedTaskContainerControl;
    private TaskContainerControl uncompletedTaskContainerControl;

    public ToDoListControl(String name) {
        // initialize based on the name
    }

    private void initializeGUI(String name) {
        // initialize and include all the GUI per mockup

        // set the header to the ListName

        // set up the pane per GUI mockup

        // add the controls

        // we show all the tasks as the default
    }

    private void initializeContainers() {
        // initialize the taskContainerControls for our list
    }

    public void addTask(String description, String dueDate) throws Exception {
        // check that our capacity isn't full
        // throws exception if full
        // throws exception if description or due date is incorrect
        // add the TaskControl to our taskContainerControl
        // set the button action for the task
    }

    private void setButtonAction(TaskControl taskControl) {
        // when we add a task, we will need to set the method on it for the click action
        // this will make us be able to do things like select the button in our list control from clicking the
        // object itself
    }

    public void removeTask(TaskControl taskControl) {
        // remove the task from toDoList
        // remove from taskContainerControl
    }

    public void selectTask(TaskControl taskControl) {
        // select the task from the todolist
    }

    public void removeSelected() {
        // get all selected tasks from todolist
        // remove all of those tasks from the list and taskContainer
    }

    public void toggleComplete(TaskControl taskControl) {
        // get the complete bool from the taskControl to figure out which list to remove from / add to
        // add / remove from lists accordingly
        // toggle complete for the taskControl
    }

    public void markTaskComplete(TaskControl taskControl) {
        // remove from incomplete
        // add to complete
    }

    public void markTaskIncomplete(TaskControl taskControl) {
        // remove from complete
        // mark as incomplete
    }

    public void viewAllTasks() {
        // display the allTaskContainerControl
    }

    public void viewCompletedTasks() {
        // display the completedTaskContainerControl
    }

    public void viewUncompletedTasks() {
        // display the uncompletedTaskContainerControl
    }

    public void viewTasksSortedByDate() {
        // sort all tasks by date then show that
    }

    public void openAllTasks() {
        // get all the tasks from list
        // open all
    }

    public void collapseAllTasks() {
        // get all the tasks from list
        // collapse all
    }

    public void editListTitle(String newTitle) {
        // change the title in the button, and in the toDoList
    }

    public void clearAllTasks() {
        // remove all the tasks
    }

    // METHODS FOR TESTING
    public TaskContainerControl getAllTaskContainerControl(){
        // return the container to verify content
        return null;
    }

    public TaskContainerControl getSelectedTaskContainerControl(){
        // return the container to verify content
        return null;
    }

    public TaskContainerControl getCompletedTaskContainerControl(){
        // return the container to verify content
        return null;
    }

    public TaskContainerControl getUncompletedTaskContainerControl(){
        // return the container to verify content
        return null;
    }
}
