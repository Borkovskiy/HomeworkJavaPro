package org.example;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Setting {
    private EntityManager em;
    private Scanner sc;

    public Setting() {
    }

    public Setting(EntityManager em, Scanner sc) {
        this.em = em;
        this.sc = sc;
    }

    public void addToMenu() {
        System.out.println("Enter dish name");
        String name = sc.nextLine();

        int weight = 0;
        int cossts = 0;
        while (true) {


            try {
                System.out.println("Enter dish weight");
                weight = Integer.parseInt(sc.nextLine());
                System.out.println("Enter dish costs");
                cossts = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("error costs");
            }

        }
        System.out.println("dish with discount y/n?");
        boolean discount = false;
        if (sc.nextLine().equalsIgnoreCase("y")) {
            discount = true;
        }
        em.getTransaction().begin();
        try {

            Menu newDish = new Menu(name, weight, cossts, discount);
            em.persist(newDish);
            em.getTransaction().commit();


        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public void deleteFromMenu() {
        System.out.print("Enter dish id: ");
        String menuId = sc.nextLine();
        long id = 0;
        while (true) {


            try {
                id = Long.parseLong(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("error costs");
            }

        }

        Menu m = em.getReference(Menu.class, id);
        if (m == null) {
            System.out.println("Dish not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(m);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }


    public void showMenu() {
        Query query = em.createQuery(
                "select m FROM Menu m", Menu.class);
        List<Menu> list = (List<Menu>) query.getResultList();
        for (Menu m : list)
            System.out.println(m);
    }

    public void costsFilter() {

        int low = 0;
        int top = 0;
        while (true) {


            try {
                System.out.println("Enter low costs");
                low = Integer.parseInt(sc.nextLine());
                System.out.println("Enter top costs");
                top = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("error costs");
            }

        }
        Query query = em.createQuery("SELECT m FROM Menu m WHERE (m.costs> :low)AND (m.costs<:top)", Menu.class);
        query.setParameter("low", low);
        query.setParameter("top", top);
        List<Menu> dishes = query.getResultList();
        for (Menu m : dishes)
            System.out.println(m);

    }

    public void discountFilter() {
        Query query = em.createQuery("SELECT m FROM Menu m WHERE m.discount=true", Menu.class);
        List<Menu> dishes = query.getResultList();
        for (Menu m : dishes)
            System.out.println(m);
    }

    public void weightFilter() {
        int weight = 0;
        List<Menu> order = new ArrayList<>();
        long id = 0;
        while (true) {
            System.out.println("enter dish id to +order");
            System.out.println("enter 0 to show ur order");

            try {
                id = Long.parseLong(sc.nextLine());
                if (id == 0) {
                    for (Menu m : order)
                        System.out.println(m);
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("error costs");
            }
            System.out.println(id);

            try {
                Menu dish = em.find(Menu.class, id);
                weight += dish.getWeight();
                if (weight > 1000) {
                    for (Menu m : order)
                        System.out.println(m);

                    break;
                }
                order.add(dish);

            } catch (Exception e) {
                System.out.println("error id");
                continue;
            }
        }
    }
}
