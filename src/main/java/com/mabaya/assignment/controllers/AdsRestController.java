package com.mabaya.assignment.controllers;

import com.mabaya.assignment.entities.Product;
import com.mabaya.assignment.services.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdsRestController {
    @Autowired
    AdsService adsService;

    @GetMapping("/ads")
    public ResponseEntity<Product> serveAd(@RequestParam(value = "category") String category) {
        return ResponseEntity.ok().body(adsService.serveAd(category));
    }

}
