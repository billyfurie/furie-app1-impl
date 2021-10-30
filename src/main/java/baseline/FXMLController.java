/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package baseline;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import models.ToDoListControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class FXMLController implements Initializable {

    // these are already made in scene

    @FXML
    private VBox viewPane;

    @FXML
    private Button createTaskButton;

    // task creator panel, where user creates the task
    // used for collecting the data once they click create task
    @FXML private TextField taskCreatorTaskName;
    @FXML private TextField taskCreatorDueDate;
    @FXML private TextField taskCreatorDescription;


    // Left pane with list of ToDoLists
    // includes ToDoListContainer
    private ToDoListControl currentListControl; // current list we are viewing

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // start off by showing user a blank screen where they can begin to add tasks to a new list
    }

    public void viewAllTasks() {
        // show all tasks in the list
    }

    public void viewCompletedTasks() {
        // show only the completed tasks
    }

    public void viewUncompletedTasks() {
        // show only the uncompleted tasks
    }

    public void sortTasksByDate() {
        // sort the tasks by date, closest to farthest
    }

    private void setActiveList() {
        // remove the former list, if any
        // update the view with our current list
    }

    public void addTask() {
        // get the task details from the task creator panel
        // handles exceptions, show alert
        // create a task with those details in the current list
        // create task based on title, description, due date
    }

    // remove an individual task is controlled by ToDoListControl due to the nature
    public void removeSelectedTasks() {
        // remove the tasks that have been selected from currentListControl
    }

    public void removeAllTasks() {
        // remove all the tasks and make this list a fresh new one
    }

    public void openAllTasks() {
        // open all the tasks views
    }

    public void collapseAllTasks() {
        // collapse all the tasks views
    }

    // create a new ToDoList
    public void addToDoList() {
        // get text from field
        // create and add a new ToDoList to the list container
    }

    // save a todolist
    public void saveToDoList() {
        // save the current list
        // handles exceptions
    }

    // load a todolist
    public void loadToDoList() {
        // set current list to the one we load
        // handles exceptions
    }
}

