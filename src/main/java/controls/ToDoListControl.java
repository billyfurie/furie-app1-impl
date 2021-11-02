/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package controls;

import exceptions.ListFullException;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import models.Task;
import models.ToDoList;

import java.security.InvalidParameterException;
import java.util.*;

public class ToDoListControl extends VBox {

    private static final int LIST_CAPACITY = 100;
    private int size = 0;

    // header for where the list title is
    private ListHeaderControl listHeaderControl;
    // where we insert our TaskContainerControl
    private ScrollPane tasksScrollPane;
    private final Label alertLabel;
    private final ToDoList toDoList;

    // views we could show to the user
    // this allows us to just hide one then show the other without having to calculate anything
    private TaskContainerControl allTaskContainerControl;

    // needs reference to the alert label
    public ToDoListControl(Label alertLabel) {
        // initialize based on the name
        initializeContainer();
        initializeGUI();
        toDoList = new ToDoList();
        // so editing tasks can give user feedback
        this.alertLabel = alertLabel;
    }

    private void initializeGUI() {
        // initialize and include all the GUI per mockup

        // set the header to the ListName
        listHeaderControl = new ListHeaderControl("All Tasks"); // start off always showing all the tasks

        // set up the scroll pane per GUI mockup
        tasksScrollPane = new ScrollPane();
        tasksScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        tasksScrollPane.setFitToHeight(true);
        tasksScrollPane.setFitToWidth(true);
        tasksScrollPane.setMaxWidth(Double.MAX_VALUE);
        tasksScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // add the controls
        getChildren().add(listHeaderControl);
        getChildren().add(tasksScrollPane);

        // we show all the tasks as the default
        tasksScrollPane.setContent(allTaskContainerControl);
    }

    private void initializeContainer() {
        // initialize the taskContainerControls for our list
        allTaskContainerControl = new TaskContainerControl();
    }

    public void addTask(String description, String dueDate) throws InvalidParameterException {
        // check that our capacity isn't full
        if (LIST_CAPACITY - size <= 0) {
            // throws exception if full
            throw new ListFullException();
        }

        // throws exception if description or due date is incorrect

        // check that user entered a date
        // add the TaskControl to our taskContainerControls
        TaskControl control = new TaskControl(description, dueDate, alertLabel);

        // add to our model
        toDoList.addTask(new Task(description, dueDate, false, false));

        // set the button action for the task ***
        // Set delete task button to remove the task from here
        setButtonActionDelete(control);
        setButtonActionToggleComplete(control);
        setActionSelectTask(control);

        allTaskContainerControl.addTaskControl(control);

        // we added another item
        size++;
    }

    public void addTaskControl(TaskControl taskControl) {
        // this is useful for our deserialization of task controls!
        // use regular setup
        setButtonActionDelete(taskControl);
        setButtonActionToggleComplete(taskControl);
        setActionSelectTask(taskControl);

        allTaskContainerControl.addTaskControl(taskControl);

        // we added another item
        size++;
    }

    private void setButtonActionDelete(TaskControl taskControl) {
        // when we add a task, we will need to set the method on it for the click action
        // this will make us be able to do things like select the button in our list control from clicking the
        // object itself

        taskControl.getDeleteTaskButton().setOnAction(event -> removeTask(taskControl));
    }

    private void setButtonActionToggleComplete(TaskControl taskControl) {
        // when we add a task, we will need to set the method on it for the click action
        // this will make us be able to do things like select the button in our list control from clicking the
        // object itself

        taskControl.getToggleCompleteButton().setOnAction(event -> toggleComplete(taskControl));
    }

    private void setActionSelectTask(TaskControl taskControl) {
        // allows user to right-click select tasks!

        taskControl.getTaskTitledPane().setOnMouseClicked(event -> {

            // if user right-clicks on the task, it will select it
            if (event.getButton() == MouseButton.SECONDARY) {
                selectTask(taskControl);
            }
        });
    }

    public void removeTask(TaskControl taskControl) {
        // remove from taskContainerControls

        allTaskContainerControl.removeTaskControl(taskControl);

        // remove from our model
        toDoList.removeTask(taskControl.getTask());

        // decrement size of list
        size--;
    }

    public void selectTask(TaskControl taskControl) {
        // select the task from the todolist
        taskControl.toggleSelect();
        // also toggle the model
        Task model = taskControl.getTask();
        boolean isSelected = taskControl.getSelected();

        if (isSelected) {
            toDoList.selectTask(model);
        } else {
            toDoList.deselectTask(model);
        }
    }

    public void removeSelected() {
        // get all selected tasks from todolist
        // remove all of those tasks from the taskContainer
        ArrayDeque<TaskControl> removalStack = new ArrayDeque<>();

        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t && t.getSelected()) {
                // store the previous
                removalStack.add(t);
            }
        }

        // need to use a stack because if we remove in regular order, we will try to remove the same item twice
        // resulting in error
        for (TaskControl taskControl : removalStack) {
            removeTask(taskControl);
        }
    }

    public void toggleComplete(TaskControl taskControl) {
        // toggle complete for the taskControl
        taskControl.toggleComplete();

        // also toggle the model
        Task model = taskControl.getTask();
        boolean isComplete = taskControl.getComplete();

        if (isComplete) {
            toDoList.markTaskComplete(model);
        } else {
            toDoList.markTaskIncomplete(model);
        }
    }

    public void markSelectedTasksComplete() {
        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t && t.getSelected() && !t.getComplete()) {
                // store the previous
                t.toggleComplete();
            }
        }

        // after user marks the tasks, we deselect them
        deselectAllTasks();
    }

    public void markSelectedTasksIncomplete() {
        // loop through, marking incomplete
        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t && t.getSelected() && t.getComplete()) {
                // store the previous
                t.toggleComplete();
            }
        }

        // after user marks the tasks, we deselect them
        deselectAllTasks();
    }

    public void selectAllTasks() {
        // loop through, selecting all
        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t && !t.getSelected()) {
                 t.toggleSelect();
            }
        }
    }

    public void deselectAllTasks() {
        // loop through, deselecting all
        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t && t.getSelected()) {
                // store the previous
                t.toggleSelect();
            }
        }
    }

    public void viewAllTasks() {
        // display the allTaskContainerControl
        tasksScrollPane.setContent(allTaskContainerControl);
        listHeaderControl.changeTitle("All Tasks");
        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t) {
                allTaskContainerControl.setTaskVisible(t, true);
            }
        }
    }

    public void viewCompletedTasks() {
        // view all tasks first, then rearrange from there

        viewAllTasks();
        listHeaderControl.changeTitle("Completed Tasks");

        // display the tasks that are completed, hide the others
        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t && !t.getComplete()) {
                allTaskContainerControl.setTaskVisible(t, false);
            }
        }
    }

    public void viewUncompletedTasks() {
        // view all tasks first, then rearrange from there

        viewAllTasks();
        listHeaderControl.changeTitle("Uncompleted Tasks");

        /// display the tasks that are uncompleted, hide the others
        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t && t.getComplete()) {
                allTaskContainerControl.setTaskVisible((TaskControl) taskControl, false);
            }
        }
    }

    public void viewTasksSortedByDate(boolean ascending) {
        // sort all tasks by date then show that
        // create a list to sort the tasks

        List<TaskControl> sortList = new ArrayList<>();
        ArrayDeque<TaskControl> removalStack = new ArrayDeque<>();

        // were going to remove the tasks since we can't reference them in multiple containers
        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t) {
                // store the previous
                removalStack.add(t);
            }
        }

        // need to use a stack because if we remove in regular order, we will try to remove the same item twice
        // resulting in error
        // add to our sort list once removed
        for (TaskControl taskControl : removalStack) {
            // need to keep our size the same, we must increment when we temporarily remove
            size++;
            removeTask(taskControl);
            sortList.add(taskControl);
        }

        // sort the tasks by the date
        if (ascending) {
            sortList.sort(Comparator.comparing(TaskControl::getDueDate));
        } else {
            sortList.sort(Comparator.comparing(TaskControl::getDueDate).reversed());
        }

        // prep the sorted view for the user
        for (TaskControl taskControl : sortList) {
            allTaskContainerControl.addTaskControl(taskControl);
        }
    }

    public void openAllTasks() {
        // get all the tasks from list
        // open all
        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t) {
                // open all
                t.setCollapsed(false);
            }
        }
    }

    public void collapseAllTasks() {
        // get all the tasks from list
        // collapse all
        for (Node taskControl: allTaskContainerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t) {
                // close all
                t.setCollapsed(true);
            }
        }
    }

    public void clearAllTasks() {
        // remove all the tasks
        allTaskContainerControl = new TaskContainerControl();
        tasksScrollPane.setContent(allTaskContainerControl);

        //clear the model
        toDoList.clearAllTasks();

        size = 0;
    }

    // useful for serialization of list!
    public TaskContainerControl getAllTaskContainerControl() {
        // return the container to verify content
        return allTaskContainerControl;
    }
}
