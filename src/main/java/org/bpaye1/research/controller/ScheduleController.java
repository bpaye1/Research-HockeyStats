package org.bpaye1.research.controller;

import org.bpaye1.research.model.schedule.Game;
import org.bpaye1.research.model.schedule.HomeOrAway;
import org.bpaye1.research.model.schedule.Schedule;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.repository.ScheduleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@RequestMapping(value="/admin/schedules")
@Transactional
@Controller
public class ScheduleController {

    private ScheduleRepository repository;
    private PlayerRepository playerRepository;

    protected ScheduleController(){
    }

    @Inject
    public ScheduleController(ScheduleRepository repository, PlayerRepository playerRepository) {
        this.repository = repository;
        this.playerRepository = playerRepository;
    }

    @RequestMapping(value = "", method=RequestMethod.GET)
	public String findSchedules(Model model){
		model.addAttribute("schedules", repository.findAll());
		return "schedules";
	}
	
	@RequestMapping(value="/schedule/{id}", method=RequestMethod.GET)
	public String findSchedule(@PathVariable Integer id, Model model){
        model.addAttribute("schedule", repository.find(id));
		return "schedule";
	}
	
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public String newSchedule(Model model){
		model.addAttribute("schedule", new Schedule());
		return "new-schedule";
	}

    @RequestMapping(value="schedule/{id}/game", method = RequestMethod.GET)
    public String newGame(@PathVariable Integer id, Model model){
        Schedule schedule = repository.find(id);
        model.addAttribute("players", playerRepository.findAllActive());
        model.addAttribute("homeOrAway", HomeOrAway.values());
        model.addAttribute("game", new Game(schedule));
        return "new-schedule-game";
    }

    @RequestMapping(value="schedule/{id}/game", method = RequestMethod.POST)
    public String saveNewGame(Game game){
        repository.update(game.getSchedule());
        return "redirect:/schedule/" + game.getSchedule().getId() + "/";    }

}
