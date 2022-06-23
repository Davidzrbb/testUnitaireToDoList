package fr.esgi.todolist.models;

import fr.esgi.todolist.exceptions.ArrayFullException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public void setContent(String content) {
        checkContentItem(content);
        this.content = content;
    }

    public void checkContentItem(String content){
        if(content.length() > 1000){
            throw new IllegalArgumentException("Liste Pleine");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(content, item.content) && Objects.equals(creationDate, item.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, content, creationDate);
    }
}
