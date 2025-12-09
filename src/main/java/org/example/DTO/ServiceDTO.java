package org.example.DTO;

import jakarta.persistence.Column;

import java.util.UUID;

public class ServiceDTO {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer duration;
    private String category;
    private boolean isActive;

    protected ServiceDTO() {}

    public ServiceDTO(String name, String description, Double price, Integer duration, String category) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
