package fr.esgi.todolist;

import fr.esgi.todolist.validators.CustomEmailValidator;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private String name;
    private String lastname;
    private String email;
    private LocalDate birthDate;
    private CustomEmailValidator customEmailValidator;

    public User(String name, String lastname, LocalDate birthDate, CustomEmailValidator customEmailValidator, String email) {
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.customEmailValidator = customEmailValidator;
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
        if (this.name == null || this.name.equals("")) {
            System.out.println("Name is invalid");
            return false;
        }
        if (this.lastname == null || this.lastname.equals("")) {
            System.out.println("Lastname is invalid");
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
}
