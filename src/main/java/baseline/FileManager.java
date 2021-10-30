/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package baseline;

import models.ToDoListControl;

import java.io.IOException;

public class FileManager {

    public void saveListToFile(ToDoListControl listControl, String filepath) throws IOException {
        // will use a JavaFX FileChooser here
        // serialize the listControl
        // save to file at specified location
        // throws IOException
    }

    // throws exception if we find that the file is not a list type
    public ToDoListControl loadListFromFile(String filepath) throws IllegalArgumentException {
        // will use a JavaFX FileChooser here
        // deserialize the file if it exists
        // return the ToDoListControl

        return null;
    }
}
