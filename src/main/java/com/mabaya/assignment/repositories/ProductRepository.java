package com.mabaya.assignment.repositories;

import com.mabaya.assignment.entities.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product p, Campaign c " +
            "WHERE c.category = p.category " +
            "AND c.active = true " +
            "AND c.category = ?1 " +
            "ORDER BY c.bid DESC ")
    Product findPromotedProductWithHighestCampaignBidByCategory(String category, PageRequest page);

    @Query("SELECT p from Product p, Campaign c " +
            "WHERE c.active = true " +
            "ORDER BY c.bid DESC, p.price DESC ")
    Product findPromotedProductWithHighestCampaignBidAndPrice(PageRequest page);
}
