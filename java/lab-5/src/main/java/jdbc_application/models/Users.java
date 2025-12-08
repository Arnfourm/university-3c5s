package jdbc_application.models;

public class Users {
    private int id;
    private String name;
    private String surname;
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
