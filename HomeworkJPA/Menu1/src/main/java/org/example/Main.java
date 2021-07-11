package org.example;

import org.example.dao.MenuDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;


    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {
            emf = Persistence.createEntityManagerFactory("Menu1");
            em = emf.createEntityManager();
            MenuDAOImpl dao = new MenuDAOImpl(em, sc);


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
                        dao.add();
                        break;
                    case "2":
                        dao.delete();
                        break;
                    case "3":
                        dao.showMenu();
                        break;
                    case "4":
                        dao.findByCosts();
                        break;
                    case "5":
                        dao.findByDiscount();
                        break;
                    case "6":
                        dao.findByWeight();
                        break;
                    default:
                        continue;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        } finally {

            em.close();
            emf.close();
        }
    }
}

