/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package controls;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class TaskContainerControl extends VBox {

    public TaskContainerControl() {
        // initialize the GUI style
        initializeStyle();
    }

    public void addTaskControl(TaskControl taskControl) {
        // set the margins for the task control
        setMargin(taskControl, new Insets(0, 8, 8, 8));

        // add the task control to our view
        getChildren().add(taskControl);
    }

    public void removeTaskControl(TaskControl taskControl) {
        // remove the task control from our view
        getChildren().remove(taskControl);
    }

    public void setTaskVisible(TaskControl taskControl, boolean visible) {
        taskControl.setVisible(visible);
        taskControl.setManaged(visible);
    }

    private void initializeStyle() {
        // set the style for the task container per our GUI mockup
        setPadding(new Insets(8, 0, 0 ,0));
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);
        getStyleClass().add("TaskContainer");
    }
}
