package org.bpaye1.research.controller;

import org.bpaye1.research.controller.editor.CustomEditorFactory;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.player.Position;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.repository.StateRepository;
import org.joda.time.LocalDate;
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
@RequestMapping(value="/admin/players")
@SessionAttributes({"states", "positions"})
public class    PlayerController{
	
	private PlayerRepository repository;
	private StateRepository stateRepository;
    private CustomEditorFactory customEditorFactory;
	
	protected PlayerController(){
	}
	
	@Inject
	public PlayerController(PlayerRepository repository, StateRepository stateRepository,
                                CustomEditorFactory customEditorFactory) {
		this.repository = repository;
		this.stateRepository = stateRepository;
        this.customEditorFactory = customEditorFactory;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, customEditorFactory.createLocalDateCustomEditor());
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
	public String addPlayer(@Valid Player player, BindingResult bindingResult, Model model){
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
	