package com.gmail.gor.randomCat.dao;

import com.gmail.gor.randomCat.entity.Cat;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class CatDAOImpl implements CatDAO{
    private String catAPI;
    private EntityManager em;
    private int catsZise;


    public CatDAOImpl(String catAPI, EntityManager em, int catsZise) {
        this.catAPI = catAPI;
        this.em = em;
        this.catsZise = catsZise;
    }

    public CatDAOImpl(EntityManager em, int catsZise) {
        this.em = em;
        this.catsZise = catsZise;
    }

    @Override
    public void add() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri((
                            URI.create(catAPI)
                    ))
                    .build();
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            byte[] catByte = response.body();
            Cat cat = new Cat();
            cat.setCatImage(catByte);
            try {
                em.getTransaction().begin();
                em.persist(cat);
                em.getTransaction().commit();
            } catch (EntityExistsException e) {
                e.printStackTrace();
                em.getTransaction().rollback();

            }

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Cat getRandomCat() {
        Random random = new Random();
        int number = random.nextInt(catsZise);
        Query selectQuery = em.createQuery("select c from Cat c");
        selectQuery.setFirstResult(number);
        selectQuery.setMaxResults(1);
        Cat cat= (Cat)selectQuery.getSingleResult();
        return cat;
    }
}
