package com.luckofwise.zipsearch.data;

public class Location {
	private String city;
	private String city_kana;
	private String town;
	private String town_kana;
	private String x;
	private String y;
	private String prefecture;
	private String postal;

	public String getPostal() {
		return postal;
	}

	public String getAddress() {
		return prefecture + city + town;
	}
}
