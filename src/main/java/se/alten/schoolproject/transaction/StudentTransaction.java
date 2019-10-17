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
    public List<Student> listAllStudents() {
        Query query = entityManager.createQuery("SELECT s from Student s");
        List<Student> students = query.getResultList();
        return students;
    }

    @Override
    public String addStudent(Student studentToAdd) {
        try {
            entityManager.persist(studentToAdd);
            entityManager.flush();
        } catch ( PersistenceException pe ) {
            return "Email exists";
        }
        return "OK";
    }
}
