package com.example.demo.kunstobjekt;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Kunstobjekt {
    @Id
    @TextIndexed
    private String id;

    @TextIndexed(weight = 2)
    private String name;

    @TextIndexed
    private String description;

    @TextIndexed
    private String technik;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnik() {
        return technik;
    }

    public void setTechnik(String technik) {
        this.technik = technik;
    }
}