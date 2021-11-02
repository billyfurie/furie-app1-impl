/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package baseline;

import models.Task;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    /*
    TEST FUNCTIONALITY OF FOLLOWING REQUIREMENTS
        -A user shall be able to save the list (and all its items) to local storage while the application is being used
        -A user shall be able to load a previously saved list while the application is being used
     */

    @Test
    void saveArrayListToFile() {
        // verify that the file is created for this list at the proper location
        ArrayList<Task> tasks = new ArrayList<>();

        // save a list to a file
        FileManager manager = new FileManager();

        File file = new File("src/test/resources/save_test.txt");

        try {
            manager.saveArrayListToFile(tasks, file);
        } catch (IOException e) {
            System.out.println("Could not save file in saveArrayListToFile test");
        }

        // verify
        File fileCheck = new File("src/test/resources/save_test.txt");

        assertTrue(fileCheck.exists());
    }

    @Test
    void loadListFromFile() {
        // create test file
        ArrayList<Task> tasks = new ArrayList<>();
        // the one we will load
        ArrayList<Task> tasksLoaded = new ArrayList<>();

        tasks.add(new Task("Test Task 1", "2021-11-01", false, false));
        tasks.add(new Task("Test Task 2", "2021-11-30", true, false));
        tasks.add(new Task("Test Task 3", "2021-08-21", false, true));
        tasks.add(new Task("Test Task 4", "2021-04-09", true, true));

        // save a list to a file
        FileManager manager = new FileManager();

        File file = new File("src/test/resources/load_test.txt");

        try {
            manager.saveArrayListToFile(tasks, file);
        } catch (IOException e) {
            System.out.println("Could not save file in loadListFromFile test");
        }

        // read from them
        try {
            tasksLoaded = (ArrayList<Task>) manager.loadArrayListFromFile(file);
        } catch (IOException e) {
            System.out.println("Could not load file in loadListFromFile test");
        }


        // go through and check that all tasks are equivalent
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            Task taskLoaded = tasksLoaded.get(i);

            assertEquals(task.getDescription(), taskLoaded.getDescription());
            assertEquals(task.getDueDate(), taskLoaded.getDueDate());
            assertEquals(task.isComplete(), taskLoaded.isComplete());
            assertEquals(task.isSelected(), taskLoaded.isSelected());
        }
    }
}