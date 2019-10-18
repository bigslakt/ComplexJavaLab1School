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
}
