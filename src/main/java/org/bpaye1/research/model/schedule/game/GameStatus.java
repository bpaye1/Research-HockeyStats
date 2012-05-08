package org.bpaye1.research.model.schedule.game;

public enum GameStatus {
    PLAYED("Played"), DID_NOT_PLAY("Did Not Play"), SUSPENDED("Suspended");

    private String description;

    private GameStatus(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
