package org.bpaye1.research.model.schedule.game;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.bpaye1.research.model.player.Player;
import org.bpaye1.research.model.player.Position;
import org.bpaye1.research.model.schedule.HomeOrAway;
import org.bpaye1.research.model.schedule.Schedule;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @Column(name="TEAM_SCORE")
    private Integer teamScore;

    @Column(name="OPPONENT_TEAM_SCORE")
    private Integer opponentTeamScore;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="GAME_ID")
    private List<PlayerGameStats> gameStats = Lists.newArrayList();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="GAME_ID")
    private List<GoalieGameStats> goalieGameStats = Lists.newArrayList();

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

    public Integer getOpponentTeamScore() {
        return opponentTeamScore;
    }

    public void setOpponentTeamScore(Integer opponentTeamScore) {
        this.opponentTeamScore = opponentTeamScore;
    }

    public Integer getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(Integer teamScore) {
        this.teamScore = teamScore;
    }

    public List<PlayerGameStats> getGameStats() {
        return gameStats;
    }

    public List<GoalieGameStats> getGoalieGameStats(){
        return  goalieGameStats;
    }

    public String getDescription(){
        return isGamePlayed() ? teamScore + " - " + opponentTeamScore : StringUtils.EMPTY;
    }

    public boolean isGameTied(){
        return opponentTeamScore == teamScore;
    }

    public boolean isGameWon(){
        return teamScore > opponentTeamScore;
    }

    public boolean isGameLost(){
        return teamScore < opponentTeamScore;
    }

    public boolean isGamePlayed(){
        return teamScore != null && opponentTeamScore != null;
    }

    public boolean hasNoGameStats(){
        return gameStats.size() == 0;
    }

    public void initializeGameStats(List<Player> players){
        for(Player player : players){
            if(player.getPosition() == Position.GOALIE){
                addGoalieGameStats(new GoalieGameStats(this, player));
            }
            else {
                addPlayerGameStats(new PlayerGameStats(this, player));
            }
        }
    }

    public void addPlayerGameStats(PlayerGameStats playerGameStats){
        gameStats.add(playerGameStats);
    }

    public void addGoalieGameStats(GoalieGameStats goaliePlayerGameStats){
        goalieGameStats.add(goaliePlayerGameStats);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (date != null ? !date.equals(game.date) : game.date != null) return false;
        if (homeOrAway != game.homeOrAway) return false;
        if (location != null ? !location.equals(game.location) : game.location != null) return false;
        if (opponent != null ? !opponent.equals(game.opponent) : game.opponent != null) return false;
        if (time != null ? !time.equals(game.time) : game.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 33 * result + (time != null ? time.hashCode() : 0);
        result = 33 * result + (opponent != null ? opponent.hashCode() : 0);
        result = 33 * result + (homeOrAway != null ? homeOrAway.hashCode() : 0);
        result = 33 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
