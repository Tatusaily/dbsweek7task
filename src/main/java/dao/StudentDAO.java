package dao;

import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.ProgressReport;
import model.Student;
import jakarta.persistence.EntityManager;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StudentDAO extends GenericDAO<Student> {
    public StudentDAO(EntityManager entityManager) {
        super(Student.class, entityManager);
    }

    public List<Student> getStudentsByRank (String rank) {
        // Ei sanitaatiota, voi olla mikä vain string

        List<Student> students = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.rank = :rank", Student.class)
                .setParameter("rank", rank)
                .getResultList();
        return students;
    }

    public List<Student> getActiveStudents() {

        List<Student> students = entityManager.createQuery(
                "SELECT Student from ProgressReport where reportDate >= :datedate", Student.class)
                .setParameter("datedate", Timestamp.valueOf(LocalDateTime.now().minusMonths(3)))
                .getResultList();

        return students;
    }

    public List<Student> findNewStudents6Months() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        cq.select(student).where(cb.greaterThanOrEqualTo(student.get("joinDate"), sixMonthsAgo));
        return entityManager.createQuery(cq).getResultList();
    }

    public void updateStudentReport(ProgressReport progressReport) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(progressReport);
            entityManager.getTransaction().commit();
        } catch (OptimisticLockException e) {
            entityManager.getTransaction().rollback();
            notifyUserOfConflict(progressReport);
        }
    }

    private void notifyUserOfConflict(ProgressReport progressReport) {
        // Implement your notification mechanism here
        System.out.println("Conflict detected for ProgressReport ID: " + progressReport.getId());
    }

}
