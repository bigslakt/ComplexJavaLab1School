package se.alten.schoolproject.rest;

import lombok.NoArgsConstructor;
import se.alten.schoolproject.dao.SchoolAccessLocal;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Stateless
@NoArgsConstructor
@Path("/student")
public class StudentController {

    @Inject
    private SchoolAccessLocal sal;

    @GET
    @Produces({"application/json"})
    public Response test() {
        System.out.println("At Rest");
        try {
           // sal.test();
            String t = "Hej Hej";
            return Response.ok(t).build();
        } catch ( Exception pe ) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
