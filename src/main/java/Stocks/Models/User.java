package Stocks.Models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    private int ID;
    @NotNull(message = "Name cannot be null")
    private String Name;
    @NotEmpty(message = "password cannot be Empty")
    private String Password;

    public User() {

    }
    public User(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}

