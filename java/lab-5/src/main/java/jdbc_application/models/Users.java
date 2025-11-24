package jdbc_application.models;

public class Users {
    private int id;
    private String name;
    private String surname;
    private String email;

    public Users(int id, String name, String surname, String email){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

//  Getters
    public int GetId() {
        return id;
    }
    public String GetName(){
        return name;
    }
    public String GetSurname(){
        return surname;
    }
    public String GetEmail(){
        return email;
    }

//  Setters
    public void SetName(String name){
        this.name = name;
    }
    public void SetSurname(String surname){
        this.surname = surname;
    }
    public void SetEmail(String email){
        this.email = email;
    }
}
