package org.bpaye1.research.model.schedule;

import org.apache.commons.lang.StringUtils;
import org.bpaye1.research.model.player.Player;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameTest {

    @Test
    public void addPlayerGameResult() throws Exception {
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
    public void getDescription() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(5);
        game.setOpponentTeamScore(3);
        assertThat(game.getDescription(), is("5 - 3"));
    }

    @Test
    public void getDescription_shouldReturnEmptyStringWhenTeamScoreIsNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(null);
        game.setOpponentTeamScore(3);
        assertThat(game.getDescription(), is(StringUtils.EMPTY));
    }

    @Test
    public void getDescription_shouldReturnEmptyStringWhenOpponentTeamScoreIsNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(5);
        game.setOpponentTeamScore(null);
        assertThat(game.getDescription(), is(StringUtils.EMPTY));
    }

    @Test
    public void getDescription_shouldReturnEmptyStringWhenBothScoresAreNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        game.setTeamScore(null);
        game.setOpponentTeamScore(null);
        assertThat(game.getDescription(), is(StringUtils.EMPTY));
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
}
