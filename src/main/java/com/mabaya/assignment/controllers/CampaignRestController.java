package com.mabaya.assignment.controllers;

import com.mabaya.assignment.entities.Campaign;
import com.mabaya.assignment.services.CampaignService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CampaignRestController {

    @Autowired
    CampaignService campaignService;

    @GetMapping("/campaigns")
    public ResponseEntity<List<Campaign>> getCampaigns() {
        return ResponseEntity.ok().body(campaignService.getCampaigns());
    }

    @GetMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable(value = "id") Long id) throws NotFoundException {
        Campaign c = campaignService.getCampaignById(id);
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("/campaigns")
    public ResponseEntity<Campaign> createCampaign(@RequestParam String name,
                                   @RequestParam String startDate,
                                   @RequestParam String category,
                                   @RequestParam long bid) {
        Campaign c = campaignService.createCampaign(name, startDate, category, bid);
        return ResponseEntity.ok().body(c);
    }
}
