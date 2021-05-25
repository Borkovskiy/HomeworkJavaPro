package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("Menu1");
            em = emf.createEntityManager();
            Setting setting=new Setting(em,sc);
            try {

                while (true) {
                    System.out.println("1: add dish");
                    System.out.println("2: delete dish");
                    System.out.println("3: view Menu");
                    System.out.println("4: view Menu with costs filter");
                    System.out.println("5: view dishes with discount");
                    System.out.println("6: view Menu with weight filter");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            setting.addToMenu();
                            break;
                        case "2":
                            setting.deleteFromMenu();
                            break;
                        case "3":
                            setting.showMenu();
                            break;
                        case "4":
                            setting.costsFilter();
                            break;
                        case "5":
                            setting.discountFilter();
                            break;
                        case "6":
                            setting.weightFilter();
                            break;
                        default:
                            continue;
                    }
                }

            } finally {
            sc.close();
            em.close();
            emf.close();
    }
    }catch (Exception ex) {
        ex.printStackTrace();
        return;
        }
    }

}