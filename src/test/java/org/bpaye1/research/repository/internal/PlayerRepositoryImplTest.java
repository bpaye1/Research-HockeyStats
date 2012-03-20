package org.bpaye1.research.repository.internal;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.bpaye1.research.AbstractDatabaseTest;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.util.DateUtils;
import org.junit.Test;

public class PlayerRepositoryImplTest extends AbstractDatabaseTest {
	
	@Inject
	private PlayerRepository repository;

	@Test
	public void add_shouldPersistPlayer() throws Exception {
		Player player = new Player("Richard", "Maurice", DateUtils.newDate(1945, 12, 12), 9);
		repository.add(player);
		assertThat(repository.find(player.getId()), is(player));
	}
	
	@Test
	public void update() throws Exception {
		Player player = repository.add(new Player("Richard", "Maurice", DateUtils.newDate(1945, 12, 12), 9));
		assertThat(player.getFirstName(), is("Maurice"));
		
		player.setFirstName("Moe");
		
		repository.update(player);
		
		Player updatedPlayer = repository.find(player.getId());
		assertThat(updatedPlayer.getFirstName(), is("Moe"));
	}
	
	@Test
	public void list() throws Exception {
		Player maurice = repository.add(new Player("Richard", "Maurice", DateUtils.newDate(1945, 12, 12), 9));
		Player henry = repository.add(new Player("Henry", "Maurice", DateUtils.newDate(1945, 12, 12), 12));
		
		List<Player> players = repository.list();
		assertThat(players, hasItem(maurice));
		assertThat(players, hasItem(henry));
	}
}
