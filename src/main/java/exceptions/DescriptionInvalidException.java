/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 William Furie
 */

package exceptions;

import java.security.InvalidParameterException;

public class DescriptionInvalidException extends InvalidParameterException {

    public DescriptionInvalidException() {
        /*
        This method is empty because we do not need anything else for this exception implementation
        This exception is for when a user tries to add an invalid description (Not 1-456 characters)
         */
    }
}
