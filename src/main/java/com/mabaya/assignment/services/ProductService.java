package com.mabaya.assignment.services;

import com.mabaya.assignment.entities.Category;
import com.mabaya.assignment.entities.Product;
import com.mabaya.assignment.repositories.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new NotFoundException("campaign not found : " + id);
        }

        return product.get();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @PostConstruct
    public void loadData() {
        for (int i = 0; i < 200; i++) {
            int randomCategory = getRandomNumber(0, Category.values().length - 1);
            productRepository.save(new Product(generateRandomString(), Category.values()[randomCategory].toString(), getRandomNumber(1, 1000)));
        }

    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String generateRandomString() {
        int targetStringLength = 10;
        Random random = new Random();
        int leftLimit = 48;
        int rightLimit = 122;
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }


}

