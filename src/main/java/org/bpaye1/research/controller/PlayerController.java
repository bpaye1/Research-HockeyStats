package org.bpaye1.research.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.bpaye1.research.model.Player;
import org.bpaye1.research.repository.PlayerRepository;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlayerController {
	
	@Inject
	PlayerRepository repository;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="/players", method=RequestMethod.GET)
	public String players(Model model){
		model.addAttribute("players", repository.list());
		return "players";
	}
	
	@RequestMapping(value="/player", method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("player", new Player());
		return "player";
	}
	
	@RequestMapping(value="/player", method=RequestMethod.POST)
	public String add(@Valid Player player, BindingResult bindingResult){
		
		System.out.println(bindingResult.getErrorCount());
		System.out.println(bindingResult.getAllErrors().get(0).toString());

		if(bindingResult.hasErrors()){
			System.out.println("error");
			return "player";
		}
		
		repository.add(player);
		
		return "redirect:/players/";
	}

}
	