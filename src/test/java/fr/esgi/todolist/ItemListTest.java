package fr.esgi.todolist;

import fr.esgi.todolist.exceptions.ArrayFullException;
import fr.esgi.todolist.models.Item;
import fr.esgi.todolist.validators.CustomEmailValidator;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import fr.esgi.todolist.models.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemListTest {

    public User testUser;
    private CustomEmailValidator customEmailValidator;


    @BeforeEach
    void setup() throws ExecutionControl.NotImplementedException {
        this.customEmailValidator = Mockito.mock(CustomEmailValidator.class);
        Mockito.when(customEmailValidator.validate(ArgumentMatchers.anyString())).thenReturn(true);
        this.testUser = new User("Marwan", "Boubchir", "azertyuiop", LocalDate.now().minusYears(21), this.customEmailValidator, "osef");
    }

    @Test
    void shouldAddItemToUser() throws ArrayFullException {
        Item item = new Item("Item", "Content");
        int items = this.testUser.getToDoList().size();
        this.testUser.addItem(item);
        assertEquals(items + 1, this.testUser.getToDoList().size());
    }
}
