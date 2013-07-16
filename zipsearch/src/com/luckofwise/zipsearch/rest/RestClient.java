package com.luckofwise.zipsearch.rest;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.luckofwise.zipsearch.data.ResponseContainer;

@Rest(rootUrl = "http://geoapi.heartrails.com/api", converters = GsonHttpMessageConverter.class)
public interface RestClient {

	@Get("/json?method=suggest&keyword={keyword}&matching=like")
	public ResponseContainer getResponseContainer(String keyword);
	
}
