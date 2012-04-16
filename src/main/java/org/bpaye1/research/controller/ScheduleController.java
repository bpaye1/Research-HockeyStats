package org.bpaye1.research.controller;

import org.bpaye1.research.controller.editor.CustomEditorFactory;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.schedule.Game;
import org.bpaye1.research.model.schedule.HomeOrAway;
import org.bpaye1.research.model.schedule.Schedule;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.repository.ScheduleRepository;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping(value="/admin/schedules")
@Transactional
@Controller
public class ScheduleController {

    private ScheduleRepository repository;
    private PlayerRepository playerRepository;
    private CustomEditorFactory customEditorFactory;

    protected ScheduleController(){
    }

    @Inject
    public ScheduleController(ScheduleRepository repository, PlayerRepository playerRepository,
                              CustomEditorFactory customEditorFactory) {
        this.repository = repository;
        this.playerRepository = playerRepository;
        this.customEditorFactory = customEditorFactory;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, customEditorFactory.createLocalDateCustomEditor());
        binder.registerCustomEditor(LocalTime.class, customEditorFactory.createLocalTimeCustomEditor());
        binder.registerCustomEditor(Player.class,  customEditorFactory.createPlayerCustomEditor(playerRepository));
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
    public String newGame(Model model){
        model.addAttribute("players", playerRepository.findAllActive());
        model.addAttribute("homeOrAway", HomeOrAway.values());
        model.addAttribute("game", new Game());
        return "new-schedule-game";
    }

    @RequestMapping(value="schedule/{id}/game", method = RequestMethod.POST)
    public String saveNewGame(@PathVariable("id") Integer scheduleId, @Valid Game game, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "new-schedule-game";
        }
        Schedule schedule = repository.find(scheduleId);
        schedule.addGame(game);
        repository.update(schedule);
        return "redirect:/schedule/" + scheduleId + "/";
    }
}
