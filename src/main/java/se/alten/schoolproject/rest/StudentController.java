package se.alten.schoolproject.rest;

import lombok.NoArgsConstructor;
import se.alten.schoolproject.dao.SchoolAccessLocal;
import se.alten.schoolproject.entity.Student;
import se.alten.schoolproject.model.StudentModel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Stateless
@NoArgsConstructor
@Path("/student")
public class StudentController {

    @Inject
    private SchoolAccessLocal sal;

    @GET
    @Produces({"application/json"})
    public Response showStudents() {
        try {
            List students = sal.listAllStudents();
            return Response.ok(students).build();
        } catch ( Exception e ) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Path("/add")
    @Produces({"application/json"})
    /**
     * JavaDoc
     */
    public Response addStudent(String studentModel) {
        try {

            StudentModel answer = sal.addStudent(studentModel);

            switch ( answer.getForename()) {
                case "empty":
                    return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{\"Fill in all details please\"}").build();
                case "duplicate":
                    return Response.status(Response.Status.EXPECTATION_FAILED).entity("{\"Email already registered!\"}").build();
                default:
                    return Response.ok(answer).build();
            }
        } catch ( Exception e ) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
