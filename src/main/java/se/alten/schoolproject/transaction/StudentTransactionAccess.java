package se.alten.schoolproject.transaction;

import se.alten.schoolproject.entity.Student;

import javax.ejb.Local;
import java.util.List;

@Local
public interface StudentTransactionAccess {
    List listAllStudents();
    Student addStudent(Student studentToAdd);
    void removeStudent(Student student);
    Student updateStudent(Student student);
}
