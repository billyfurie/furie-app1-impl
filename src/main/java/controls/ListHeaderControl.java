/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ListHeaderControl extends HBox {

    private final Label listTitle;

    // the list header, shows which view user is in for the list
    public ListHeaderControl(String listTitle) {
        // create a label
        this.listTitle = new Label();

        // set the text to the title text
        // format the label per our GUI mockup design
        initializeGUI(listTitle);
    }

    private void initializeGUI(String title) {
        // initialize the GUI per our mockup design
        initializeLabel(title);
        setMargin(listTitle,new Insets(24, 0, 16, 8));
    }

    private void initializeLabel(String title) {
        // set the color
        listTitle.getStyleClass().add("ListTitleName");
        listTitle.setFont(Font.font("System", 40));

        // set the alignment
        listTitle.setAlignment(Pos.CENTER);

        // set the title
        listTitle.setText(title);

        getChildren().add(listTitle);
    }

    public void changeTitle(String title) {
        // set the text of the title
        listTitle.setText(title);
    }
}
