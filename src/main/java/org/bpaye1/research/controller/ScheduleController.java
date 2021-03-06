package org.bpaye1.research.controller;

import org.bpaye1.research.controller.editor.CustomEditorFactory;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.schedule.game.Game;
import org.bpaye1.research.model.schedule.HomeOrAway;
import org.bpaye1.research.model.schedule.Schedule;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.service.ScheduleService;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Transactional
@Controller
@RequestMapping(value="/admin/schedules")
@SessionAttributes({"game", "homeOrAway", "players"})
public class ScheduleController {
    private ScheduleService service;
    private PlayerRepository playerRepository;
    private CustomEditorFactory customEditorFactory;

    protected ScheduleController(){
    }

    @Inject
    public ScheduleController(ScheduleService service, PlayerRepository playerRepository,
                              CustomEditorFactory customEditorFactory) {
        this.service = service;
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
		model.addAttribute("schedules", service.findAllSchedules());
		return "schedules";
	}

	@RequestMapping(value="/schedule/{id}", method=RequestMethod.GET)
	public String findSchedule(@PathVariable Integer id, Model model){
        model.addAttribute("schedule", service.findSchedule(id));
		return "schedule";
	}

	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public String newSchedule(Model model){
		model.addAttribute("schedule", new Schedule());
		return "new-schedule";
	}

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public String saveNewSchedule(@Valid Schedule schedule, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "new-schedule";
        }
        service.addSchedule(schedule);
        return "redirect:/admin/schedules/";
    }

    @RequestMapping(value="schedule/{id}/game", method = RequestMethod.GET)
    public String newGame(@PathVariable("id") Integer scheduleId, Model model){
        Schedule schedule = service.findSchedule(scheduleId);
        model.addAttribute("players", playerRepository.findAllActive());
        model.addAttribute("homeOrAway", HomeOrAway.values());
        model.addAttribute("game", new Game(schedule));
        return "schedule-game";
    }

    @RequestMapping(value="schedule/{id}/game", method = RequestMethod.POST)
    public String saveNewGame(@PathVariable("id") Integer scheduleId, @Valid Game game, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "schedule-game";
        }
        Schedule schedule = service.findSchedule(scheduleId);
        schedule.addGame(game);
        service.updateSchedule(schedule);
        return "redirect:/admin/schedules/schedule/" + scheduleId + "/";
    }

    @RequestMapping(value = "schedule/{scheduleId}/game/{id}", method = RequestMethod.GET)
    public  String editGame(@PathVariable Integer scheduleId, @PathVariable Long id, Model model){
        Schedule schedule = service.findSchedule(scheduleId);
        model.addAttribute("players", playerRepository.findAllActive());
        model.addAttribute("homeOrAway", HomeOrAway.values());
        model.addAttribute("game", schedule.findGame(id));
        return "schedule-game";
    }

    @RequestMapping(value = "schedule/{scheduleId}/game/{id}", method = RequestMethod.POST)
    public String editGame(@Valid Game game, BindingResult bindingResult, @PathVariable Integer scheduleId){
        if(bindingResult.hasErrors()){
            return "schedule-game";
        }
        service.updateSchedule(game.getSchedule());
        return "redirect:/admin/schedules/schedule/" +  scheduleId;
    }

    @RequestMapping(value = "schedule/{scheduleId}/game/{id}/result", method = RequestMethod.GET)
    public String editGameResults(@PathVariable Long id, Model model){
        Game game = service.findGame(id);
        model.addAttribute("game", game);
        return "schedule-game-result";
    }

    @RequestMapping(value = "schedule/{scheduleId}/game/{id}/result", method = RequestMethod.POST)
    public String editGameResults(@Valid Game game, BindingResult bindingResult, @PathVariable Integer scheduleId){
        if(bindingResult.hasErrors()){
            return "schedule-game-result";
        }
        service.updateSchedule(game.getSchedule());
        return "redirect:/admin/schedules/schedule/" +  scheduleId;
    }
}
