package se.alten.schoolproject.dao;

import se.alten.schoolproject.model.StudentModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SchoolAccessLocal {

    List listAllStudents() throws Exception;

    StudentModel addStudent(String studentModel);

    void removeStudent(String student);

    void updateStudent(String forename, String lastname, String email);

    void updateStudentPartial(String studentModel);
}
