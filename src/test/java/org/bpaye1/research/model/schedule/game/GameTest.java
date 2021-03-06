package org.bpaye1.research.model.schedule.game;

import com.google.common.collect.Lists;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.player.Position;
import org.bpaye1.research.model.schedule.Schedule;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

//TODO Cleanup tests
public class GameTest {

    @Test
    public void addPlayerGameStats() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Player player = new Player("Richard", "Maurice", new LocalDate(1945, 12, 12), 9);

        Game game = new Game(spring2012);
        game.setOpponentTeamScore(0);

        PlayerGameStats playerGameResult = new PlayerGameStats(game, player);
        playerGameResult.setGoals(1);
        playerGameResult.setAssists(0);
        playerGameResult.setPenaltyMinutes(4);
        game.addPlayerGameStats(playerGameResult);

        assertThat(game.getGameStats(), hasItem(playerGameResult));
    }

    @Test
    public void initializeGameStats() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Player player = new Player("Richard", "Maurice", new LocalDate(1945, 12, 12), 9);
        player.setPosition(Position.CENTER);

        Player goalie = new Player("Dryden", "Ken", new LocalDate(1944, 12, 22), 1);
        goalie.setPosition(Position.GOALIE);

        Game game = new Game(spring2012);
        game.initializeGameStats(Lists.newArrayList(player, goalie));
        assertThat(game.getGameStats(), hasItem(new PlayerGameStats(game, player)));
        assertThat(game.getGoalieGameStats(), hasItem(new GoalieGameStats(game, player)));
    }


    @Test
    public void getDescription() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(5);
        game.setOpponentTeamScore(3);
        assertThat(game.getDescription(), is("W 5 - 3"));
    }

    @Test
    public void getDescription_shouldReturnEditWhenTeamScoreIsNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(null);
        game.setOpponentTeamScore(3);
        assertThat(game.getDescription(), is("Edit"));
    }

    @Test
    public void getDescription_shouldReturnEditStringWhenOpponentTeamScoreIsNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(5);
        game.setOpponentTeamScore(null);
        assertThat(game.getDescription(), is("Edit"));
    }

    @Test
    public void getDescription_shouldReturnEmptyStringWhenBothScoresAreNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(null);
        game.setOpponentTeamScore(null);
        assertThat(game.getDescription(), is("Edit"));
    }

    @Test
    public void isGameTied() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(4);
        game.setOpponentTeamScore(4);
        assertThat(game.isGameTied(), is(true));
    }

    @Test
    public void isGameTied_shouldReturnFalseWhenScoresNotIdentical() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(4);
        game.setOpponentTeamScore(5);
        assertThat(game.isGameTied(), is(false));
    }

    @Test
    public void isGameWon() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(6);
        game.setOpponentTeamScore(4);
        assertThat(game.isGameWon(), is(true));
    }

    @Test
    public void isGameWon_shouldReturnFalseWhenOpponentTeamScoreIsGreaterThanTeamScore() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(2);
        game.setOpponentTeamScore(4);
        assertThat(game.isGameWon(), is(false));
    }

    @Test
    public void isGameLost() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(1);
        game.setOpponentTeamScore(4);
        assertThat(game.isGameLost(), is(true));
    }

    @Test
    public void isGameLost_shouldReturnFalseWhenOpponentTeamScoreIsLessThanTeamScore() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(2);
        game.setOpponentTeamScore(1);
        assertThat(game.isGameLost(), is(false));
    }

    @Test
    public void isGamePlayed() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(2);
        game.setOpponentTeamScore(1);
        assertThat(game.isGamePlayed(), is(true));
    }

    @Test
    public void isGamePlayed_shouldReturnFalseWhenTeamScoreIsNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(null);
        game.setOpponentTeamScore(1);
        assertThat(game.isGamePlayed(), is(false));
    }

    @Test
    public void isGamePlayed_shouldReturnFalseWhenOpponentTeamScoreIsNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(3);
        game.setOpponentTeamScore(null);
        assertThat(game.isGamePlayed(), is(false));
    }

    @Test
    public void isGamePlayed_shouldReturnFalseWhenBothTeamScoresAreNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(null);
        game.setOpponentTeamScore(null);
        assertThat(game.isGamePlayed(), is(false));
    }

    @Test
    public void hasNoGameStats() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        assertThat(game.hasNoGameStats(), is(true));
    }

    @Test
    public void hasNoGameStats_returnFalseWhenGameStatsExist() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.addPlayerGameStats(new PlayerGameStats(game, new Player()));
        assertThat(game.hasNoGameStats(), is(false));
    }
}
