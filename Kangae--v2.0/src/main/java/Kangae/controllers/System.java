package Kangae.controllers;


import Kangae.models.User;

import javax.validation.constraints.Null;

public class System {

    User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public System(User user) {
        this.user = user;
    }


}
