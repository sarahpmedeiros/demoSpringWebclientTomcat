package com.webclient.demoWithTomcat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String login;
    private Integer id;
    private String name;

    @JsonCreator
    public User(@JsonProperty("login") String login, @JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.login = login;
        this.id = id;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
