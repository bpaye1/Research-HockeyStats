package org.bpaye1.research.model.schedule;

import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class GameResult {

    @NotNull
    private Game game;

    private Integer teamScore;
    private Integer opponentTeamScore;

    @OneToMany
    private Set<PlayerGameResult>  playerGameResults = Sets.newHashSet();

    public GameResult(Game game) {
        this.game = game;
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

    public Set<PlayerGameResult> getPlayerGameResults() {
        return playerGameResults;
    }

    public void addPlayerGameResult(PlayerGameResult playerGameResult){
        playerGameResults.add(playerGameResult);
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
}
