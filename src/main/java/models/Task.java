/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package models;

import java.io.Serializable;

public class Task implements Serializable {

    private String description;
    private String dueDate;
    private boolean complete;
    private boolean selected;

    // this class is for serialization of our data
    public Task(String description, String dueDate, boolean complete, boolean selected){
        this.description = description;
        this.dueDate = dueDate;
        this.complete = complete;
        this.selected = selected;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isComplete() {
        return complete;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setComplete(boolean isComplete) {
        complete = isComplete;
    }

    public void setSelected(boolean isSelected) {
        selected = isSelected;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    public void setDueDate(String newDueDate) {
        dueDate = newDueDate;
    }
}
