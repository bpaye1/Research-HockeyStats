package org.bpaye1.research.controller;

import com.google.common.collect.Lists;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.repository.StateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerControllerTest {

	@Mock
	PlayerRepository playerRepository;
	
	@Mock
	StateRepository stateRepository;
	
	@Mock
	BindingResult bindResult;
	
	PlayerController controller;
	
	@Before
	public void setUp(){
		controller = new PlayerController(playerRepository, stateRepository);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void findPlayers() throws Exception {
		Player joe = new Player("Howard", "joe", new Date(), 12);
		Player ben = new Player("ben", "cool", new Date(), 14);
		List<Player> players = Lists.newArrayList(ben, joe);
		
		when(playerRepository.findAll()).thenReturn(players);
		
		Model model = new ExtendedModelMap();
		String viewName = controller.findPlayers(model);
		assertThat(viewName, is("players"));
		
		List<Player> playersInModel = (List<Player>)model.asMap().get("players");
		assertThat(playersInModel, hasItem(joe));
		assertThat(playersInModel, hasItem(ben));
	}
	
	@Test
	public void addPlayer_initialize() throws Exception {
		Model model = new ExtendedModelMap();
		String viewName = controller.addPlayer(model);
		assertThat(viewName, is("player"));
		
		assertThat(model.containsAttribute("player"), is(true));
		assertThat(model.containsAttribute("positions"), is(true));
		assertThat(model.containsAttribute("states"), is(true));
	}
	
	@Test
	public void addPlayer_saveWhenNoBindingErrorsExist() throws Exception {
		Player joe = new Player("Howard", "joe", new Date(), 12);
		
		when(bindResult.hasErrors()).thenReturn(false);
		
		String viewName = controller.addPlayer(joe, bindResult);
		assertThat(viewName, is("redirect:/players/"));
		
		ArgumentCaptor<Player> argument = ArgumentCaptor.forClass(Player.class);
		verify(playerRepository).add(argument.capture());
		assertThat(argument.getValue(), is(joe));
	}
	
	@Test
	public void addPlayer_saveWhenBindingErrorsExist() throws Exception {
		Player joe = new Player("Howard", "joe", new Date(), 12);
		
		when(bindResult.hasErrors()).thenReturn(true);
		
		String viewName = controller.addPlayer(joe, bindResult);
		assertThat(viewName, is("player"));
		
		verify(playerRepository, times(0)).add(joe);
	}
	
	@Test
	public void editPlayer_initialize() throws Exception {
		Player ben = new Player("ben", "cool", new Date(), 14);
		
		when(playerRepository.find(anyLong())).thenReturn(ben);
		
		Model model = new ExtendedModelMap();
		String viewName = controller.editPlayer(1L, model);
		assertThat(viewName, is("player"));
		assertThat((Player)model.asMap().get("player"), is(ben));
		assertThat(model.containsAttribute("positions"), is(true));
		assertThat(model.containsAttribute("states"), is(true));
	}
	
	@Test
	public void editPlayer_saveWhenNoBindingErrorsExist() throws Exception {
		Player joe = new Player("Howard", "joe", new Date(), 12);
		
		when(bindResult.hasErrors()).thenReturn(false);
		
		String viewName = controller.editPlayer(joe, bindResult);
		assertThat(viewName, is("redirect:/players/"));
		
		ArgumentCaptor<Player> argument = ArgumentCaptor.forClass(Player.class);
		verify(playerRepository).update(argument.capture());
		assertThat(argument.getValue(), is(joe));
	}
	
	@Test
	public void editPlayer_saveWhenBindingErrorsExist() throws Exception {
		Player joe = new Player("Howard", "joe", new Date(), 12);
		
		when(bindResult.hasErrors()).thenReturn(true);
		
		String viewName = controller.editPlayer(joe, bindResult);
		assertThat(viewName, is("player"));
		
		verify(playerRepository, times(0)).update(joe);
	}
}
