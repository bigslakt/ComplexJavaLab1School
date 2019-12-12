package se.alten.schoolproject.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class NotAllowedToPatchEmailException extends IllegalArgumentException {

    public NotAllowedToPatchEmailException(String message) {
        super(message);
    }
}
