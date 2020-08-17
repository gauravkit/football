package com.example.football;

import com.example.football.model.Countries;
import com.example.football.model.Country;
import com.example.football.model.League;
import com.example.football.model.Team;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    public static Logger log = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    String url = "https://apiv2.apifootball.com/";

    public CompletableFuture<Countries> getCountry(String cname) throws InterruptedException, IOException {


        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("action", "get_countries")
                .queryParam("APIkey", "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");

        log.info("Getting Countries");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        String jsonStr = responseEntity.getBody();
        ObjectMapper om = new ObjectMapper();

        Countries countries = om.readValue(jsonStr ,Countries.class);
        return CompletableFuture.completedFuture(countries);
    }


    public CompletableFuture<League> getLeague(String lname) throws InterruptedException, IOException {


        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("action", "get_standings")
                .queryParam("league_id" ,lname)
                .queryParam("APIkey", "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");

        log.info("Getting Countries");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        String jsonStr = responseEntity.getBody();
        ObjectMapper om = new ObjectMapper();

        League league = om.readValue(jsonStr , League.class);
        return CompletableFuture.completedFuture(league);
    }


    public CompletableFuture<Team> getTeam(String tname) throws InterruptedException, IOException {


        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("action", "get_teams")
                .queryParam("league_id" ,tname)
                .queryParam("APIkey", "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");

        log.info("Getting Countries");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        String jsonStr = responseEntity.getBody();
        ObjectMapper om = new ObjectMapper();

        Team team  = om.readValue(jsonStr , Team.class);
        return CompletableFuture.completedFuture(team);
    }




}
