package com.markisha.enums;

public enum Zahtevi {

	PESMA("Song"),
	ALBUM("Album"),
	IZVODJAC("Artist");
	
	final String tip;
	
	Zahtevi(String tip) {
		this.tip = tip;
	}

	public String getTip() {
		return tip;
	}
	
}
