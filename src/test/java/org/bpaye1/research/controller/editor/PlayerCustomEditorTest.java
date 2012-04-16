package org.bpaye1.research.controller.editor;

import org.apache.commons.lang.StringUtils;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.repository.PlayerRepository;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerCustomEditorTest {

    @Mock
    private PlayerRepository repository;

    @Test
    public void setAsText() throws Exception {
        Player player = new Player("Richard", "Maurice", new LocalDate(1945, 12, 12), 9);
        long playerId = 1L;
        ReflectionTestUtils.setField(player, "id", playerId);
        when(repository.find(playerId)).thenReturn(player);

        PlayerCustomEditor customEditor = new PlayerCustomEditor(repository);

        customEditor.setAsText(String.valueOf(playerId));

        assertThat((Player) customEditor.getValue(), is(player));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAsText_shouldFailWhenParsingAnInvalidPlayerId() throws Exception {
        PlayerCustomEditor customEditor = new PlayerCustomEditor(repository);
        customEditor.setAsText("adsasd11");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAsText_shouldFailWhenParsingANullPlayerId() throws Exception {
        PlayerCustomEditor customEditor = new PlayerCustomEditor(repository);
        customEditor.setAsText(null);
    }

    @Test
    public void getAsText() throws Exception {
        Player player = new Player("Richard", "Maurice", new LocalDate(1945, 12, 12), 9);
        long playerId = 1L;
        ReflectionTestUtils.setField(player, "id", playerId);
        when(repository.find(playerId)).thenReturn(player);

        PlayerCustomEditor customEditor = new PlayerCustomEditor(repository);

        customEditor.setAsText(String.valueOf(playerId));

        assertThat((Player) customEditor.getValue(), is(player));
        assertThat(customEditor.getAsText(), is(String.valueOf(playerId)));
    }

    @Test
    public void getAsText_shouldReturnEmptyStringWhenNoValueIsPassedIn() throws Exception {
        PlayerCustomEditor customEditor = new PlayerCustomEditor(repository);
        assertThat(customEditor.getAsText(), is(StringUtils.EMPTY));
    }
}
