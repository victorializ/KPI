package com.gateway.apigateway.Equipment;

public class Equipment {
    private Integer id;
    private String name;
    private Float price;
    private String type;
    private Integer rating;
    private String description;

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Integer getRating() { return rating; }

    public void setRating(Integer rating) { this.rating = rating; }
}