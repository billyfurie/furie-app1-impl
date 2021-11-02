/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package models;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    private final ArrayList<Task> tasks;

    public ToDoList() {
        // init the tasks list
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        // add a task to our list
        tasks.add(task);
    }

    public void removeTask(Task task) {
        // remove the task from our list
        tasks.remove(task);
    }

    public boolean containsTask(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    public void clearAllTasks() {
        // remove all tasks from the list
        tasks.clear();
    }

    public void markTaskComplete(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                task.setComplete(true);
            }
        }
    }

    public void markTaskIncomplete(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                task.setComplete(false);
            }
        }
    }

    public void selectTask(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                task.setSelected(true);
            }
        }
    }

    public void deselectTask(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                task.setSelected(false);
            }
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getCompletedTasks() {
        ArrayList<Task> completed = new ArrayList<>();

        // add if completed
        for (Task t : tasks) {
            if (t.isComplete()) {
                completed.add(t);
            }
        }

        return completed;
    }

    public List<Task> getUncompletedTasks() {
        ArrayList<Task> uncompleted = new ArrayList<>();

        // add if uncompleted
        for (Task t : tasks) {
            if (!t.isComplete()) {
                uncompleted.add(t);
            }
        }

        return uncompleted;
    }
}
