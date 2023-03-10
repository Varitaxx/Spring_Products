package eu.asgardschmiede.sproducts.service;

import eu.asgardschmiede.sproducts.model.User;

import java.io.Serializable;

public class LoginService implements Serializable {

    private User user;

    public User getUser() {
        return user;
    }

    public void doLogIn(User user) {
        this.user = user;
    }

    public void doLogOut() {
        this.user = null;
    }

    public boolean isLoggedIn() {
        return this.user != null;
    }
}
