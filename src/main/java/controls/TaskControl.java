/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package controls;

import exceptions.DateInvalidException;
import exceptions.DescriptionInvalidException;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import models.Task;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskControl extends StackPane {

    private static final int DESCRIPTION_LIMIT = 256;
    private static final String BUTTON_TEXT_COMPLETE = "Mark Complete";
    private static final String BUTTON_TEXT_INCOMPLETE = "Mark Incomplete";
    private static final String TEXT_EDITOR_PROMPT_DATE = "Enter a new due date";
    private static final String TEXT_EDITOR_PROMPT_DESCRIPTION = "Enter a new description";
    private static final String TITLED_PANE_STYLE_CLASS = "TitledPane";
    private static final String SYSTEM_FONT = "System";

    private TitledPane taskTitledPane;
    private HBox optionsPane;
    private Button toggleCompleteButton;
    private Button editDescriptionButton;
    private Button editDueDateButton;
    private Button deleteTaskButton;
    private TextField textEditorField;
    private Label dueDateLabel;

    private boolean complete = false;
    private boolean selected = false;

    private final Task task;
    private final Label alertLabel;

    public TaskControl(String description, String dueDate, Label alertLabel) throws InvalidParameterException {
        // initialize the Task object based on these parameters

        // Check that everything is valid
        if (!isDescriptionValid(description)) {
            throw new DescriptionInvalidException();
        } else if (!isDueDateValid(dueDate)) {
            throw new DateInvalidException();
        }
        // initialize the GUI objects with design properties from mockup
        initializeGUI(description, dueDate);
        this.alertLabel = alertLabel;
        // our model
        task = new Task(description, dueDate, false, false);
    }

    // based on the titled pane we designed in mockup
    private void initializeGUI(String description, String dueDate) {
        // initialize all the GUI components
        initializeTitledPane(description);

        initializeDueDateLabel(dueDate);

        initializeOptionsPane();

        initializeTextEditorField();

        // initialize the buttons that are controlled by TaskControl

        // set the edit due date
        editDueDateButton.setOnAction(event -> enterTaskEditorModeDate());

        // set the edit description
        editDescriptionButton.setOnAction(event -> enterTaskEditorModeDescription());
    }

    private void initializeTitledPane(String description) {
        // create the titled pane per our GUI mockup
        taskTitledPane = new TitledPane();

        // set the name of the pane to the title
        taskTitledPane.setText(description);
        taskTitledPane.setTextFill(Color.WHITE);

        taskTitledPane.setExpanded(false);
        taskTitledPane.setAnimated(false);
        taskTitledPane.setMaxHeight(Double.MAX_VALUE);

        // add the style class
        taskTitledPane.getStyleClass().add(TITLED_PANE_STYLE_CLASS);
        taskTitledPane.setFont(Font.font(SYSTEM_FONT, FontWeight.BOLD, 18));
        taskTitledPane.setTextFill(Color.color(0,0,0));
        getChildren().add(taskTitledPane);
    }

    private void initializeDueDateLabel(String dueDate) {
        // add the label
        dueDateLabel = new Label();

        // set the text
        dueDateLabel.setText(dueDate);

        // set the style class
        dueDateLabel.getStyleClass().add("TitleDueDate");
        dueDateLabel.setFont(Font.font(SYSTEM_FONT, FontWeight.BOLD, 20));
        setAlignment(Pos.TOP_RIGHT);
        setMargin(dueDateLabel, new Insets(10, 16, 0, 0));

        getChildren().add(dueDateLabel);
    }

    private void initializeOptionsPane() {
        // create the pane
        optionsPane = new HBox();

        // set some style for the pane itself
        optionsPane.setPadding(new Insets(1, 8, 0, 8));
        optionsPane.setMinHeight(USE_PREF_SIZE);
        optionsPane.setPrefWidth(200);
        optionsPane.setPrefHeight(48);
        optionsPane.setMaxHeight(getPrefHeight());
        optionsPane.setAlignment(Pos.CENTER_LEFT);
        optionsPane.getStyleClass().add("TaskOptionsPane");

        // Create the buttons with our style per GUI mockup
        toggleCompleteButton = new TaskOptionsButtonControl(BUTTON_TEXT_COMPLETE);
        editDescriptionButton = new TaskOptionsButtonControl("Edit Description");
        editDueDateButton = new TaskOptionsButtonControl("Edit Due Date");
        deleteTaskButton = new TaskOptionsButtonControl("Delete Task");


        // add all the buttons to the option pane
        optionsPane.getChildren().addAll(toggleCompleteButton, editDescriptionButton, editDueDateButton, deleteTaskButton);

        // set the content to our options pane
        taskTitledPane.setContent(optionsPane);
    }

    private void initializeTextEditorField() {
        // create the field
        textEditorField = new TextField();

        // change the design per our GUI mockup
        textEditorField.setFont(Font.font(SYSTEM_FONT, 14));
        textEditorField.getStyleClass().add("TaskOptionsTextBox");
        textEditorField.setMinWidth(400);
        textEditorField.setMaxWidth(Double.MAX_VALUE);
        textEditorField.setMaxHeight(Double.MAX_VALUE);
        HBox.setMargin(textEditorField, new Insets(0, 0, 6, 0));

        optionsPane.getChildren().add(textEditorField);

        // initialize to not show
        setNodeVisible(textEditorField, false);
    }

    private void enterTaskEditorModeDescription() {
        // the user clicked the edit description button
        showTextEditorField();

        // set the text field to prompt for description
        textEditorField.setPromptText(TEXT_EDITOR_PROMPT_DESCRIPTION);

        // be ready to update the description
        // action for this is pressing enter
        textEditorField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // send data
                try {
                    editDescription(textEditorField.getText());
                    textEditorField.clear();
                } catch (DescriptionInvalidException e) {
                    displayAlertMessage("New description is not in the proper format (1 - 456 Characters).");
                }

                exitTaskEditorMode();
            }
        });
    }

    private void enterTaskEditorModeDate() {
        // the user clicked the edit due date button
        showTextEditorField();

        // set the text field to prompt for due date
        textEditorField.setPromptText(TEXT_EDITOR_PROMPT_DATE);

        // be ready to update the date
        // action for this is pressing enter
        textEditorField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // send data
                try {
                    editDueDate(textEditorField.getText());
                    textEditorField.clear();
                } catch (DateInvalidException e) {
                    displayAlertMessage("New date is not in the proper format (YYYY-MM-DD).");
                }

                exitTaskEditorMode();
            }
        });
    }

    private void exitTaskEditorMode() {
        // this happens when the user enters the new description / due date
        // hide the text field now
        setNodeVisible(textEditorField, false);

        // show the buttons again
        setNodeVisible(toggleCompleteButton, true);
        setNodeVisible(editDescriptionButton, true);
        setNodeVisible(editDueDateButton, true);
        setNodeVisible(deleteTaskButton, true);
    }

    private void showTextEditorField() {
        // hide the buttons to make room for text editor
        setNodeVisible(toggleCompleteButton, false);
        setNodeVisible(editDescriptionButton, false);
        setNodeVisible(editDueDateButton, false);
        setNodeVisible(deleteTaskButton, false);

        // show the text editor field to the user
        setNodeVisible(textEditorField, true);
    }

    private void setNodeVisible(Node node, boolean visible) {
        node.setVisible(visible);
        node.setManaged(visible);
    }

    public void setCollapsed(boolean collapsed) {
        // set whether this task is collapsed
        taskTitledPane.setExpanded(!collapsed);
    }

    public void editDescription(String newDescription) throws DescriptionInvalidException {
        // check that description is in proper format

        if (isDescriptionValid(newDescription)) {
            // update the description text
            taskTitledPane.setText(newDescription);

            // update model
            task.setDescription(newDescription);
        } else {
            // throws exception if invalid
            throw new DescriptionInvalidException();
        }
    }

    public void editDueDate(String newDueDate) throws DateInvalidException {
        // check that date is in proper format

        if (isDueDateValid(newDueDate)) {
            // update the due date text
            dueDateLabel.setText(newDueDate);

            // update model
            task.setDueDate(newDueDate);
        } else {
            // throws exception if invalid
            throw new DateInvalidException();
        }
    }

    // display an alert message at the bottom of the screen
    private void displayAlertMessage(String message) {
        alertLabel.setText(message);

        // displays the message for 3 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> alertLabel.setText(null));
        pause.play();
    }

    // this will allow us to set the OnMouseClicked function from the ToDoListControl
    public TitledPane getTaskTitledPane() {
        return taskTitledPane;
    }

    // this will allow us to set the OnMouseClicked function, so we can delete from that list!
    public Button getDeleteTaskButton() {
        return deleteTaskButton;
    }

    // this will allow us to set the OnMouseClicked function, so we can mark complete/incomplete from that list!
    public Button getToggleCompleteButton() {
        return toggleCompleteButton;
    }

    public static boolean isDescriptionValid(String description) {
        // return whether it is valid per our requirements
        return description.length() >= 1 && description.length() <= DESCRIPTION_LIMIT;
    }

    public static boolean isDueDateValid(String dueDate) {
        // No date entered is valid
        if (dueDate.equals("")) {
            return true;
        }

        // use SimpleDateFormat to check if the date is valid to our pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        // try to parse for date
        try {
            dateFormat.parse(dueDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    // toggle whether a task is complete
    public void toggleComplete() {
        // toggle the complete bool
        complete = !complete;
        // update tasks look
        // update the button

        if (!complete) {
            taskTitledPane.getStyleClass().add(TITLED_PANE_STYLE_CLASS);
            taskTitledPane.getStyleClass().remove("TitledPaneComplete");
            toggleCompleteButton.setText(BUTTON_TEXT_COMPLETE);
        } else {
            taskTitledPane.getStyleClass().add("TitledPaneComplete");
            taskTitledPane.getStyleClass().remove(TITLED_PANE_STYLE_CLASS);
            toggleCompleteButton.setText(BUTTON_TEXT_INCOMPLETE);
        }
    }

    // toggle whether a task is selected
    public void toggleSelect() {
        // toggle the selected bool
        selected = !selected;
        // update tasks look
        // update the button

        if (!selected) {
            taskTitledPane.getStyleClass().add(TITLED_PANE_STYLE_CLASS);
            taskTitledPane.getStyleClass().remove("TitledPaneSelected");
        } else {
            taskTitledPane.getStyleClass().add("TitledPaneSelected");
            taskTitledPane.getStyleClass().remove(TITLED_PANE_STYLE_CLASS);
        }
    }

    public boolean getComplete() {
        // return complete bool
        return complete;
    }

    public boolean getSelected() {
        // return complete bool
        return selected;
    }

    public String getDueDate() {
        // return the due date text
        return dueDateLabel.getText();
    }

    public String getDescription() {
        // return the description
        return taskTitledPane.getText();
    }

    public Task getTask() {
        // return model
        return task;
    }
}
