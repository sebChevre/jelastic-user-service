package ch.sebooom.jelastic.usersservice.domaine;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@EqualsAndHashCode(of = "login")
public class User {

    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    private String password;
    @NotBlank
    private String login;

    public User(String nom, String prenom, String password, String login) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.login = login;
    }

    public User(){}


}
