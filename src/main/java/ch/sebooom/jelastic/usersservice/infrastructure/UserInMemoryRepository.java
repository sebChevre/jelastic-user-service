package ch.sebooom.jelastic.usersservice.infrastructure;

import ch.sebooom.jelastic.usersservice.domaine.User;
import ch.sebooom.jelastic.usersservice.domaine.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserInMemoryRepository implements UserRepository{

    private static Map<Integer, User> USERSDATASOURCE = new HashMap<>();


    public static void clear(){
        USERSDATASOURCE = new HashMap<>();
    }

    static {
        //USERSDATASOURCE.put(1,new User("Chèvre","Seb","toto","sce"));
    }

    @Override
    public void saveUser(User user){
        log.info("Saving user: {}",user);

        checkIfUserExsit(user.getLogin());
        USERSDATASOURCE.put(key(),user);
        log.info("User saved: {}",user);
    }

    private void checkIfUserExsit(@NotBlank String login) {

        Optional<User> user = findUserByLogin(login);

        if(user.isPresent()){
            throw new UserExisteException("The user with login : " + login + " already exist");
        }
    }

    @Override
    public List<User> getAllUsers(){

        if(USERSDATASOURCE.values().size() == 0){
            return new ArrayList<>();
        }

        return USERSDATASOURCE.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Integer userId) {
        if(USERSDATASOURCE.keySet().contains(userId)){
            return Optional.of(USERSDATASOURCE.get(userId));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
       Optional<User> user = USERSDATASOURCE.values().stream()
                      .filter(u -> {
                          return u.getLogin().equals(login);
                      }).findFirst();

       return user;
    }

    @Override
    public Boolean login(String login, String password){

        Optional<User> user = findUserByLogin(login);

        if(user.isPresent()){
            return user.get().getPassword().equals(password);
        }

        return false;
    }

    protected Integer key() {

        Integer key = 0;

        if(USERSDATASOURCE.keySet().size() != 0){
            key = USERSDATASOURCE.keySet()
                  .stream().max(Comparator.comparing(Integer::intValue)).get() + 1;
        }

        return key;
    }
}
