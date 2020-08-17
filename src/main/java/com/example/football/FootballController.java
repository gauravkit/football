package com.example.football;

import com.example.football.model.Countries;
import com.example.football.model.League;
import com.example.football.model.Team;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class FootballController {

    public static Logger log = LoggerFactory.getLogger(FootballController.class);

    @Autowired
    private AsyncService service;

    @RequestMapping( value = "getOrches/{country}/{league}/{team}" ,method = RequestMethod.GET)
    public void  footballOrches(@PathVariable( name="country") String country ,@PathVariable(name="league") String league ,
                                @PathVariable(name="team") String team) throws InterruptedException, ExecutionException, IOException {

      log.info("Inside footballOrches");

        CompletableFuture<Countries>  countries = service.getCountry(country);
        CompletableFuture<League>  leagues = service.getLeague(league);
        CompletableFuture<Team> teams = service.getTeam(team);

        CompletableFuture.allOf(countries,leagues,teams).join();

        //set Response
        Response rs = new Response();


    }
}
