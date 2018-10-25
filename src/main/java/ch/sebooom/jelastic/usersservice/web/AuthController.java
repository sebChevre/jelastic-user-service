package ch.sebooom.jelastic.usersservice.web;

import ch.sebooom.jelastic.usersservice.domaine.User;
import ch.sebooom.jelastic.usersservice.domaine.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity login(@Valid @RequestBody Login login){
        Boolean loginOk = userRepository.login(login.getLogin(),login.getPassword());

        log.info("Login status : {}", loginOk);

        if(loginOk){
            return ResponseEntity.ok(new AuthResult(Boolean.TRUE));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResult(Boolean.FALSE));
        }

    }
}
