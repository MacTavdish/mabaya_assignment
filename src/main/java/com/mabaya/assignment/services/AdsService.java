package com.mabaya.assignment.services;

import com.mabaya.assignment.entities.Product;
import com.mabaya.assignment.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AdsService {

    private final static Logger logger = LoggerFactory.getLogger(AdsService.class);

    @Autowired
    ProductRepository productRepository;

    public Product serveAd(String category) {
        /// for matching category, find the promoting campaign and return the product with highest price
        Product promotedProduct = null;
        promotedProduct = getPromotedProductByCategoryAndHighestBid(category);
        /// Note here that "bid" is a field of campaign, so in case there are more than one campaign for same category,
        /// I just return the product that is promoted for the highest bid

        /// in case no promoted product exists, return the product with highest price
        if (promotedProduct == null) {
            promotedProduct = productRepository.findPromotedProductWithHighestCampaignBidAndPrice(PageRequest.of(0,1));
        }
        return promotedProduct;
    }

    private Product getPromotedProductByCategoryAndHighestBid(String category) {
        Product promotedProduct = null;
        try {
            promotedProduct = productRepository.findPromotedProductWithHighestCampaignBidByCategory(category, PageRequest.of(0,1));
        } catch (IllegalArgumentException ex) {
            logger.error("No category matched for user request");
        }
        return promotedProduct;
    }
}
