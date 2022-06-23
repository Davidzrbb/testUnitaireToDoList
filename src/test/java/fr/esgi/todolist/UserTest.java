package fr.esgi.todolist;

import fr.esgi.todolist.validators.CustomEmailValidator;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserTest {

    CustomEmailValidator customEmailValidator;
    private User testUser ;


    @BeforeEach
    public void setup() throws NotImplementedException {
        this.customEmailValidator = Mockito.mock(CustomEmailValidator.class);
        Mockito.when(customEmailValidator.validate(ArgumentMatchers.anyString())).thenReturn(true);
        this.testUser = new User("Marwan", "Boubchir", LocalDate.now().minusYears(21), this.customEmailValidator,"osef");
        //on test le user car sinon renvoie une erreur à chaque fois qu'on utilise pas le mock
        assertTrue(this.testUser.isValid());
    }

    @Test
    public void should_test_user() {
        User userCurrent =
                new User("John",
                        "Doe",
                        LocalDate.now().minusYears(15),
                        this.customEmailValidator,
                        "toto");
        assertTrue(userCurrent.isValid());
    }

    @Test
    void ShouldInvalidateName() {
        this.testUser.setName("");
        assertFalse(this.testUser.isValid());
        this.testUser.setName(null);
        assertFalse(this.testUser.isValid());
    }

//    @Test
//    void ShouldInvalidateLastName() {
//        assertEquals(true, this.user.isValid());
//    }
//
//    @Test
//    void ShouldInvalidateEmail() {
//        user = new User("Marwan", "", LocalDate.now().minusYears(21),"marwan.boubchir/.Eoutlook.fr");
//        assertEquals(false, this.user.isValid());
//    }
//
//    @Test
//    void ShouldInvalidateAge() {
//        user = new User("Marwan", "", LocalDate.now().minusYears(10),"marwan.boubchir/.Eoutlook.fr");
//        assertEquals(false, this.user.isValid());
//    }
//
//    @Test
//    void ShouldValidateUser() {
//        user = new User("Marwan", "", LocalDate.now().minusYears(10),"marwan.boubchir/.Eoutlook.fr");
//        assertEquals(false, this.user.isValid());
//    }
}