//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.luckofwise.zipsearch.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestClient_
    implements RestClient
{

    private RestTemplate restTemplate;
    private String rootUrl;

    public RestClient_() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        rootUrl = "http://10.0.0.2";
    }

    @Override
    public void main() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        restTemplate.exchange(rootUrl.concat("/"), HttpMethod.GET, requestEntity, null).getBody();
    }

}
