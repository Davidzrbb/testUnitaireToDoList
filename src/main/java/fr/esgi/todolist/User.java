package fr.esgi.todolist;

import fr.esgi.todolist.validators.CustomEmailValidator;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.time.LocalDate;
import java.time.Period;

public class User {
    String name;
    String lastname;
    String email;
    LocalDate birthDate;

    public User(String name, String lastname, LocalDate birthDate, String email) {
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
}
