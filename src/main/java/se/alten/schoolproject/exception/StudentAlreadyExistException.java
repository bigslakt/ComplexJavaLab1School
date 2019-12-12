package se.alten.schoolproject.exception;

import javax.ejb.ApplicationException;
import javax.persistence.PersistenceException;

@ApplicationException
public class StudentAlreadyExistException extends PersistenceException {

    public StudentAlreadyExistException(String message) {
        super(message);
    }
}
