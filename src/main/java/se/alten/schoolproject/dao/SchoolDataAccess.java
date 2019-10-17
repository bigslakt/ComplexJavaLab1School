package se.alten.schoolproject.dao;

import se.alten.schoolproject.entity.Student;
import se.alten.schoolproject.transaction.StudentTransactionAccess;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SchoolDataAccess implements SchoolAccessLocal {

    @Inject
    StudentTransactionAccess studentTransactionAccess;

    @Override
    public void test() throws Exception {
        studentTransactionAccess.test();
    }
}
