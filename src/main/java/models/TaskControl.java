/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package models;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.security.InvalidParameterException;

public class TaskControl extends StackPane {

    private static final int DESCRIPTION_LIMIT = 256;

    private TitledPane taskTitledPane;
    private HBox optionsPane;
    private Button toggleCompleteButton;
    private Button editDescriptionButton;
    private Button editDueDateButton;
    private Button deleteTaskButton;
    private TextField textEditorField;
    private Label dueDateLabel;
    private boolean complete = false;

    public TaskControl(String description, String dueDate) throws InvalidParameterException {
        // initialize the Task object based on these parameters
        // initialize the GUI objects with design properties from mockup
    }

    public TaskControl(String description) throws InvalidParameterException {
        // initialize the Task object based on these parameters
        // initialize the GUI objects with design properties from mockup
        // no due date entered
    }

    // based on the titled pane we designed in mockup
    private void initializeGUI(String description, String dueDate) {
        // initialize all the GUI components
    }

    private void initializeTitledPane(String description) {
        // create the titled pane per our GUI mockup

        // set the name of the pane to the title

        // add the style class
    }

    private void initializeDueDateLabel(String dueDate) {
        // add the label
        // set the text
        // set the style class
    }

    private void enterTaskEditorModeDescription() {
        // the user clicked the edit description button
        // set the text field to prompt for description
        // be ready to update the description
    }

    private void enterTaskEditorModeDate() {
        // the user clicked the edit due date button
        // set the text field to prompt for due date
        // be ready to update the date
    }

    private void exitTaskEditorMode() {
        // this happens when the user enters the new description / due date
        // hide the text field now
    }

    private void showTextEditorField() {
        // show the text editor field to the user
    }

    public void setCollapsed(boolean collapsed) {
        // set whether this task is collapsed
    }

    public void editDescription(String newDescription) throws InvalidParameterException {
        // update the description text
        // check for constraints on description
        // throws exception if invalid
    }

    public void editDueDate(String newDueDate) throws InvalidParameterException {
        // update the due date text
        // check that date is in proper format
        // throws exception if invalid
    }

    // this will allow us to set the OnMouseClicked function from the ToDoListControl
    public TitledPane getTaskTitledPane() {
        return null;
    }

    // this will allow us to set the OnMouseClicked function, so we can delete from that list!
    public Button getDeleteTaskButton() {
        return null;
    }

    // this will allow us to set the OnMouseClicked function, so we can mark complete/incomplete from that list!
    public Button getToggleCompleteButton() {
        return null;
    }

    public boolean isDescriptionValid(String description) {
        // return whether it is valid per our requirements
        return false;
    }

    public boolean isDueDateValid(String dueDate) {
        // return whether it is valid per our requirements
        return false;
    }

    public String getDate() {
        // if there's a date, get it from the dateLabel

        return null;
    }

    public void toggleComplete() {
        // toggle the complete bool
    }

    public boolean getComplete() {
        // return complete bool
        return false;
    }

    public String getDescription() {
        // return the description text
        return null;
    }

    public String getDueDate() {
        // return the due date text
        return null;
    }
}
