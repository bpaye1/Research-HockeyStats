package org.bpaye1.research.controller;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HomeControllerTest {

    @Test
    public void home() throws Exception {
        HomeController controller = new HomeController();
        String viewName = controller.home();
        assertThat(viewName, is("home"));
    }
}
