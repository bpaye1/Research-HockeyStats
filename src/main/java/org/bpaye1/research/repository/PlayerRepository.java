	package org.bpaye1.research.repository;

import org.bpaye1.research.model.player.Player;

import java.util.List;

    public interface PlayerRepository extends GenericRepository<Player, Long>{
        List<Player> findAllActive();
    }
