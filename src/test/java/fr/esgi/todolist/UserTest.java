package fr.esgi.todolist;

import fr.esgi.todolist.validators.CustomEmailValidator;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.commons.lang3.StringUtils;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    private CustomEmailValidator customEmailValidator;
    private User testUser;

    @BeforeEach
    public void setup() {
//        doReturn(true).when(CustomEmailValidator.validate(ArgumentMatchers.anyString()));
        this.testUser = new User( "Marwan","Boubchir", "azertyuiop", LocalDate.now().minusYears(21), "marwan.boubchir@outlook.fr");
    }
    @Test
    public void shouldTestUser() {
        assertEquals(testUser.firstname, "Marwan");
        assertEquals(testUser.lastname, "Boubchir");
        assertEquals(testUser.getPassword(), "azertyuiop");
        assertEquals(testUser.email, "marwan.boubchir@outlook.fr");
        assertEquals(testUser.birthDate, LocalDate.now().minusYears(21));
    }

    @Test
    void shouldInvalidateFirstname() {
        this.testUser.setFirstname("");
        assertFalse(this.testUser.isValid());
        this.testUser.setFirstname(null);
        assertFalse(this.testUser.isValid());
    }

    @Test
    void shouldInvalidateLastname() {
        this.testUser.setLastname("");
        assertFalse(this.testUser.isValid());
        this.testUser.setLastname(null);
        assertFalse(this.testUser.isValid());
    }

//    @Test
//    void shouldInvalidateEmail() {
//        this.testUser.setEmail("");
//        assertEquals(false, this.testUser.isValid());
//        this.testUser.setEmail(null);
//        assertEquals(false, this.testUser.isValid());
//    }

    @Test
    void shouldInvalidatePassword() {
        this.testUser.setLastname("");
        assertFalse(this.testUser.isValid());
        this.testUser.setLastname(null);
        assertFalse(this.testUser.isValid());
        this.testUser.setLastname("az");
        assertFalse(this.testUser.isValid());
        this.testUser.setLastname(StringUtils.repeat('e', 41));
        assertFalse(this.testUser.isValid());
    }

    @Test
    void shouldPasswordInvalid() {
        this.testUser.setPassword("az");
        assertTrue(testUser.isPasswordInvalid());
        this.testUser.setPassword(StringUtils.repeat('e', 41));
        assertTrue(this.testUser.isPasswordInvalid());
    }
    @Test
    void shouldPasswordValid() {
        this.testUser.setPassword("azertyuio");
        assertFalse(this.testUser.isPasswordInvalid());
    }

    @Test
    void ShouldInvalidateAge() {
    }

    @Test
    void shouldValidateUser() {
        assertTrue(this.testUser.isValid());
    }

}