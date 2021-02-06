package com.mabaya.assignment.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@NamedQueries({
        @NamedQuery(name= Campaign.GET_ALL_CAMPAIGNS,
                query= "SELECT c FROM Campaign c")
})
@Entity
@Table(name = "Campaign")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Campaign extends DataObject{

    public static final String GET_ALL_CAMPAIGNS = "Campaign.getAllCampaigns";
    public static final int CAMPAIGN_ACTIVE_THRESHOLD_DAYS = 10;


    private String name;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate startDate;

    private String category;

    private long bid;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="campaign_product",
            joinColumns = @JoinColumn(name = "fk_campaign"),
            inverseJoinColumns = @JoinColumn(name = "fk_product")
    )
    private Set<Product> products = new HashSet<>();

    private boolean active = false;

    public Campaign(String name, LocalDate startDate, String category, long bid) {
        this.name = name;
        this.startDate = startDate;
        this.category = category;
        this.bid = bid;
        resolveIsActive();
    }

    private void resolveIsActive() {
        if (startDate != null) {
            this.active = DAYS.between(startDate, LocalDate.now()) < CAMPAIGN_ACTIVE_THRESHOLD_DAYS;
        }
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
