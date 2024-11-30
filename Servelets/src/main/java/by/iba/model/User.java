package by.iba.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class User {

    private int id;
    private String login;
    private byte[] passw;

    public User(String name, byte[] password) {
        this.login = name;
        this.passw = password;
    }


}



