package org.bpaye1.research.model;

public enum Position {
	GOALIE("Goalie"), DEFENSEMEN("Defensemen"), CENTER("Center"), RIGHT_WING("Right Wing"), LEFT_WING("Left Wing");
	
	private String description;
	
	private Position(String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
