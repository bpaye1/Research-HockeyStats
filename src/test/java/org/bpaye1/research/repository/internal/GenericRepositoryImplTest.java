package org.bpaye1.research.repository.internal;

import org.bpaye1.research.AbstractDatabaseTest;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.repository.PlayerRepository;
import org.bpaye1.research.util.DateUtils;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class GenericRepositoryImplTest extends AbstractDatabaseTest{

    @Inject
    private PlayerRepository repository;

    @Test
    public void add() throws Exception {
        Player player = new Player("Richard", "Maurice", DateUtils.newDate(1945, 12, 12), 9);
        repository.add(player);
        assertThat(getEm().find(Player.class, player.getId()), is(player));
    }

    @Test
    public void update() throws Exception {
        Player player = repository.add(new Player("Richard", "Maurice", DateUtils.newDate(1945, 12, 12), 9));
        assertThat(player.getFirstName(), is("Maurice"));

        player.setFirstName("Moe");

        repository.update(player);

        Player updatedPlayer = repository.find(player.getId());
        assertThat(updatedPlayer.getFirstName(), is("Moe"));
    }

    @Test
    public void remove() throws Exception {
        Player player = new Player("Richard", "Maurice", DateUtils.newDate(1945, 12, 12), 9);
        repository.add(player);
        Player persistedPlayer = getEm().find(Player.class, player.getId());
        assertThat(persistedPlayer, is(player));

        repository.remove(persistedPlayer);
        assertThat(getEm().find(Player.class, persistedPlayer.getId()), is(nullValue()));
    }
}
