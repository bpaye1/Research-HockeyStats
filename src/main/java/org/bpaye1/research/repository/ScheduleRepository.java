package org.bpaye1.research.repository;

import org.bpaye1.research.model.schedule.Schedule;
import org.bpaye1.research.model.schedule.game.Game;

public interface ScheduleRepository extends GenericRepository<Schedule, Integer> {
    Game findGame(Long id);
}
