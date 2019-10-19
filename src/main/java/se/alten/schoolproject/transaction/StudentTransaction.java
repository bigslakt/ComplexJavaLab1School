package se.alten.schoolproject.transaction;

import se.alten.schoolproject.entity.Student;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Default
public class StudentTransaction implements StudentTransactionAccess{

    @PersistenceContext(unitName="school")
    private EntityManager entityManager;

    @Override
    public List listAllStudents() {
        Query query = entityManager.createQuery("SELECT s from Student s");
        return query.getResultList();
    }

    @Override
    public Student addStudent(Student studentToAdd) {
        try {
            entityManager.persist(studentToAdd);
            entityManager.flush();
            return studentToAdd;
        } catch ( PersistenceException pe ) {
            studentToAdd.setForename("duplicate");
            return studentToAdd;
        }
    }

    @Override
    public void removeStudent(String student) {
        //JPQL Query
        Query query = entityManager.createQuery("DELETE FROM Student s WHERE s.email = :email");

        //Native Query
        //Query query = entityManager.createNativeQuery("DELETE FROM student WHERE email = :email", Student.class);

        query.setParameter("email", student)
             .executeUpdate();
    }

    @Override
    public void updateStudent(String forename, String lastname, String email) {
        Query updateQuery = entityManager.createNativeQuery("UPDATE student SET forename = :forename, lastname = :lastname WHERE email = :email", Student.class);
        updateQuery.setParameter("forename", forename)
                   .setParameter("lastname", lastname)
                   .setParameter("email", email)
                   .executeUpdate();
    }

    @Override
    public void updateStudentPartial(Student student) {
        Student studentFound = (Student)entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email")
                .setParameter("email", student.getEmail()).getSingleResult();

        Query query = entityManager.createQuery("UPDATE Student SET forename = :studentForename WHERE email = :email");
        query.setParameter("studentForename", student.getForename())
                .setParameter("email", studentFound.getEmail())
                .executeUpdate();
    }
}
