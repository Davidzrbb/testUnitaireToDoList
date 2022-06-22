package fr.esgi.todolist;

import fr.esgi.todolist.validators.CustomEmailValidator;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    private CustomEmailValidator customEmailValidator;
    private User testUser;

    @BeforeEach
    public void setup() throws NotImplementedException {
        doReturn(true).when(CustomEmailValidator.validate(ArgumentMatchers.anyString()));
        this.user = new User( "Marwan","Boubchir", LocalDate.now().minusYears(21), "marwan.boubchir@outlook.fr");
    }
    @Test
    public void should_test_user() {
        //Mockito.when(ValidatorAge.validatorAge(ArgumentMatchers.anyInt())).thenReturn(25);
        User user = new User("John", "Doe", LocalDate.now().minusYears(20), "john@david");
        assertEquals(user.name, "John");
        assertEquals(user.lastname, "Doe");
        assertEquals(user.email, "john@david");
        assertEquals(user.birthDate, LocalDate.now().minusYears(20));
    }

    private User user;
    @Test
    void ShouldInvalidateName() {
        this.user.setName("");
        assertEquals(false, this.user.isValid());
        this.user.setName(null);
        assertEquals(false, this.user.isValid());
    }

    @Test
    void ShouldInvalidateLastName() {
        assertEquals(true, this.user.isValid());
    }

    @Test
    void ShouldInvalidateEmail() {
        user = new User("Marwan", "", LocalDate.now().minusYears(21),"marwan.boubchir/.Eoutlook.fr");
        assertEquals(false, this.user.isValid());
    }

    @Test
    void ShouldInvalidateAge() {
        user = new User("Marwan", "", LocalDate.now().minusYears(10),"marwan.boubchir/.Eoutlook.fr");
        assertEquals(false, this.user.isValid());
    }

    @Test
    void ShouldValidateUser() {
        user = new User("Marwan", "", LocalDate.now().minusYears(10),"marwan.boubchir/.Eoutlook.fr");
        assertEquals(false, this.user.isValid());
    }
}