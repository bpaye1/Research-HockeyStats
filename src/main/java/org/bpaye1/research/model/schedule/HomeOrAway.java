package org.bpaye1.research.model.schedule;

public enum HomeOrAway {
    HOME("Home"), AWAY("Away");

    private String description;

    private HomeOrAway(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
