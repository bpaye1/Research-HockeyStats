package org.bpaye1.research.controller;

import org.bpaye1.research.model.schedule.Schedule;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/admin")
@Transactional
@Controller
public class ScheduleController {
	
	@RequestMapping(value="/schedules", method=RequestMethod.GET)
	public String findSchedules(Model model){
		model.addAttribute("schedules", null);
		return "schedules";
	}
	
	@RequestMapping(value="/schedules/schedule/{id}", method=RequestMethod.GET)
	public String findSchedule(@PathVariable Integer id, Model model){
		return "schedule";
	}
	
	@RequestMapping(value="/schedules/schedule", method=RequestMethod.GET)
	public String newSchedule(Model model){
		model.addAttribute("schedule", new Schedule());
		return "new-schedule";
	}

}
