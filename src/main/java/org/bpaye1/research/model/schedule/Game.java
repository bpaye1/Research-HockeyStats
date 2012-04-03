package org.bpaye1.research.model.schedule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bpaye1.research.model.player.Player;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@Entity
@Table(name="GAME")
public class Game {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="SCHEDULE_ID")
	private Schedule schedule;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name="GAME_DATE")
	private LocalDate date;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
	@Column(name="GAME_TIME")
	private LocalTime time;
	
	@Column(name="OPPONENT")
	private String opponent;
	
	@Column(name="HOME_OR_AWAY")
	private String homeOrAway;
	
	@ManyToOne
	@JoinColumn(name="PLAYER_ON_BEVARAGE_DUTY")
	private Player beverageDutyPlayer;
	
	protected Game(){
	}

    public Game(Schedule schedule){
        this.schedule = schedule;
    }
	
	public Game(Schedule schedule, LocalDate date, LocalTime time, String opponent) {
		this.schedule = schedule;
		this.schedule.addGame(this);
		this.date = date;
		this.time = time;
		this.opponent = opponent;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public String getHomeOrAway() {
		return homeOrAway;
	}

	public void setHomeOrAway(String homeOrAway) {
		this.homeOrAway = homeOrAway;
	}

	public Player getBeverageDutyPlayer() {
		return beverageDutyPlayer;
	}

	public void setBeverageDutyPlayer(Player beverageDutyPlayer) {
		this.beverageDutyPlayer = beverageDutyPlayer;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((opponent == null) ? 0 : opponent.hashCode());
		result = prime * result
				+ ((schedule == null) ? 0 : schedule.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Game other = (Game) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (opponent == null) {
			if (other.opponent != null)
				return false;
		} else if (!opponent.equals(other.opponent))
			return false;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
}
