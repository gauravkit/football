package com.example.football;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

@Builder
@Data
@ToString

public class Response {

    @Tolerate
    public Response(){}

    public String country_id;
    public String country_name;
    public String league_id;
    public String league_name;
    public String team_id;
    public String team_name;
}
