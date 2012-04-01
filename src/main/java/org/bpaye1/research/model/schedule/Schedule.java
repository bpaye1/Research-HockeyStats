package org.bpaye1.research.model.schedule;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SCHEDULE")
public class Schedule {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
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
	
	public void setGames(List<Game> games){
		this.games = games;
	}
		
	public Game addGame(Game game){
		games.add(game);
		return game;
	}
	
	public void removeGame(Game game){
		games.remove(game);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
