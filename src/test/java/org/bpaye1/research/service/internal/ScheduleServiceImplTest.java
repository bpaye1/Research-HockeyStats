package org.bpaye1.research.service.internal;

import com.google.common.collect.Lists;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.schedule.game.Game;
import org.bpaye1.research.model.schedule.Schedule;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.repository.ScheduleRepository;
import org.bpaye1.research.service.ScheduleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.anyListOf;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceImplTest {

    @Mock
    private ScheduleRepository repository;

    @Mock
    private PlayerRepository playerRepository;

    private ScheduleService service;

    @Before
    public void setUp() throws Exception {
        service = new ScheduleServiceImpl(repository, playerRepository);
    }

    @Test
    public void findGame() throws Exception {
        Schedule fall2012 = new Schedule();
        Game game = mock(Game.class);
        Player player = new Player();

        when(repository.findGame(anyLong())).thenReturn(game);
        when(playerRepository.findAllActive()).thenReturn(Lists.newArrayList(player));
        when(game.hasNoGameStats()).thenReturn(true);

        service.findGame(1L);

        verify(game).hasNoGameStats();
        verify(game).initializeGameStats(anyListOf(Player.class));
    }

    @Test
    public void findGame_shouldNotInitializeGameStatsWhenGameStatsExists() throws Exception {
        Schedule fall2012 = new Schedule("Fall 2012", "B-League");
        Game game = mock(Game.class);
        Player player = new Player();

        when(repository.findGame(anyLong())).thenReturn(game);
        when(playerRepository.findAllActive()).thenReturn(Lists.newArrayList(player));
        when(game.hasNoGameStats()).thenReturn(false);

        service.findGame(1L);

        verify(game).hasNoGameStats();
        verify(game, times(0)).initializeGameStats(anyListOf(Player.class));
    }

    @Test
    public void findSchedule() throws Exception {
        int scheduleId = 1;
        Schedule fall2012 = new Schedule("Fall 2012", "B-League");
        when(repository.find(scheduleId)).thenReturn(fall2012);

        Schedule result = service.findSchedule(scheduleId);
        verify(repository).find(scheduleId);
        assertThat(result, is(fall2012));
    }

    @Test
    public void findAllSchedules() throws Exception {
        Schedule fall2012 = new Schedule("Fall 2012", "B-League");
        when(repository.findAll()).thenReturn(Lists.newArrayList(fall2012));

        List<Schedule> result = service.findAllSchedules();
        verify(repository).findAll();
        assertThat(result, hasItem(fall2012));
    }

    @Test
    public void addSchedule() throws Exception {
        Schedule fall2012 = new Schedule("Fall 2012", "B-League");
        service.addSchedule(fall2012);
        verify(repository).add(fall2012);
    }

    @Test
    public void updateSchedule() throws Exception {
        Schedule fall2012 = new Schedule("Fall 2012", "B-League");
        service.updateSchedule(fall2012);
        verify(repository).update(fall2012);
    }

}
