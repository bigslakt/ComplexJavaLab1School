package se.alten.schoolproject.dao;


import se.alten.schoolproject.entity.Student;
import se.alten.schoolproject.model.StudentModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SchoolAccessLocal {

    List<Student> listAllStudents() throws Exception;

    String addStudent(String studentModel);
}
