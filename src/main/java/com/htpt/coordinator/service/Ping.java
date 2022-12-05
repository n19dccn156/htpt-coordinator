package com.htpt.coordinator.service;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class Ping {
    
    public Boolean ping(String host) {
        try {

            // URI uri = new URI(host+"/api/ping"); 
            System.out.println(host+"/api/ping");
            SimpleClientHttpRequestFactory client = new SimpleClientHttpRequestFactory();
            client.setConnectTimeout(1000);
            client.setReadTimeout(1000);


            RestTemplate restTemplate = new RestTemplate(client);
            Boolean response = restTemplate.getForObject(host+"/api/ping", Boolean.class);
            System.out.println(response);
            if(response != null) return response;

            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }  
}
