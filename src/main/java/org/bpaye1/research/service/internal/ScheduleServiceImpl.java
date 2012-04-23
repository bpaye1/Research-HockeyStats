package org.bpaye1.research.service.internal;

import org.bpaye1.research.model.schedule.Game;
import org.bpaye1.research.model.schedule.Schedule;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.repository.ScheduleRepository;
import org.bpaye1.research.service.ScheduleService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private ScheduleRepository repository;
    private PlayerRepository playerRepository;

    @Inject
    public ScheduleServiceImpl(ScheduleRepository repository, PlayerRepository playerRepository){
        this.repository = repository;
        this.playerRepository = playerRepository;
    }

    public Schedule findSchedule(Integer id) {
        return repository.find(id);
    }

    public List<Schedule> findAllSchedules() {
        return repository.findAll();
    }

    public void addSchedule(Schedule schedule) {
        //TODO Add check for duplicate schedule logic
        repository.add(schedule);
    }

    public  void updateSchedule(Schedule schedule){
        //TODO Add check for duplicate schedule logic
        repository.update(schedule);
    }

    public Game findGame(Long id) {
        Game game = repository.findGame(id);
        if(game.hasNoGameStats()){
            game.initializeGameStats(playerRepository.findAllActive());
        }
        return game;
    }
}
