package org.bpaye1.research.model.schedule;

import org.apache.commons.lang.StringUtils;
import org.bpaye1.research.model.player.Player;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameResultTest {

    @Test
    public void addPlayerGameResult() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);

        Player player = new Player("Richard", "Maurice", new LocalDate(1945, 12, 12), 9);

        PlayerGameResult playerGameResult = new PlayerGameResult(player);
        playerGameResult.setGoals(1);
        playerGameResult.setAssists(0);
        playerGameResult.setPenaltyMinutes(4);

        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(1);
        gameResult.setOpponentTeamScore(0);
        gameResult.addPlayerGameResult(playerGameResult);

        assertThat(gameResult.getPlayerGameResults(), hasItem(playerGameResult));
    }

    @Test
    public void getDescription() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(5);
        gameResult.setOpponentTeamScore(3);
        assertThat(gameResult.getDescription(), is("5 - 3"));
    }

    @Test
    public void getDescription_shouldReturnEmptyStringWhenTeamScoreIsNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(null);
        gameResult.setOpponentTeamScore(3);
        assertThat(gameResult.getDescription(), is(StringUtils.EMPTY));
    }

    @Test
    public void getDescription_shouldReturnEmptyStringWhenOpponentTeamScoreIsNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(5);
        gameResult.setOpponentTeamScore(null);
        assertThat(gameResult.getDescription(), is(StringUtils.EMPTY));
    }

    @Test
    public void getDescription_shouldReturnEmptyStringWhenBothScoresAreNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(null);
        gameResult.setOpponentTeamScore(null);
        assertThat(gameResult.getDescription(), is(StringUtils.EMPTY));
    }

    @Test
    public void isGameTied() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(4);
        gameResult.setOpponentTeamScore(4);
        assertThat(gameResult.isGameTied(), is(true));
    }

    @Test
    public void isGameTied_shouldReturnFalseWhenScoresNotIdentical() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(4);
        gameResult.setOpponentTeamScore(5);
        assertThat(gameResult.isGameTied(), is(false));
    }

    @Test
    public void isGameWon() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(6);
        gameResult.setOpponentTeamScore(4);
        assertThat(gameResult.isGameWon(), is(true));
    }

    @Test
    public void isGameWon_shouldReturnFalseWhenOpponentTeamScoreIsGreaterThanTeamScore() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(2);
        gameResult.setOpponentTeamScore(4);
        assertThat(gameResult.isGameWon(), is(false));
    }

    @Test
    public void isGameLost() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(1);
        gameResult.setOpponentTeamScore(4);
        assertThat(gameResult.isGameLost(), is(true));
    }

    @Test
    public void isGameLost_shouldReturnFalseWhenOpponentTeamScoreIsLessThanTeamScore() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(2);
        gameResult.setOpponentTeamScore(1);
        assertThat(gameResult.isGameLost(), is(false));
    }

    @Test
    public void isGamePlayed() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(2);
        gameResult.setOpponentTeamScore(1);
        assertThat(gameResult.isGamePlayed(), is(true));
    }

    @Test
    public void isGamePlayed_shouldReturnFalseWhenTeamScoreIsNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(null);
        gameResult.setOpponentTeamScore(1);
        assertThat(gameResult.isGamePlayed(), is(false));
    }

    @Test
    public void isGamePlayed_shouldReturnFalseWhenOpponentTeamScoreIsNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(3);
        gameResult.setOpponentTeamScore(null);
        assertThat(gameResult.isGamePlayed(), is(false));
    }

    @Test
    public void isGamePlayed_shouldReturnFalseWhenBothTeamScoresAreNull() throws Exception {
        Schedule spring2012 = new Schedule("Spring 2012", "A-League");
        Game game = new Game(spring2012);
        GameResult gameResult = new GameResult(game);
        gameResult.setTeamScore(null);
        gameResult.setOpponentTeamScore(null);
        assertThat(gameResult.isGamePlayed(), is(false));
    }
}
