package org.bpaye1.research.model.schedule;

import org.bpaye1.research.model.schedule.game.Game;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class ScheduleTest {

	@Test
	public void addGame_usingSeason() throws Exception {
		Schedule fall2012 = new Schedule("Fall 2012", "B-League");
        Game game1 = new Game(fall2012, new LocalDate(2012, 9, 23), new LocalTime(21, 30), "Chiefs", HomeOrAway.AWAY, "Frisco Stars Center");
		assertThat(fall2012.getGames(), hasItem(game1));
	}

    @Test
    public void addGame_shouldSetGameSeason() throws Exception {
        Schedule fall2012 = new Schedule("Fall 2012", "B-League");
        Game game1 = new Game();
        fall2012.addGame(game1);
        assertThat(game1.getSchedule(), is(fall2012));
    }

    @Test
	public void removeGame() throws Exception {
		Schedule fall2012 = new Schedule("Fall 2012", "B-League");
		Game game1 = new Game(fall2012, new LocalDate(2012, 9, 23), new LocalTime(21, 30), "Chiefs", HomeOrAway.AWAY, "Frisco Stars Center");
		assertThat(fall2012.getGames(), hasItem(game1));
		
		fall2012.removeGame(game1);
		assertThat(fall2012.getGames(), not(hasItem(game1)));
	}

    @Test
    public void findGame() throws Exception {
        Schedule fall2012 = new Schedule("Fall 2012", "B-League");
        Game game1 = new Game(fall2012, new LocalDate(2012, 9, 23), new LocalTime(21, 30), "Chiefs", HomeOrAway.AWAY, "Frisco Stars Center");
        ReflectionTestUtils.setField(game1, "id", 0L);
        Game game2 = new Game(fall2012, new LocalDate(2012, 9, 30), new LocalTime(21, 30), "Hawks", HomeOrAway.HOME, "Frisco Stars Center");
        ReflectionTestUtils.setField(game2, "id", 1L);
        assertThat(fall2012.getGames(), hasItem(game1));
        assertThat(fall2012.getGames(), hasItem(game2));
        assertThat(fall2012.findGame(1L), is(game2));
    }
}
