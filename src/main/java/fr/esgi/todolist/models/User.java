package fr.esgi.todolist.models;

import fr.esgi.todolist.exceptions.ArrayFullException;
import fr.esgi.todolist.validators.CustomEmailValidator;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.Getter;
import lombok.Setter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class User {
    String name;
    String lastname;
    String email;
    LocalDate birthDate;
    private List<Item> toDoList;


    public User(String name, String lastname, LocalDate birthDate, String email) {
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.email = email;
    }

    public boolean isValid() {

        if(this.name == null || this.name.equals("")) {
            return false;
        }
        if(this.lastname == null || this.lastname.equals("")) {
            return false;
        }
        if(this.birthDate != null || Period.between(LocalDate.now(), birthDate).getYears() < 13){
            return false;
        }
        try {
            return CustomEmailValidator.validate(email);
        } catch (NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addItem(Item item) throws ArrayFullException {
        if(toDoList.size() >= 10){
            throw new ArrayFullException("Liste Pleine");
        }
        if(toDoList.stream().map(Item::getName).toList().contains(item.getName())){
            throw new IllegalArgumentException("Item déjà existant");
        }
        if(isInInsertItemDelay()){
            throw new DateTimeException("Rééssayez plus tard");
        }
        toDoList.add(item);
    }

    public boolean isInInsertItemDelay(){
        LocalDateTime date = Collections.max(toDoList.stream().map(Item::getCreationDate).toList());
        return ChronoUnit.MINUTES.between(date, LocalDateTime.now()) < 1;
    }
}
