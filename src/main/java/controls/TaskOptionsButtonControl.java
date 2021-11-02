/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TaskOptionsButtonControl extends Button {

    public TaskOptionsButtonControl(String text) {
        // set the text of the button
        this.setText(text);

        // set the style of the button per our GUI mockup
        initializeStyle();
    }

    private void initializeStyle() {
        // Set the font
        setFont(Font.font("System", FontWeight.BOLD, 14));
        getStyleClass().add("TaskOptionsButton");
        setMaxWidth(Double.MAX_VALUE);

        // set the margin
        HBox.setMargin(this, new Insets(0, 8, 8, 0));
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}
