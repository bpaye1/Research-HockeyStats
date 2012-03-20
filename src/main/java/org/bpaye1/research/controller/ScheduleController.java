package org.bpaye1.research.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/schedules")
@Transactional
@Controller
public class ScheduleController {
	
	@RequestMapping(value="/players", method=RequestMethod.GET)
	public String findSchedules(Model model){
		model.addAttribute("schedules", null);
		return "schedules";
	}

}
