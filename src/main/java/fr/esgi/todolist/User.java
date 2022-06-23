package fr.esgi.todolist;

import fr.esgi.todolist.validators.CustomEmailValidator;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.time.LocalDate;
import java.time.Period;

public class User {
    String firstname;
    String lastname;
    String password;
    String email;
    LocalDate birthDate;
    private CustomEmailValidator customEmailValidator;


    public User(String firstname, String lastname, String password, LocalDate birthDate, CustomEmailValidator customEmailValidator, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.password = password;
        this.customEmailValidator = customEmailValidator;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isPasswordInvalid(){
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
        if(this.password == null || this.password.equals("") || isPasswordInvalid()) {
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
