package ch.sebooom.jelastic.usersservice.web;

import lombok.Getter;

@Getter
public class Login {

    private String login;
    private String password;

    public Login(String login, String password){
        this.login = login;
        this.password = password;
    }

    public Login () {}

}
