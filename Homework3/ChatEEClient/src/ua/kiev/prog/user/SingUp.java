package ua.kiev.prog.user;

import java.io.IOException;
import java.util.Scanner;

public class SingUp {
    private String login;
    private String password;
    private Scanner scanner;
    private User user;

    public SingUp(Scanner scanner) {
        this.scanner = scanner;
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

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User enter() throws IOException {
        while (true) {
            System.out.println("Enter your login: ");
            this.login = scanner.nextLine();
            System.out.println("Enter your password: ");
            this.password = scanner.nextLine();
            user = new User(login, password);
            int number = user.checkUser();
            if (number == 201) {
                break;

            } else {
                System.out.println("error login or password");

            }

        }
        return user;
    }

}

