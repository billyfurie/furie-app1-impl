/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package exceptions;

import java.security.InvalidParameterException;

public class ListFullException extends InvalidParameterException {

    public ListFullException() {
        /*
        This method is empty because we do not need anything else for this exception implementation
        This exception is for when a user tries to add a task to a full list
         */
    }
}
