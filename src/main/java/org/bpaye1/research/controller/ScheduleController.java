package org.bpaye1.research.controller;

import org.bpaye1.research.model.schedule.Schedule;
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

    @Inject
    ScheduleRepository repository;

	@RequestMapping(value = "", method=RequestMethod.GET)
	public String findSchedules(Model model){
		model.addAttribute("schedules", repository.findAll());
		return "schedules";
	}
	
	@RequestMapping(value="/schedule/{id}", method=RequestMethod.GET)
	public String findSchedule(@PathVariable Integer id, Model model){
		return "schedule";
	}
	
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public String newSchedule(Model model){
		model.addAttribute("schedule", new Schedule());
		return "new-schedule";
	}

}
