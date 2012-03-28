package org.bpaye1.research.model.schedule;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.bpaye1.research.AbstractDatabaseTest;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.util.DateUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class SchedulePersistenceTest extends AbstractDatabaseTest {
	
	@Test
	public void persist_schedule() throws Exception {
		Schedule schedule = new Schedule("Fall 2012", "B-League");
		persist(schedule);
		
		Schedule persistedSchedule = getEm().find(Schedule.class, schedule.getId());
		assertThat(persistedSchedule.getDescription(), is(schedule.getDescription()));
		assertThat(persistedSchedule.getLeague(), is(schedule.getLeague()));
	}
	
	@Test
	public void addGameToSchedule() throws Exception {
		Schedule schedule = new Schedule("Fall 2012", "B-League");
		Game game1 = new Game(schedule, new LocalDate(2012, 9, 23), new LocalTime(21, 30), "Chiefs");
		Game game2 = new Game(schedule, new LocalDate(2012, 9, 27), new LocalTime(21, 30), "Flyers");
		persist(schedule);
		
		Schedule persistedSchedule = getEm().find(Schedule.class, schedule.getId());
		assertThat(persistedSchedule.getGames(), hasItem(game1));
		assertThat(persistedSchedule.getGames(), hasItem(game2));
	}
	
	@Test
	public void removeGameFromSchedule() throws Exception {
		Schedule schedule = new Schedule("Fall 2012", "B-League");
		Game game1 = new Game(schedule, new LocalDate(2012, 9, 23), new LocalTime(21, 30), "Chiefs");
		Game game2 = new Game(schedule, new LocalDate(2012, 9, 27), new LocalTime(21, 30), "Flyers");
		
		persist(schedule);
		
		Schedule persistedSchedule = getEm().find(Schedule.class, schedule.getId());
		assertThat(persistedSchedule.getGames(), hasItem(game1));
		assertThat(persistedSchedule.getGames(), hasItem(game2));
		
		persistedSchedule.removeGame(game2);
		
		getEm().flush();
		getEm().clear();
	
		persistedSchedule = getEm().find(Schedule.class, schedule.getId());
		assertThat(persistedSchedule.getGames(), hasItem(game1));
		assertThat(persistedSchedule.getGames(), not(hasItem(game2)));
	}
	
	@Test
	public void persistGame() throws Exception {
		Player joe = new Player("joe", "joe", DateUtils.newDate(1965,12,12), 88);
		Schedule schedule = new Schedule("Fall 2012", "B-League");
		Game game1 = new Game(schedule, new LocalDate(2012, 9, 23), new LocalTime(21, 30), "Chiefs");
		game1.setBeverageDutyPlayer(joe);
		game1.setHomeOrAway("AWAY");
		
		getEm().persist(joe);
		getEm().persist(schedule);
		getEm().flush();
		getEm().clear();
		
		Game persistedGame = getEm().find(Game.class, game1.getId());
		assertThat(persistedGame.getDate(), is(game1.getDate()));
		assertThat(persistedGame.getTime(), is(game1.getTime()));
		assertThat(persistedGame.getOpponent(), is(game1.getOpponent()));
		assertThat(persistedGame.getSchedule(), is(schedule));
		assertThat(persistedGame.getHomeOrAway(), is(game1.getHomeOrAway()));
		assertThat(persistedGame.getBeverageDutyPlayer(), is(joe));
	}
}
