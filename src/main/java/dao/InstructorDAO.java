package dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Instructor;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class InstructorDAO extends GenericDAO<Instructor> {
    public InstructorDAO(EntityManager entityManager) {
        super(Instructor.class, entityManager);
    }

    public List<Instructor> getInstructorsBySpecialization(String specialization) {
        List<Instructor> instructors = entityManager.createQuery(
                "SELECT i FROM Instructor i WHERE i.specialization = :specialization", Instructor.class)
                .setParameter("specialization", specialization)
                .getResultList();
        return instructors;
    }

    public List<Instructor> findInstructorsWithMoreThanFiveYearsExperience() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
        Root<Instructor> instructor = cq.from(Instructor.class);
        LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);
        cq.select(instructor).where(cb.lessThanOrEqualTo(instructor.get("joinDate"), fiveYearsAgo));
        return entityManager.createQuery(cq).getResultList();
    }
}
