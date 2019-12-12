package se.alten.schoolproject.dao;

import se.alten.schoolproject.entity.Student;
import se.alten.schoolproject.exception.MissingArgumentException;
import se.alten.schoolproject.exception.NotAllowedToPatchEmailException;
import se.alten.schoolproject.exception.StudentAlreadyExistException;
import se.alten.schoolproject.exception.StudentDontExistException;
import se.alten.schoolproject.model.StudentModel;
import se.alten.schoolproject.transaction.StudentTransactionAccess;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class SchoolDataAccess implements SchoolAccessLocal, SchoolAccessRemote {

    @Inject
    StudentTransactionAccess studentTransactionAccess;

    @Inject
    EntityModelConverter entityModelConverter;

    @Override
    public List listAllStudents(){
        return studentTransactionAccess.listAllStudents();
    }

    @Override
    public StudentModel getStudentByEmail(String email) {

        List studentList = listAllStudents();

        for (Object student : studentList) {

            if(((Student)student).getEmail().equals(email)) {

                return (entityModelConverter.toModel((Student)student));
            }
        }

        throw new StudentDontExistException("Student with that email does not exist");
    }

    @Override
    public List getStudentByName(String name) {

        List<Student> studentList = listAllStudents();

        List<Student> studentsByNameList = studentList.stream()
                .filter(student -> student.getForename().equals(name) || student.getLastname().equals(name))
                .collect(Collectors.toList());

        if(studentsByNameList.isEmpty()) {

            throw new StudentDontExistException("Student with that name does not exist");
        }

        return entityModelConverter.listToModel(studentsByNameList);
    }

    @Override
    public List getStudentByFullName(String foreName, String lastName) {

        List<Student> studentList = listAllStudents();

        List<Student> studentsByNameList = studentList.stream()
                .filter(student -> student.getForename().equals(foreName) && student.getLastname().equals(lastName))
                .collect(Collectors.toList());

        if(studentsByNameList.isEmpty()) {

            throw new StudentDontExistException("Student with that name does not exist");
        }

        return entityModelConverter.listToModel(studentsByNameList);
    }

    @Override
    public StudentModel addStudent(String newStudent) {

        Student studentToAdd = entityModelConverter.toEntity(newStudent);
        boolean checkForEmptyVariables = Stream.of(studentToAdd.getForename(), studentToAdd.getLastname(), studentToAdd.getEmail()).anyMatch(String::isBlank);

        if (checkForEmptyVariables) {
            throw new MissingArgumentException("Request-body is not complete!");
        }

        List<Student> studentList = listAllStudents();

        for (Student student : studentList) {

            if(student.getEmail().equals(studentToAdd.getEmail())) {
                throw new StudentAlreadyExistException("Student with that email already exists!");
            }
        }

        studentTransactionAccess.addStudent(studentToAdd);
        return entityModelConverter.toModel(studentToAdd);
    }

    @Override
    public void removeStudent(String studentEmail) {

        List<Student> studentList = listAllStudents();

        for (Student student : studentList) {

            if(student.getEmail().equals(studentEmail)) {
                studentTransactionAccess.removeStudent(student);
                return;
            }
        }

        throw new StudentDontExistException("Student with that email does not exist!");
    }

    @Override
    public StudentModel updateStudent(String currentEmail, String studentModel) {

        //TODO: måste göra koller först så inte ett objekt tas bort om de nya inte funkar
        removeStudent(currentEmail);
        return addStudent(studentModel);
    }

    @Override
    public StudentModel updateStudentPartial(String currentEmail, String studentUpdate) {

        JsonReader reader = Json.createReader(new StringReader(studentUpdate));
        JsonObject jsonObject = reader.readObject();

        List<Student> studentList = listAllStudents();
        Student studentToUpdate;

        for (Student student : studentList) {

            if(student.getEmail().equals(currentEmail)) {

                studentToUpdate = student;
                if(jsonObject.containsKey("email")) {
                    throw new NotAllowedToPatchEmailException("Not allowed to update email through patch request!");
                }
                if(jsonObject.containsKey("forename")) {
                    studentToUpdate.setForename(jsonObject.getString("forename"));
                }
                if(jsonObject.containsKey("lastname")) {
                    studentToUpdate.setLastname(jsonObject.getString("lastname"));
                }

                Student updatedStudent = studentTransactionAccess.updateStudent(studentToUpdate);
                return entityModelConverter.toModel(updatedStudent);
            }
        }

        throw new StudentDontExistException("Student with that email does not exist!");
    }
}
