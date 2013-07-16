package com.luckofwise.zipsearch.data;

public class Location {
	String city;
	String city_kana;
	String town;
	String town_kana;
	String x;
	String y;
	String prefecture;
	String postal;

	public String getPostal() {
		return postal;
	}

	public String getAddress() {
		return prefecture + city + town;
	}
}
