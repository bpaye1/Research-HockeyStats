package org.bpaye1.research.service;

import org.bpaye1.research.model.schedule.Game;
import org.bpaye1.research.model.schedule.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule findSchedule(Integer id);
    List<Schedule> findAllSchedules();
    void addSchedule(Schedule schedule);
    void updateSchedule(Schedule schedule);
    Game findGame(Long id);
}
