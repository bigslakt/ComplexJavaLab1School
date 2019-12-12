package se.alten.schoolproject.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class MissingArgumentException extends IllegalArgumentException {

    public MissingArgumentException(String message) {
        super(message);
    }
}
