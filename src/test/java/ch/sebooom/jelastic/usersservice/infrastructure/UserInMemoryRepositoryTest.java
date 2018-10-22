package ch.sebooom.jelastic.usersservice.infrastructure;

import ch.sebooom.jelastic.usersservice.domaine.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInMemoryRepositoryTest {

    @Autowired
    UserInMemoryRepository userInMemoryRepository;

    @Test
    public void assert_that_key_return_correct_key(){

        UserInMemoryRepository.clear();

        assertThat(userInMemoryRepository.key()).isEqualTo(0);

        userInMemoryRepository.saveUser(getFakeUSer());

        assertThat(userInMemoryRepository.key()).isEqualTo(1);

    }

    @Test
    public void assert_that_get_by_id_return_correct_value(){

        //assertThat(userInMemoryRepository.key()).isEqualTo(0);

        userInMemoryRepository.saveUser(getFakeUSer());

        assertThat(userInMemoryRepository.findById(1).isPresent());

    }

    private User getFakeUSer(){
        return new User("toto","tutu","atat","tete");
    }


}