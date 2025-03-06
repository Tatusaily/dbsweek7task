package dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.TrainingSession;
import jakarta.persistence.EntityManager;
import java.util.List;


public class TrainingSessionDAO extends GenericDAO<TrainingSession> {
    public TrainingSessionDAO(EntityManager entityManager) {
        super(TrainingSession.class, entityManager);
    }

    public List<TrainingSession> getSessionsByStudentID(Long studentId) {
        List<TrainingSession> trainingSessions = entityManager.createQuery(
                "SELECT ts FROM TrainingSession ts" +
                    " JOIN ts.students s WHERE s.id = :studentId", TrainingSession.class)
                .setParameter("studentId", studentId)
                .getResultList();
        return trainingSessions;
    }

    public List<TrainingSession> findTrainingSessionsByLocation(String location) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TrainingSession> cq = cb.createQuery(TrainingSession.class);
        Root<TrainingSession> trainingSession = cq.from(TrainingSession.class);
        cq.select(trainingSession).where(cb.equal(trainingSession.get("location"), location));
        return entityManager.createQuery(cq).getResultList();
    }

}
