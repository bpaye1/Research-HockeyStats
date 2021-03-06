package org.bpaye1.research.repository.internal;

import org.bpaye1.research.AbstractDatabaseTest;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.player.Status;
import org.bpaye1.research.repository.PlayerRepository;
import org.joda.time.LocalDate;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class PlayerRepositoryImplTest extends AbstractDatabaseTest {
	
	@Inject
	private PlayerRepository repository;

    @Test
    public void find() throws Exception {
        Player player = new Player("Richard", "Maurice", new LocalDate(1945, 12, 12), 9);
        repository.add(player);
        assertThat(repository.find(player.getId()), is(player));
    }

    @Test
	public void findAll() throws Exception {
		Player maurice = repository.add(new Player("Richard", "Maurice", new LocalDate(1945, 12, 12), 9));
		Player henry = repository.add(new Player("Henry", "Maurice", new LocalDate(1945, 12, 12), 12));
		
		List<Player> players = repository.findAll();
		assertThat(players, hasItem(maurice));
		assertThat(players, hasItem(henry));
	}

    @Test
    public void findAllActivePlayers() throws Exception {
        Player maurice = new Player("Richard", "Maurice", new LocalDate(1945, 12, 12), 9);
        Player henry = new Player("Henry", "Maurice", new LocalDate(1945, 12, 12), 12);
        henry.setStatus(Status.INACTIVE);
        Player junior = new Player("Junior", "Hogan", new LocalDate(197, 12, 12), 12);

        repository.add(maurice);
        repository.add(henry);
        repository.add(junior);

        List<Player> players = repository.findAllActive();
        assertThat(players, hasItem(maurice));
        assertThat(players, not(hasItem(henry)));
        assertThat(players, hasItem(junior));
    }
}
