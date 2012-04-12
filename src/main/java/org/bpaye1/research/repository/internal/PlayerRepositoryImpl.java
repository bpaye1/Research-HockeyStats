package org.bpaye1.research.repository.internal;

import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.player.Status;
import org.bpaye1.research.repository.PlayerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PlayerRepositoryImpl extends GenericRepositoryImpl<Player, Long> implements PlayerRepository {

	public Player find(Long id) {
		return em.find(Player.class, id);
	}

    public List<Player> findAll() {
        CriteriaQuery<Player> criteriaQuery = em.getCriteriaBuilder().createQuery(Player.class);
        Root<Player> playerRoot = criteriaQuery.from(Player.class);
        criteriaQuery.select(playerRoot);
        TypedQuery<Player> query =  em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Player> findAllActive() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQuery = builder.createQuery(Player.class);
        Root<Player> playerRoot = criteriaQuery.from(Player.class);

        criteriaQuery.select(playerRoot);

        Predicate statusPredicate = builder.equal(playerRoot.get("status"), Status.ACTIVE);

        criteriaQuery.where(statusPredicate);

        TypedQuery<Player> query =  em.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
