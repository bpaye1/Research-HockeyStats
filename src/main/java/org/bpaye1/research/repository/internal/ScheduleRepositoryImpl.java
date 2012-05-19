package org.bpaye1.research.repository.internal;

import org.bpaye1.research.model.schedule.game.Game;
import org.bpaye1.research.model.schedule.Schedule;
import org.bpaye1.research.repository.ScheduleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl extends GenericRepositoryImpl<Schedule, Integer> implements ScheduleRepository {

    public List<Schedule> findAll() {
        CriteriaQuery<Schedule> criteriaQuery = em.getCriteriaBuilder().createQuery(Schedule.class);
        criteriaQuery.select(criteriaQuery.from(Schedule.class));
        TypedQuery<Schedule> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Schedule find(Integer id) {
        return em.find(Schedule.class, id);
    }

    public Game findGame(Long id) {
        String hql = "select g from Game g left join fetch g.gameStats left join fetch g.goalieGameStats where g.id = :id";
        TypedQuery<Game> query = em.createQuery(hql, Game.class).setParameter("id", id);
        return query.getSingleResult();
    }
}
