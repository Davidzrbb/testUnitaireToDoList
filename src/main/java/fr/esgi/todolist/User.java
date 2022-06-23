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

    public User(String firstname, String lastname, String password, LocalDate birthDate, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.birthDate = birthDate;
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

        if(this.firstname == null || this.firstname.equals("")) {
            return false;
        }
        if(this.lastname == null || this.lastname.equals("")) {
            return false;
        }
        if(this.password == null || this.password.equals("") || isPasswordInvalid()) {
            return false;
        }
        if(this.birthDate == null || Period.between(LocalDate.now(), birthDate).getYears() < 13){
            return false;
        }
        try {
            return CustomEmailValidator.validate(email);
        } catch (NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}
