package org.bpaye1.research.controller;

import com.google.common.collect.Lists;
import org.bpaye1.research.model.schedule.Schedule;
import org.bpaye1.research.repository.ScheduleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleControllerTest {

    @Mock
    private ScheduleRepository repository;

    private ScheduleController controller;

    @Before
    public void setUp(){
        controller = new ScheduleController(repository);
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
}
