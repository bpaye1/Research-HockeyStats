package org.bpaye1.research.model;

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
		player.setPhoneNumber(9992225555L);
		
		Address address = new Address();
		address.setAddress("232 some street");
		address.setCity("some city");
		address.setState("some state");
		address.setZipCode("some zip code");
		
		player.setAddress(address);
		
		getEm().persist(player);
		getEm().flush();
		getEm().clear();
	}
	
	private Date newDate(int year, int month, int date){
		calendar.set(year, month, date);
		return calendar.getTime();
	}
}
