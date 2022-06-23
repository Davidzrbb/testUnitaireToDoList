package fr.esgi.todolist;

import fr.esgi.todolist.exceptions.ArrayFullException;
import fr.esgi.todolist.models.Item;
import fr.esgi.todolist.validators.CustomEmailValidator;
import jdk.jshell.spi.ExecutionControl;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import fr.esgi.todolist.models.User;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

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

    @Test
    void shouldAddMultipleItemToUserWithOutInterval() {
        Assertions.assertThrows(DateTimeException.class, () -> {
            Item item = new Item("Item", "Content");
            this.testUser.addItem(item);
            item = new Item("Item1", "Content1");
            this.testUser.addItem(item);
        });
    }

    @Test
    void shouldAddMultipleItemToUserWithInterval() throws ArrayFullException, InterruptedException {
        Item item = new Item("Item", "Content");
        int items = this.testUser.getToDoList().size();
        this.testUser.addItem(item);
        Thread.sleep(60001);
        item = new Item("Item1", "Content1");
        this.testUser.addItem(item);
        assertEquals(items + 2, this.testUser.getToDoList().size());
    }

    @Test
    void shouldSameNameItemToList() throws ArrayFullException {
        Item item = new Item("Item", "Content");
        this.testUser.addItem(item);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.testUser.addItem(item);
        });
    }
}
