package com.note.modules;

import com.note.entitiesDAO.UserEntityDAO;

public class JwtResponse {

    private UserEntityDAO user;
    private String jwtToken;

    public JwtResponse(UserEntityDAO user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public UserEntityDAO getUser() {
        return user;
    }

    public void setUser(UserEntityDAO user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
