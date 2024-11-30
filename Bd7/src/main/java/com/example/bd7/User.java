package com.example.bd7;

public class User {
    private int userId;
    private String login;
    private String password;
    private Person person;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public String getName() {
        return person != null ? person.getName() : null;
    }

    public String getSurname() {
        return person != null ? person.getSurname() : null;
    }
}
