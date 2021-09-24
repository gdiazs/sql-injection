package com.github.gdiazs.sqlinjection.models;

import java.util.List;
import java.util.Map;

public final class UserSessionModel {

    private final String username;
    private final  Map<String, List<String>> roles;

    public UserSessionModel(String username, Map<String, List<String>> roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }


    public Map<String, List<String>> getRoles() {
        return roles;
    }

}
