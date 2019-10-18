package se.alten.schoolproject.model;

import lombok.*;
import se.alten.schoolproject.entity.Student;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentModel {

    private Long id;
    private String forename;
    private String lastname;
    private String email;

    public StudentModel toModel(Student student) {
        if ( student.getEmail().equals("Email already exist!") ) {
            StudentModel studentModel = new StudentModel();
            studentModel.setForename("N");
            studentModel.setEmail("email not present");
            return studentModel;
        } else if ( student.getForename().isBlank()) {
            StudentModel studentModel = new StudentModel();
            studentModel.setForename("");
            return studentModel;
        } else {
            StudentModel studentModel = new StudentModel();
            studentModel.setForename(student.getForename());
            studentModel.setLastname(student.getLastname());
            studentModel.setEmail(student.getEmail());
            return studentModel;
        }
    }
}
