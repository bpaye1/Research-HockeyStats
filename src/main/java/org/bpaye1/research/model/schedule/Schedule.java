package org.bpaye1.research.model.schedule;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="SCHEDULE")
public class Schedule {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;

    @NotEmpty
	@Column(name="DESCRIPTION")
	private String description;

    @NotEmpty
	@Column(name="LEAGUE")
	private String league;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="schedule", orphanRemoval=true, fetch = FetchType.EAGER)
	private List<Game> games = Lists.newArrayList();
	
	public Schedule(){
	}
	
	public Schedule(String description, String league){
		this.description = description;
		this.league = league;
	}
	
	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}
	
	public List<Game> getGames(){
		return games;
	}
		
	public Game addGame(Game game){
        game.setSchedule(this);
        games.add(game);
		return game;
	}
	
	public void removeGame(Game game){
		games.remove(game);
	}

    public Game findGame(final Long id){
        return Iterables.find(games, new Predicate<Game>() {
            @Override
            public boolean apply(@Nullable Game game) {
                return game.getId().equals(id);
            }
        }, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (description != null ? !description.equals(schedule.description) : schedule.description != null)
            return false;
        if (league != null ? !league.equals(schedule.league) : schedule.league != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (league != null ? league.hashCode() : 0);
        return result;
    }
}
