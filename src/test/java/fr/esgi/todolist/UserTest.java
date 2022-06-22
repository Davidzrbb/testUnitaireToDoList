package fr.esgi.todolist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


//@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    @Test
    public void testUser() {
        //Mockito.when(ValidatorAge.validatorAge(ArgumentMatchers.anyInt())).thenReturn(25);
        User user = new User("John", "Doe", "john@david", 25);
        Assertions.assertEquals(user.name, "John");
        Assertions.assertEquals(user.lastname, "Doe");
        Assertions.assertEquals(user.email, "john@david");
        Assertions.assertEquals(user.age, 25);
    }

}