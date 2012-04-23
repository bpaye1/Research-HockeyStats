package org.bpaye1.research.model.schedule;

import org.bpaye1.research.model.player.Player;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PlayerGameStatsId implements Serializable {

    @Column(name="PLAYER_ID")
    private Long playerId;

    @Column(name="GAME_ID")
    private Long gameId;

    protected  PlayerGameStatsId(){
    }

    public PlayerGameStatsId(Game game, Player player){
        this.gameId = game.getId();
        this.playerId = player.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerGameStatsId that = (PlayerGameStatsId) o;

        if (gameId != null ? !gameId.equals(that.gameId) : that.gameId != null) return false;
        if (playerId != null ? !playerId.equals(that.playerId) : that.playerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerId != null ? playerId.hashCode() : 0;
        result = 31 * result + (gameId != null ? gameId.hashCode() : 0);
        return result;
    }
}
