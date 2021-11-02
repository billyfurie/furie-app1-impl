/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package baseline;

import javafx.scene.Node;
import javafx.scene.control.Label;
import models.Task;
import controls.TaskContainerControl;
import controls.TaskControl;
import controls.ToDoListControl;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {

    public void saveListToFile(ToDoListControl listControl, File file) throws IOException {
        // will use a JavaFX FileChooser here
        ArrayList<Task> tasks = getDataFromListControl(listControl);

        // serialize the listControl as a collection of task models
        saveArrayListToFile(tasks, file);
    }

    public void saveArrayListToFile(List<Task> tasks, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.flush();
        }
    }

    private ArrayList<Task> getDataFromListControl(ToDoListControl listControl) {
        ArrayList<Task> tasksList = new ArrayList<>();
        TaskContainerControl containerControl = listControl.getAllTaskContainerControl();

        // get all the tasks of the list
        for (Node taskControl: containerControl.getChildren())
        {
            if (taskControl instanceof TaskControl t) {
                // prep for creating our task
                String description = t.getDescription();
                String dueDate = t.getDueDate();
                boolean isCompleted = t.getComplete();
                boolean isSelected = t.getSelected();

                // convert our taskControl to a serializable
                Task task = new Task(description, dueDate, isCompleted, isSelected);
                tasksList.add(task);
            }
        }

        return tasksList;
    }

    // throws exception if we find that the file is not a list type
    // unchecked cast is handled by ClassNotFound exception
    @SuppressWarnings("unchecked")
    public List<Task> loadArrayListFromFile(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // ** this unchecked cast is handled by throwing exception **
            return (ArrayList<Task>) ois.readObject();
        } catch (ClassNotFoundException e) {
            return Collections.emptyList();
        }
    }

    public ToDoListControl loadListFromFile(File file, Label alertLabel) throws IOException {
        // deserialize the file if it exists

        // ** this unchecked cast is handled by throwing exception **
        ArrayList<Task> tasks = (ArrayList<Task>) loadArrayListFromFile(file);

        ToDoListControl listControl = new ToDoListControl(alertLabel);

        // go through all the tasks, convert to task controls
        for (Task task : tasks) {
            TaskControl taskControl = new TaskControl(task.getDescription(), task.getDueDate(), alertLabel);
            listControl.addTaskControl(taskControl);

            if (task.isComplete()) {
                listControl.toggleComplete(taskControl);
            }

            if (task.isSelected()) {
                listControl.selectTask(taskControl);
            }
        }

        return listControl;
    }
}
