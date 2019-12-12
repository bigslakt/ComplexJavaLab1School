package se.alten.schoolproject.dao;

import se.alten.schoolproject.entity.Student;
import se.alten.schoolproject.exception.MissingArgumentException;
import se.alten.schoolproject.model.StudentModel;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class EntityModelConverter {

    public Student toEntity(String studentModel) {

        JsonReader reader = Json.createReader(new StringReader(studentModel));
        JsonObject jsonObject = reader.readObject();

        Student student = new Student();

        if ( jsonObject.containsKey("forename")) {
            student.setForename(jsonObject.getString("forename"));
        } else {
            throw new MissingArgumentException("Forename is missing in the request-body!");
        }

        if ( jsonObject.containsKey("lastname")) {
            student.setLastname(jsonObject.getString("lastname"));
        } else {
            throw new MissingArgumentException("Lastname is missing in the request-body!");
        }

        if ( jsonObject.containsKey("email")) {
            student.setEmail(jsonObject.getString("email"));
        } else {
            throw new MissingArgumentException("Email is missing in the request-body!");
        }

        return student;
    }

    public StudentModel toModel(Student student) {

        StudentModel studentModel = new StudentModel();

        studentModel.setForename(student.getForename());
        studentModel.setLastname(student.getLastname());
        studentModel.setEmail(student.getEmail());

        return studentModel;
    }

    public List<StudentModel> listToModel(List<Student> studentlist) {

        List<StudentModel> studentModelList = new ArrayList<>();

        studentlist.forEach(student -> studentModelList.add(toModel(student)));

        return studentModelList;
    }
}
