package org.bpaye1.research.model.player;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Test
    public void getFullName() throws Exception {
        Player player = new Player("Richard", "Maurice", new LocalDate(1945, 12, 12), 9);
        assertThat(player.getFullName(), is("Maurice Richard"));
    }
}
