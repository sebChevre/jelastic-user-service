package ch.sebooom.jelastic.usersservice.infrastructure;

import javax.validation.constraints.NotBlank;

public class UserExisteException extends RuntimeException {
    public UserExisteException(@NotBlank String s) {
        super(s);
    }
}
