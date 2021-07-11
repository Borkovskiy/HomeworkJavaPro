package com.gmail.gor.randomCat.dao;

import com.gmail.gor.randomCat.entity.Cat;

public interface CatDAO {
    void add();
    Cat getRandomCat();
}
