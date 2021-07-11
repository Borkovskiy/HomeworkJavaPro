package com.gmail.gor.randomCat.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Transient
    private String base64Image;
    @Lob
    @Column(name = "Cat")
    private byte[] catImage;
    @Transient
    public String getBase64Image() {
        base64Image = Base64.getEncoder().encodeToString(this.catImage);
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public Cat() {
    }

    public Cat(byte[] catImage) {
        this.catImage = catImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getCatImage() {
        return catImage;
    }

    public void setCatImage(byte[] catImage) {
        this.catImage = catImage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return id == cat.id && Objects.equals(base64Image, cat.base64Image) && Arrays.equals(catImage, cat.catImage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, base64Image);
        result = 31 * result + Arrays.hashCode(catImage);
        return result;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", base64Image='" + base64Image + '\'' +
                ", catImage=" + Arrays.toString(catImage) +
                '}';
    }
}
