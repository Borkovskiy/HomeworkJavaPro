package ua.kiev.prog.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {
    private String login;
    private String password;
    private String status;


    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }

}
