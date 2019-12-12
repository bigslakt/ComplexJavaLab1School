package se.alten.schoolproject.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class StudentDontExistException extends IllegalArgumentException {

    public StudentDontExistException(String message) {
        super(message);
    }
}
