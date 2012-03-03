package org.bpaye1.research.controller;

import javax.validation.Valid;

import org.bpaye1.research.model.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/player")
public class PlayerController {
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model){
		
		model.addAttribute("player", new Player());
		return "player";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String add(@Valid Player player, BindingResult bindingResult){
		System.out.println("IN add");
		if(bindingResult.hasErrors()){
			System.out.println("error");
			return "player";
		}
		return "home";
	}

}
	