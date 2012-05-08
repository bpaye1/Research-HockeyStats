package org.bpaye1.research.model.schedule.game;

import org.bpaye1.research.model.player.Player;

import javax.persistence.*;

@Entity
@Table(name="PLAYER_GAME_STATS")
public class PlayerGameStats {

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

    @Column(name="GOALS")
    private Integer goals;

    @Column(name="ASSISTS")
    private Integer assists;

    @Column(name="PENALTY_MINUTES")
    private Integer penaltyMinutes;

    protected PlayerGameStats(){
    }

    public PlayerGameStats(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.id = new PlayerGameStatsId(game, player);
    }

    public Game getGame() {
        return game;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public PlayerGameStatsId getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getPenaltyMinutes() {
        return penaltyMinutes;
    }

    public void setPenaltyMinutes(Integer penaltyMinutes) {
        this.penaltyMinutes = penaltyMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerGameStats that = (PlayerGameStats) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
