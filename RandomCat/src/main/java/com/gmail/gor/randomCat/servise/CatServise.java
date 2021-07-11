package com.gmail.gor.randomCat.servise;

import com.gmail.gor.randomCat.dao.CatDAOImpl;
import com.gmail.gor.randomCat.entity.Cat;
import com.gmail.gor.randomCat.json.CatImageApi;
import com.gmail.gor.randomCat.json.CatImageJson;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Base64;

public class CatServise {
    private boolean fullness = false;
    private int maxSize = 10;
    private int currentSize = 0;
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("RandomCat");
    private static final EntityManager em = emf.createEntityManager();
    private static final CatServise catServise = new CatServise();

    public boolean isFullness() {
        return fullness;
    }

    public void setFullness(boolean fullness) {
        this.fullness = fullness;
    }

    public static CatServise getInstance() {
        return catServise;
    }

    public String catFromApi() throws IOException, InterruptedException {

        CatImageApi catImageApi = new CatImageJson().getCatImage();
        String catImageFromApi = catImageApi.getFile();
        saveToDB(catImageFromApi);
        currentSize++;
        if (currentSize == maxSize) fullness = true;
        System.out.println("cats saved in the database-"+currentSize);
        return catImageApi.getFile();

    }

    public Cat catFromDB() {
        CatDAOImpl dao = new CatDAOImpl(em, maxSize);
        Cat cat = dao.getRandomCat();
        String base64Image = Base64.getEncoder().encodeToString(cat.getCatImage());
        cat.setBase64Image(base64Image);
        return cat;
    }

    public void saveToDB(String catAPI) {
        CatDAOImpl dao = new CatDAOImpl(catAPI, em, maxSize);
        dao.add();

    }
}
