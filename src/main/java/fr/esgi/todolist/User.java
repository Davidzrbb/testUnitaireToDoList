package fr.esgi.todolist;

import java.time.LocalDate;

public class User {
    String name;
    String lastname;
    String email;
    LocalDate age;

    public User(String name, String lastname, String email, LocalDate age) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
    }

    public boolean isValid(){
        if (this.name != null
                && this.lastname != null
                && this.age != null
                && this.email != null ){
            return LocalDate.now().minusYears(13).isAfter(this.age);
        }
        else{
            return false;
        }
    }
}
