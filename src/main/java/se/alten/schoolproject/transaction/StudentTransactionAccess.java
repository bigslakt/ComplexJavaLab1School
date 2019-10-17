package se.alten.schoolproject.transaction;

import se.alten.schoolproject.entity.Student;

import javax.ejb.Local;

@Local
public interface StudentTransactionAccess {
    public abstract void test();
}
