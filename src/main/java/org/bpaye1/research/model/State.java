package org.bpaye1.research.model;

public class State {
	
	private String code;
	private String name;
	
	public State(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}