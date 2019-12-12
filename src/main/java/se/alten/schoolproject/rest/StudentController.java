package se.alten.schoolproject.rest;

import lombok.NoArgsConstructor;
import mjson.Json;
import se.alten.schoolproject.dao.SchoolAccessLocal;
import se.alten.schoolproject.exception.StudentDontExistException;
import se.alten.schoolproject.model.StudentModel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@NoArgsConstructor
@Path("/student")
public class StudentController {

    @Inject
    private SchoolAccessLocal sal;

    @GET
    @Produces({"application/JSON"})
    public Response showStudents() {

        try {
            List students = sal.listAllStudents();
            return Response.ok(students).build();
        } catch ( Exception e ) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{email}")
    @Produces({"application/JSON"})
    public Response getStudentByEmail(@PathParam("email") String email) {

        try {
            StudentModel studentModelResponse = sal.getStudentByEmail(email);
            return Response.ok(studentModelResponse).build();
        }catch(StudentDontExistException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch ( Exception e ) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/byName/{name}")
    @Produces({"application/JSON"})
    public Response getStudentByName(@PathParam("name") String name) {

        try {
            List studentsByNameList = sal.getStudentByName(name);
            return Response.ok(studentsByNameList).build();
        }catch(StudentDontExistException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch ( Exception e ) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/byFullName")
    @Produces({"application/JSON"})
    public Response getStudentByFullName(@QueryParam("forename") String foreName, @QueryParam("lastname") String lastName) {

        try {
            List studentsByNameList = sal.getStudentByFullName(foreName, lastName);
            return Response.ok(studentsByNameList).build();
        }catch(StudentDontExistException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch ( Exception e ) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/JSON"})
    public Response addStudent(String studentModel) {

        try {
            StudentModel studentModelResponse = sal.addStudent(studentModel);
            return Response.status(Response.Status.CREATED).entity(studentModelResponse).build();
        }catch ( Exception e ) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{email}")
    public Response deleteUser( @PathParam("email") String email) {
        try {
            sal.removeStudent(email);
            return Response.ok().build();
        }catch(StudentDontExistException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch ( Exception e ) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/JSON"})
    @Path("{email}")
    public Response updateStudent( @PathParam("email") String currentEmail, String studentModel) {

        try{
            StudentModel studentModelResponse = sal.updateStudent(currentEmail, studentModel);
            return Response.ok(studentModelResponse).build();
        }catch(StudentDontExistException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/JSON"})
    @Path("{email}")
    public Response updateStudentPartial(@PathParam("email") String currentEmail, String studentModel) {
        try {
            StudentModel studentModelResponse = sal.updateStudentPartial(currentEmail, studentModel);
            return Response.ok(studentModelResponse).build();
        }catch(StudentDontExistException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
