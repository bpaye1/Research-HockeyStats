package org.bpaye1.research.model.schedule;

import java.util.List;

import com.google.common.collect.Lists;

public class Schedule {

	private Integer id;
	private String description;
	private String division;
	private List<Game> games = Lists.newArrayList();
	
	public Schedule(String description, String division){
		this.description = description;
		this.division = division;
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

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}
	
	public List<Game> getGames(){
		return games;
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
