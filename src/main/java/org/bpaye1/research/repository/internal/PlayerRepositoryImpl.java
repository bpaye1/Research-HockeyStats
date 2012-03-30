package org.bpaye1.research.repository.internal;

import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.repository.PlayerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class PlayerRepositoryImpl extends GenericRepositoryImpl<Player, Long> implements PlayerRepository {

	public Player find(Long id) {
		return em.find(Player.class, id);
	}

    public List<Player> findAll() {
        CriteriaQuery<Player> criteriaQuery = em.getCriteriaBuilder().createQuery(Player.class);
        criteriaQuery.select(criteriaQuery.from(Player.class));
        TypedQuery<Player> query =  em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
