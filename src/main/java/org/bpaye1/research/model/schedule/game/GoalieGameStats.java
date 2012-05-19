package org.bpaye1.research.model.schedule.game;

import org.bpaye1.research.model.player.Player;

import javax.persistence.*;

@Entity
@Table(name="GOALIE_GAME_STATS")
public class GoalieGameStats {

    @EmbeddedId
    private PlayerGameStatsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PLAYER_ID", insertable = false, updatable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GAME_ID", insertable = false, updatable = false)
    private Game game;

    @Enumerated(EnumType.STRING)
    @Column(name="GAME_STATUS")
    private GameStatus gameStatus = GameStatus.DID_NOT_PLAY;

    @Column(name="GOALS_AGAINST")
    private Integer goalsAgainst;

    @Column(name="SHOTS_ON_GOAL")
    private Integer shotsOnGoal;

    protected GoalieGameStats(){
    }

    public GoalieGameStats(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.id = new PlayerGameStatsId(game, player);
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public Integer getShotsOnGoal() {
        return shotsOnGoal;
    }

    public void setShotsOnGoal(Integer shotsOnGoals) {
        this.shotsOnGoal = shotsOnGoals;
    }

    public Double getSavePercentage(){
        return (goalsAgainst / Double.valueOf(shotsOnGoal)) * 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalieGameStats that = (GoalieGameStats) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
