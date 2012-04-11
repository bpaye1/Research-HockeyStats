package org.bpaye1.research.controller;

import org.bpaye1.research.controller.editor.LocalDateCustomEditor;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.player.Position;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.repository.StateRepository;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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

@Transactional
@Controller
@RequestMapping(value="/admin/players")
public class PlayerController{
	
	private PlayerRepository repository;
	private StateRepository stateRepository;
	
	protected PlayerController(){
	}
	
	@Inject
	public PlayerController(PlayerRepository repository,StateRepository stateRepository) {
		this.repository = repository;
		this.stateRepository = stateRepository;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("MM-dd-yyyy");
		binder.registerCustomEditor(LocalDate.class, new LocalDateCustomEditor(dateTimeFormat));
	}

	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String findPlayers(Model model){
		model.addAttribute("players", repository.findAll());
		return "players";
	}
	
	@RequestMapping(value="/player", method=RequestMethod.GET)
	public String addPlayer(Model model){
		model.addAttribute("player", new Player());
		model.addAttribute("positions", Position.values());
		model.addAttribute("states", stateRepository.findAll());
		return "player";
	}
	
	@RequestMapping(value="/player", method=RequestMethod.POST)
	public String addPlayer(@Valid Player player, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "player";
		}
		repository.add(player);
		return "redirect:/admin/players/";
	}

	@RequestMapping(value="/player/{id}", method=RequestMethod.GET)
	public String editPlayer(@PathVariable Long id, Model model){
		model.addAttribute("player", repository.find(Long.valueOf(id)));
		model.addAttribute("states", stateRepository.findAll());
		model.addAttribute("positions", Position.values());
		return "player";
	}
	
	@RequestMapping(value="/player/{id}", method=RequestMethod.POST)
	public String editPlayer(@Valid Player player, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "player";
		}
		repository.update(player);
		return "redirect:/admin/players/";
	}
}
	