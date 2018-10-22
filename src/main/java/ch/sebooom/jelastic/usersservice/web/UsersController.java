package ch.sebooom.jelastic.usersservice.web;

import ch.sebooom.jelastic.usersservice.domaine.User;
import ch.sebooom.jelastic.usersservice.domaine.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        userRepository.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity getAllUsers(
            @RequestParam(value = "login", required = false) String userLogin){

        if(null == userLogin){
            return ResponseEntity.ok(userRepository.getAllUsers());
        }else{

            Optional<User> user = userRepository.findUserByLogin(userLogin);

            if(user.isPresent()){
                return ResponseEntity.ok(Arrays.asList(user));
            }else{
                return ResponseEntity.ok(new ArrayList<>());
            }

        }


    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable(name = "id") Integer userId){

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }

    }




}
