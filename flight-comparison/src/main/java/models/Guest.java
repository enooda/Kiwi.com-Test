package models;

public class Guest {
private final String name;
private final String surname;
    private final String email;
    private final String message;

    public Guest(String name, String surname, String email, String message) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.message = message;
    }

    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getEmail() {return email;}
    public String getMessage() {return message;}

}
