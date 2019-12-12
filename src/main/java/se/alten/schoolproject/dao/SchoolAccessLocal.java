package se.alten.schoolproject.dao;

import se.alten.schoolproject.model.StudentModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SchoolAccessLocal {

    List listAllStudents() throws Exception;

    StudentModel getStudentByEmail(String email);

    List getStudentByName(String name);

    List getStudentByFullName(String foreName, String lastName);

    StudentModel addStudent(String studentModel);

    void removeStudent(String student);

    StudentModel updateStudent(String currentEmail, String studentModel);

    StudentModel updateStudentPartial(String currentEmail, String studentModel);
}
