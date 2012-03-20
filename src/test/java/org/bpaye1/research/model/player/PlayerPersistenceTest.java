package org.bpaye1.research.model.player;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.bpaye1.research.AbstractDatabaseTest;
import org.bpaye1.research.model.player.Address;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.player.Position;
import org.bpaye1.research.model.player.Status;
import org.bpaye1.research.util.DateUtils;
import org.junit.Test;

public class PlayerPersistenceTest extends AbstractDatabaseTest{
	
	@Test
	public void persist_Player() throws Exception {
		Player player = new Player();
		player.setFirstName("Wayne");
		player.setLastName("Gretzky");
		player.setEmail("wayne-gretzky@somemail.com");
		player.setJerseyNumber(99);
		player.setDateOfBirth(DateUtils.newDate(1965,12,12));
		player.setPhoneNumber("9992225555");
		player.setStatus(Status.ACTIVE);
		player.setPosition(Position.CENTER);
		player.setHomeTown("Some City");
		
		Address address = new Address();
		address.setAddress("232 some street");
		address.setCity("some city");
		address.setState("TX");
		address.setZipCode("some zip code");
		
		player.setAddress(address);
		
		getEm().persist(player);
		getEm().flush();
		getEm().clear();
		
		Player persistedPlayer = getEm().find(Player.class, player.getId());
		assertThat(persistedPlayer.getFirstName(), is(player.getFirstName()));
		assertThat(persistedPlayer.getLastName(), is(player.getLastName()));
		assertThat(persistedPlayer.getEmail(), is(player.getEmail()));
		assertThat(persistedPlayer.getJerseyNumber(), is(player.getJerseyNumber()));
		assertThat(persistedPlayer.getPhoneNumber(), is(player.getPhoneNumber()));
		assertThat(persistedPlayer.getStatus(), is(player.getStatus()));
		assertThat(persistedPlayer.getHomeTown(), is(player.getHomeTown()));
		assertThat(persistedPlayer.getPosition(), is(player.getPosition()));
		assertThat(persistedPlayer.getAddress().getAddress(), is(address.getAddress()));
		assertThat(persistedPlayer.getAddress().getCity(), is(address.getCity()));
		assertThat(persistedPlayer.getAddress().getState(), is(address.getState()));
		assertThat(persistedPlayer.getAddress().getZipCode(), is(address.getZipCode()));
		
	}
	
	
}
