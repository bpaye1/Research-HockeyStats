package org.bpaye1.research.model.schedule;

import org.bpaye1.research.model.player.Player;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="GAME")
public class Game {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;

    @NotNull
	@ManyToOne
	@JoinColumn(name="SCHEDULE_ID")
	private Schedule schedule;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name="GAME_DATE")
	private LocalDate date;

    @NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
	@Column(name="GAME_TIME")
	private LocalTime time;

    @NotEmpty
	@Column(name="OPPONENT")
	private String opponent;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="HOME_OR_AWAY")
	private HomeOrAway homeOrAway;

    @NotEmpty
    @Column(name="LOCATION")
	private String location;

	@ManyToOne
	@JoinColumn(name="PLAYER_ON_BEVARAGE_DUTY")
	private Player beverageDutyPlayer;

    private transient GameResult result;
	
	public Game(){
	}

    public Game(Schedule schedule){
        this.schedule = schedule;
    }
	
	public Game(Schedule schedule, LocalDate date, LocalTime time, String opponent, HomeOrAway homeOrAway, String location) {
		this.schedule = schedule;
		this.schedule.addGame(this);
		this.date = date;
		this.time = time;
		this.opponent = opponent;
        this.homeOrAway = homeOrAway;
        this.location = location;
        this.result = new GameResult(this);
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HomeOrAway getHomeOrAway() {
        return homeOrAway;
    }

    public void setHomeOrAway(HomeOrAway homeOrAway) {
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

    public GameResult getResult(){
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (date != null ? !date.equals(game.date) : game.date != null) return false;
        if (location != null ? !location.equals(game.location) : game.location != null) return false;
        if (opponent != null ? !opponent.equals(game.opponent) : game.opponent != null) return false;
        if (schedule != null ? !schedule.equals(game.schedule) : game.schedule != null) return false;
        if (time != null ? !time.equals(game.time) : game.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedule != null ? schedule.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (opponent != null ? opponent.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
