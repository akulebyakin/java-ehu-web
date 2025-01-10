package com.example.javaehuweb.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeShop {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String description;
    private String image;
    @Getter
    private int rating;

    public void setRating(int rating) {
        if (rating < 0 || rating > 100) {
            throw new IllegalArgumentException("Rating must be between 0 and 100");
        }
        this.rating = rating;
    }
}
