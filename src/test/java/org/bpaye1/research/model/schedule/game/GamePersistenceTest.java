package org.bpaye1.research.model.schedule.game;

import org.bpaye1.research.AbstractDatabaseTest;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.schedule.HomeOrAway;
import org.bpaye1.research.model.schedule.Schedule;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GamePersistenceTest extends AbstractDatabaseTest{

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

    @Test
    public void persistGoalieGameStats() throws Exception {
        Player goalie = new Player("joe", "joe", new LocalDate(1965,12,12), 88);
        Schedule schedule = new Schedule("Fall 2012", "B-League");
        Game game1 = new Game(schedule, new LocalDate(2012, 9, 23),
                new LocalTime(21, 30), "Chiefs", HomeOrAway.AWAY, "Random Arena");

        getEm().persist(goalie);
        getEm().persist(schedule);
        getEm().flush();
        getEm().clear();

        Game persistedGame = getEm().find(Game.class, game1.getId());
        GoalieGameStats goalieGameStats = new GoalieGameStats(persistedGame, goalie);
        goalieGameStats.setGoalsAgainst(2);
        goalieGameStats.setShotsOnGoals(20);

        persistedGame.setTeamScore(5);
        persistedGame.setOpponentTeamScore(3);
        persistedGame.addGoalieGameStats(goalieGameStats);

        getEm().flush();
        getEm().clear();

        persistedGame = getEm().find(Game.class, game1.getId());
        goalie = getEm().find(Player.class, goalie.getId());

        assertThat(persistedGame.getTeamScore(), is(5));
        assertThat(persistedGame.getOpponentTeamScore(), is(3));
        assertThat(persistedGame.getGoalieGameStats(), hasItem(goalieGameStats));
        GoalieGameStats playerGameStats = persistedGame.getGoalieGameStats().get(0);
        assertThat(playerGameStats.getShotsOnGoals(), is(20));
        assertThat(playerGameStats.getGoalsAgainst(), is(2));
        assertThat(playerGameStats.getPlayer(), is(goalie));
    }
}
