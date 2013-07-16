package com.luckofwise.zipsearch.data;

import com.j256.ormlite.field.DatabaseField;

public class Location {
	@DatabaseField(generatedId = true)
	int id;
	@DatabaseField
	String city;
	@DatabaseField
	String city_kana;
	@DatabaseField
	String town;
	@DatabaseField
	String town_kana;
	@DatabaseField
	String x;
	@DatabaseField
	String y;
	@DatabaseField
	String prefecture;
	@DatabaseField
	String postal;

	public Location() {
		// needed by ormlite
	}
	
	public String getPostal() {
		return postal;
	}

	public String getAddress() {
		return prefecture + city + town;
	}
}
