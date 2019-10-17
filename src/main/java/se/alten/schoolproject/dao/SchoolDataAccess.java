package se.alten.schoolproject.dao;

import se.alten.schoolproject.entity.Student;
import se.alten.schoolproject.model.StudentModel;
import se.alten.schoolproject.transaction.StudentTransactionAccess;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Stream;

@Stateless
public class SchoolDataAccess implements SchoolAccessLocal, SchoolAccessRemote {

    private Student student = new Student();
    private StudentModel studentModel = new StudentModel();

    @Inject
    StudentTransactionAccess studentTransactionAccess;

    @Override
    public List<Student> listAllStudents(){
        return studentTransactionAccess.listAllStudents();
    }

    @Override
    public String addStudent(String studentModel) {
        Student studentToAdd = student.toEntity(studentModel);
        boolean checkforEmptyVariables = Stream.of(studentToAdd.getForename(), studentToAdd.getLastname(), studentToAdd.getEmail()).anyMatch(str -> str.isBlank());

        if (checkforEmptyVariables) {
            return "Not OK";
        } else {
            return studentTransactionAccess.addStudent(studentToAdd);
        }
    }
}
