package com.example.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;


public class Countries {


    public Countries(){};

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    @JsonProperty("Country")
    public List<Country> country;
}
