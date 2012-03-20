package org.bpaye1.research.repository.internal;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.repository.PlayerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepositoryImpl extends GenericRepositoryImpl<Player> implements PlayerRepository {

	public Player find(Long id) {
		return em.find(Player.class, id);
	}

	public List<Player> list() {
		CriteriaQuery<Player> criteriaQuery = em.getCriteriaBuilder().createQuery(Player.class);
		criteriaQuery.select(criteriaQuery.from(Player.class));
		TypedQuery<Player> query =  em.createQuery(criteriaQuery);
		return query.getResultList();
	}
}
