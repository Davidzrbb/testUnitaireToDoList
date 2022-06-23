package fr.esgi.todolist.models;

import fr.esgi.todolist.exceptions.ArrayFullException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Item {
    private String name;
    private String content;
    private LocalDateTime creationDate;

    public Item(String name, String content) {
        checkContentItem(content);
        this.name = name;
        this.content = content;
        this.creationDate = LocalDateTime.now();
    }

    public void checkContentItem(String content){
        if(content.length() >= 1000){
            throw new IllegalArgumentException("Liste Pleine");
        }
    }
}
