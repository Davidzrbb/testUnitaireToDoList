package fr.esgi.todolist;

import fr.esgi.todolist.models.Item;
import fr.esgi.todolist.models.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ItemTest {

    public Item item;
    public User user;

    @BeforeEach
    void setup(){
        this.item = new Item("Item", "Contenu");
    }

    @Test
    void shouldCreateItem(){
        assertEquals("Item", this.item.getName());
        assertEquals("Contenu", this.item.getContent());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionContentTooLong(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.item.setContent(StringUtils.repeat("a", 1001)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.item = new Item("Item",StringUtils.repeat("a", 1001)));
    }

    @Test
    void shouldCreateItemWithContent(){
        String test = StringUtils.repeat("a", 1000);
        this.item.setContent(test);
        assertEquals(test, this.item.getContent());
        this.item =  new Item("Item", test);
        assertEquals(test, this.item.getContent());
    }



}