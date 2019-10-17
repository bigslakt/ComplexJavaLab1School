package se.alten.schoolproject.transaction;

import se.alten.schoolproject.entity.Student;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Stateless
@Default
public class StudentTransaction implements StudentTransactionAccess{
    @Override
    public void test() {
        System.out.println("Hej Hej");
    }
}
