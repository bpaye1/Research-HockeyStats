package org.bpaye1.research.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.bpaye1.research.AbstractDatabaseTest;
import org.junit.Test;

public class PlayerPersistenceTest extends AbstractDatabaseTest{
	
	private Calendar calendar = Calendar.getInstance();

	@Test
	public void persist_Player() throws Exception {
		Player player = new Player();
		player.setFirstName("Wayne");
		player.setLastName("Gretzky");
		player.setEmail("wayne-gretzky@somemail.com");
		player.setJerseyNumber(99);
		player.setDateOfBirth(newDate(1965,12,12));
		player.setPhoneNumber("9992225555");
		player.setStatus("ACTV");
		
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
		assertThat(persistedPlayer.getAddress().getAddress(), is(address.getAddress()));
		assertThat(persistedPlayer.getAddress().getCity(), is(address.getCity()));
		assertThat(persistedPlayer.getAddress().getState(), is(address.getState()));
		assertThat(persistedPlayer.getAddress().getZipCode(), is(address.getZipCode()));
	}
	
	private Date newDate(int year, int month, int date){
		calendar.set(year, month, date);
		return calendar.getTime();
	}
}
