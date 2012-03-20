package org.bpaye1.research.model.schedule;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class ScheduleTest {

	@Test
	public void addGame_usingSeason() throws Exception {
		Schedule fall2012 = new Schedule("Fall 2012", "B-League");
		Game game1 = new Game(fall2012, new LocalDate(2012, 9, 23), new LocalTime(21, 30), "Chiefs");
		assertThat(fall2012.getGames(), hasItem(game1));
	}
	
	@Test
	public void removeGame() throws Exception {
		Schedule fall2012 = new Schedule("Fall 2012", "B-League");
		Game game1 = new Game(fall2012, new LocalDate(2012, 9, 23), new LocalTime(21, 30), "Chiefs");
		assertThat(fall2012.getGames(), hasItem(game1));
		
		fall2012.removeGame(game1);
		assertThat(fall2012.getGames(), not(hasItem(game1)));
	}
}
