package jdbc_application.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Users {

    private int id;

    @NotBlank(message = "Name can't be null")
    @Size(min = 2, max = 50, message = "Name size can be from 2 to 50")
    private String name;

    @NotBlank(message = "Surname can't be null")
    @Size(min = 2, max = 50, message = "Surname size can be from 2 to 50")
    private String surname;

    @Email(message = "Email should have @ and .address")
    @NotBlank(message = "Email can't be null")
    private String email;

    public Users()
    {

    }
    public Users(String name, String surname, String email)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    public Users(int id, String name, String surname, String email)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

//  Getters
    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getEmail(){
        return email;
    }

//  Setters
    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setEmail(String email){
        this.email = email;
    }
}
