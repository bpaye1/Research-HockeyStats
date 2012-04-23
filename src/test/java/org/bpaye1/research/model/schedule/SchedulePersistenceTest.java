package org.bpaye1.research.model.schedule;

import org.bpaye1.research.AbstractDatabaseTest;
import org.bpaye1.research.model.player.Player;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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
		Game game1 = new Game(schedule, new LocalDate(2012, 9, 23),
                new LocalTime(21, 30), "Chiefs", HomeOrAway.AWAY, "Random Arena");
		Game game2 = new Game(schedule, new LocalDate(2012, 9, 27),
                new LocalTime(21, 30), "Flyers", HomeOrAway.HOME, "Random Arena");
		persist(schedule);
		
		Schedule persistedSchedule = getEm().find(Schedule.class, schedule.getId());
		assertThat(persistedSchedule.getGames(), hasItem(game1));
		assertThat(persistedSchedule.getGames(), hasItem(game2));
	}
	
	@Test
	public void removeGameFromSchedule() throws Exception {
		Schedule schedule = new Schedule("Fall 2012", "B-League");
		Game game1 = new Game(schedule, new LocalDate(2012, 9, 23),
                new LocalTime(21, 30), "Chiefs", HomeOrAway.HOME, "Random Arena");
		Game game2 = new Game(schedule, new LocalDate(2012, 9, 27),
                new LocalTime(21, 30), "Flyers", HomeOrAway.AWAY, "Random Arena");
		
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
		Player joe = new Player("joe", "joe", new LocalDate(1965,12,12), 88);
		Schedule schedule = new Schedule("Fall 2012", "B-League");
		Game game1 = new Game(schedule, new LocalDate(2012, 9, 23),
                new LocalTime(21, 30), "Chiefs", HomeOrAway.AWAY, "Random Arena");
		game1.setBeverageDutyPlayer(joe);
        game1.setTeamScore(5);
        game1.setOpponentTeamScore(3);

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
        assertThat(persistedGame.getLocation(), is(game1.getLocation()));
        assertThat(persistedGame.getTeamScore(), is(game1.getTeamScore()));
        assertThat(persistedGame.getOpponentTeamScore(), is(game1.getOpponentTeamScore()));
	}

    @Test
    public void persistPlayerGameStats() throws Exception {
        Player joe = new Player("joe", "joe", new LocalDate(1965,12,12), 88);
        Schedule schedule = new Schedule("Fall 2012", "B-League");
        Game game1 = new Game(schedule, new LocalDate(2012, 9, 23),
                new LocalTime(21, 30), "Chiefs", HomeOrAway.AWAY, "Random Arena");

        getEm().persist(joe);
        getEm().persist(schedule);
        getEm().flush();
        getEm().clear();

        Game persistedGame = getEm().find(Game.class, game1.getId());
        PlayerGameStats gameStatsForJoe = new PlayerGameStats(persistedGame, joe);
        gameStatsForJoe.setGoals(2);
        gameStatsForJoe.setAssists(1);
        gameStatsForJoe.setPenaltyMinutes(4);
        persistedGame.setTeamScore(5);
        persistedGame.setOpponentTeamScore(3);
        persistedGame.addPlayerGameStats(gameStatsForJoe);

        getEm().flush();
        getEm().clear();

        persistedGame = getEm().find(Game.class, game1.getId());
        joe = getEm().find(Player.class, joe.getId());

        assertThat(persistedGame.getTeamScore(), is(5));
        assertThat(persistedGame.getOpponentTeamScore(), is(3));
        assertThat(persistedGame.getGameStats(), hasItem(gameStatsForJoe));
        PlayerGameStats playerGameStats = persistedGame.getGameStats().get(0);
        assertThat(playerGameStats.getGoals(), is(2));
        assertThat(playerGameStats.getAssists(), is(1));
        assertThat(playerGameStats.getPenaltyMinutes(), is(4));
        assertThat(playerGameStats.getPlayer(), is(joe));
    }
}
