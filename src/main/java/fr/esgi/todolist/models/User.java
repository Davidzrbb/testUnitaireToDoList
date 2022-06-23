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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class User {
    String firstname;
    String lastname;
    String password;
    String email;
    LocalDate birthDate;
    private CustomEmailValidator customEmailValidator;
    private List<Item> toDoList = new ArrayList<>();


    public User(String firstname, String lastname, String password, LocalDate birthDate, CustomEmailValidator customEmailValidator, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.password = password;
        this.customEmailValidator = customEmailValidator;
        this.email = email;
    }

    public boolean isPasswordInvalid() {
        return this.password.length() < 8 || this.password.length() > 40;
    }

    public boolean isValid() {
        if (this.firstname == null || this.firstname.equals("")) {
            System.out.println("Name is invalid");
            return false;
        }
        if (this.lastname == null || this.lastname.equals("")) {
            System.out.println("Lastname is invalid");
            return false;
        }
        if (this.password == null || this.password.equals("") || isPasswordInvalid()) {
            return false;
        }
        if (this.birthDate == null || !LocalDate.now().minusYears(13).isAfter(this.birthDate)) {
            System.out.println("Birth date is invalid");
            return false;
        }
        try {
            return this.customEmailValidator.validate(email);
        } catch (NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addItem(Item item) throws ArrayFullException {
        if (toDoList.size() >= 10) {
            throw new ArrayFullException("Liste Pleine");
        }
        if (toDoList.stream().map(Item::getName).toList().contains(item.getName())) {
            throw new IllegalArgumentException("Item déjà existant");
        }
        if (isInInsertItemDelay()) {
            throw new DateTimeException("Rééssayez plus tard");
        }
        toDoList.add(item);
    }

    public boolean isInInsertItemDelay() {
        if (toDoList.size() > 0) {
            LocalDateTime date = Collections.max(toDoList.stream().map(Item::getCreationDate).toList());
            return ChronoUnit.MINUTES.between(date, LocalDateTime.now()) < 1;
        } else {
            return false;
        }
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
