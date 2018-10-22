package ch.sebooom.jelastic.usersservice.domaine;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void assert_that_two_instance_of_user_are_equal(){

        User u = new User(null,null,null,"tutu");
        User u2 = new User(null,null,null,"tutu");
        User u3 = new User(null,null,null,"tutu2");

        assertThat(u).isEqualTo(u2);

        assertThat(u2).isNotEqualTo(u3);

    }
}