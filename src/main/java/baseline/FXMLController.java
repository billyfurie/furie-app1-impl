/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package baseline;

import exceptions.DateInvalidException;
import exceptions.DescriptionInvalidException;
import exceptions.ListFullException;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import controls.ToDoListControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FXMLController implements Initializable {

    // these are already made in scene
    @FXML private BorderPane mainPane;

    @FXML private VBox viewPane;

    @FXML private Label alertLabel;

    // task creator panel, where user creates the task
    // used for collecting the data once they click create task
    @FXML private TextField taskCreatorDueDate;
    @FXML private TextField taskCreatorDescription;


    // Left pane with list of ToDoLists
    // includes ToDoListContainer
    private ToDoListControl currentListControl; // current list we are viewing

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // start off by showing user a blank screen where they can begin to add tasks to a new list
        initializeTaskEditorFields();
        setActiveList(new ToDoListControl(alertLabel));
        initializeMainPane();
    }

    public void initializeMainPane() {
        // this is so that when the user clicks anywhere, it will de-focus from the text fields
        mainPane.setOnMouseClicked(event -> mainPane.requestFocus());
    }

    // for when user presses enter when creating task
    public void initializeTaskEditorFields() {
        taskCreatorDueDate.setOnAction(event -> addTask());
        taskCreatorDescription.setOnAction(event -> addTask());
    }

    public void viewAllTasks() {
        // show all tasks in the list
        currentListControl.viewAllTasks();
    }

    public void viewCompletedTasks() {
        // show only the completed tasks
        currentListControl.viewCompletedTasks();
    }

    public void viewUncompletedTasks() {
        // show only the uncompleted tasks
        currentListControl.viewUncompletedTasks();
    }

    public void sortTasksByDateAscending() {
        // sort the tasks by date, closest to farthest
        currentListControl.viewTasksSortedByDate(true);
    }

    public void sortTasksByDateDescending() {
        // sort the tasks by date, closest to farthest
        currentListControl.viewTasksSortedByDate(false);
    }

    private void setActiveList(ToDoListControl listControl) {
        currentListControl = listControl;
        // remove the former list, if any
        viewPane.getChildren().clear();
        // update the view with our current list
        viewPane.getChildren().add(currentListControl);
    }

    public void addTask() {
        // get the task details from the task creator panel
        String description = taskCreatorDescription.getText();
        String dueDate = taskCreatorDueDate.getText();
        taskCreatorDescription.clear();
        taskCreatorDueDate.clear();
        taskCreatorDescription.requestFocus();

        // handles exceptions, show alert

        // create a task with those details in the current list
        // create task based on description, due date if applicable
        try {
            // user didn't specify a due date
            currentListControl.addTask(description, dueDate);
        } catch (DateInvalidException d) {
            displayAlertMessage("Date is not in the proper format (YYYY-MM-DD).");
            // prompt for a different date
        } catch (DescriptionInvalidException e) {
            displayAlertMessage("Description is not in the proper format (1 - 456 Characters).");
            // prompt for a different Description
        } catch (ListFullException f) {
            // list full
            displayAlertMessage("Your list is full (100 tasks).");
        }
    }

    // remove an individual task is controlled by ToDoListControl due to the nature
    public void removeSelectedTasks() {
        // remove the tasks that have been selected from currentListControl
        currentListControl.removeSelected();
    }

    public void removeAllTasks() {
        // remove all the tasks and make this list a fresh new one
        currentListControl.clearAllTasks();
    }

    public void markSelectedTasksComplete() {
        // mark the selected tasks as complete
        currentListControl.markSelectedTasksComplete();
    }

    public void markSelectedTasksIncomplete() {
        // mark the selected tasks as incomplete
        currentListControl.markSelectedTasksIncomplete();
    }

    public void openAllTasks() {
        // open all the tasks views
        currentListControl.openAllTasks();
    }

    public void collapseAllTasks() {
        // collapse all the tasks views
        currentListControl.collapseAllTasks();
    }

    public void selectAllTasks() {
        // select all the tasks that are not selected
        currentListControl.selectAllTasks();
    }

    public void deselectAllTasks() {
        // deselect all the tasks that are selected
        currentListControl.deselectAllTasks();
    }

    // create a new ToDoList
    public void createNewToDoList() {
        // create and add a new ToDoList to the list container
        // pass the label since this will always be there, it's our universal user feedback system
        setActiveList(new ToDoListControl(alertLabel));
    }

    // display an alert message at the bottom of the screen
    public void displayAlertMessage(String message) {
        alertLabel.setText(message);

        // displays the message for 3 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> alertLabel.setText(null));
        pause.play();
    }

    // save a todolist
    public void saveToDoList() {
        // save the current list
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        FileManager manager = new FileManager();

        // txt file
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt"));

        File selected = fileChooser.showSaveDialog(null);

        // handles exceptions
        if (selected != null) {
            try {
                manager.saveListToFile(currentListControl, selected);
            } catch (FileNotFoundException e) {
                // alert that file is not found
                displayAlertMessage("File not found.");
            } catch (IOException e) {
                // alert that file is not proper
                displayAlertMessage("File is not in proper format.");
            }
        }
    }

    // load a todolist
    public void loadToDoList() {
        // set current list to the one we load
        FileChooser fileChooser = new FileChooser();

        // txt file
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        File selected = fileChooser.showOpenDialog(null);
        FileManager manager = new FileManager();

        // handles exceptions
        if (selected != null) {
            try {
                setActiveList(manager.loadListFromFile(selected, alertLabel));
            } catch (FileNotFoundException e) {
                // alert that file is not found
                displayAlertMessage("File not found.");
            } catch (IOException e) {
                // alert that file is not proper
                displayAlertMessage("File is not in proper format.");
            }
        } else {
            displayAlertMessage("Error from FileChooser. Please try again.");
        }
    }
}

