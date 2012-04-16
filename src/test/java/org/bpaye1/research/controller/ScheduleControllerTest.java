package org.bpaye1.research.controller;

import com.google.common.collect.Lists;
import org.bpaye1.research.controller.editor.CustomEditorFactory;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.schedule.Game;
import org.bpaye1.research.model.schedule.HomeOrAway;
import org.bpaye1.research.model.schedule.Schedule;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.repository.ScheduleRepository;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleControllerTest {

    @Mock
    private ScheduleRepository repository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private CustomEditorFactory customEditorFactory;

    @Mock
    BindingResult bindResult;

    private ScheduleController controller;

    @Before
    public void setUp(){
        controller = new ScheduleController(repository, playerRepository, customEditorFactory);
    }

    @Test
    public void findSchedules() throws Exception {
        Schedule winter2012 = new Schedule("Winter 2012", "B-League");
        Model model = new ExtendedModelMap();

        when(repository.findAll()).thenReturn(Lists.newArrayList(winter2012));

        String viewName = controller.findSchedules(model);
        assertThat(viewName, is("schedules"));

        List<Schedule> schedules = (List<Schedule>)model.asMap().get("schedules");
        assertThat(schedules, hasItem(winter2012));
    }

    @Test
    public void findSchedule() throws Exception {
        Schedule winter2012 = new Schedule("Winter 2012", "B-League");
        when(repository.find(anyInt())).thenReturn(winter2012);

        Model model = new ExtendedModelMap();

        String viewName = controller.findSchedule(1, model);
        assertThat(viewName, is("schedule"));

        Schedule schedule = (Schedule)model.asMap().get("schedule");
        assertThat(winter2012, is(schedule));
    }

    @Test
    public void newSchedule() throws Exception {
        Model model = new ExtendedModelMap();
        String viewName = controller.newSchedule(model);
        assertThat(viewName, is("new-schedule"));
    }

    @Test
    public void newGame() throws Exception {
        Model model = new ExtendedModelMap();

        Player joe = new Player("Howard", "joe", new LocalDate(), 12);
        Player ben = new Player("ben", "cool", new LocalDate(), 14);
        when(playerRepository.findAllActive()).thenReturn(Lists.newArrayList(ben, joe));

        String viewName = controller.newGame(model);
        assertThat(viewName, is("new-schedule-game"));

        assertThat(model.containsAttribute("game"), is(true));
        assertThat(model.containsAttribute("homeOrAway"), is(true));

        List<Player> players = (List<Player>) model.asMap().get("players");
        assertThat(players, hasItem(joe));
        assertThat(players, hasItem(ben));
    }

    @Test
    public void saveNewGame() throws Exception {
        Schedule winter2012 = new Schedule("Winter 2012", "B-League");
        when(repository.find(anyInt())).thenReturn(winter2012);
        when(bindResult.hasErrors()).thenReturn(false);
        Game game1 = new Game(winter2012, new LocalDate(2012, 04, 12), new LocalTime(21,30), "Chiefs", HomeOrAway.AWAY, "Reunion Arena");
        String viewName = controller.saveNewGame(1, game1, bindResult);
        assertThat(viewName, is("redirect:/schedule/1/"));
        verify(repository).update(winter2012);
    }

    @Test
    public void saveNewGame_whenBindingErrorsExist() throws Exception {
        when(bindResult.hasErrors()).thenReturn(true);
        Schedule winter2012 = new Schedule("Winter 2012", "B-League");
        Game game1 = new Game(winter2012, new LocalDate(2012, 04, 12), new LocalTime(21,30), "Chiefs", HomeOrAway.AWAY, "Reunion Arena");
        String viewName = controller.saveNewGame(1, game1, bindResult);
        assertThat(viewName, is("schedule/1/game"));
    }
}
