package com.mabaya.assignment.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name="Product.getAllProducts",
                query="SELECT p FROM Product p")
})
@Entity
@Table(name = "Product")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Product extends DataObject{

    private String title;
    private String category;
    private int price;

    public Product(String title, String category, int price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
