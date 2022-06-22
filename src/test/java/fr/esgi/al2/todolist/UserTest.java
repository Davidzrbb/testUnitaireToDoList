package fr.esgi.al2.todolist;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import java.util.regex.Matcher;

//@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    @Test
    public void testUser() {
        //Mockito.when(ValidatorAge.validatorAge(ArgumentMatchers.anyInt())).thenReturn(25);
        User user = new User("John", "Doe", "john@david", 12);
        Assertions.assertEquals(user.name, "John");
        Assertions.assertEquals(user.lastname, "Doe");
        Assertions.assertEquals(user.email, "john@david");
        Assertions.assertEquals(user.age, 25);
    }

}