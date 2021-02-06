package com.mabaya.assignment.services;

import com.mabaya.assignment.entities.Campaign;
import com.mabaya.assignment.entities.Category;
import com.mabaya.assignment.repositories.CampaignRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class CampaignService {

    private final static Logger logger = LoggerFactory.getLogger(CampaignService.class);

    @Autowired
    CampaignRepository campaignRepository;

    public List<Campaign> getCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(Long id) throws NotFoundException {
        Optional<Campaign> c = Optional.ofNullable(campaignRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Campaign not found ::" + id)));

        return c.get();
    }

    public Campaign createCampaign(String name, String startDate, String category,long bid) {
        LocalDate localDate = LocalDate.parse(startDate);
        Campaign campaign = new Campaign(name, localDate, category, bid);
        return campaignRepository.save(campaign);
    }

    /**
     * Scheduled task running at midnight beggining of every day
     * We de-activate all over 10 days older campaigns
     * /// 0 0 * * *
     */
    @Async
    @Scheduled(cron = "0 0 0 * * *")
    private void deActivateCampaigns() {
        logger.info("Campaign de-activator is executing");
        campaignRepository.deActivateAllActiveCampaignsFromGivenStartTime(LocalDate.now().minusDays(Campaign.CAMPAIGN_ACTIVE_THRESHOLD_DAYS));
        logger.info("Campaign de-activator finished succesfully");

    }

    @PostConstruct
    public void loadData() {
        Campaign c1 = new Campaign("Tesla Israel Launch", LocalDate.now(), Category.Automotive.toString(), 10);
        campaignRepository.save(c1);

        Campaign c2 = new Campaign("Bit mobile", LocalDate.now(), Category.Finance.toString(), 8);
        campaignRepository.save(c2);

        Campaign c3 = new Campaign("Energy drinking", LocalDate.now(), Category.Sports.toString(), 11);
        campaignRepository.save(c3);

        Campaign c4 = new Campaign("Shorts shorts", LocalDate.now(), Category.Cosmetics.toString(), 12);
        campaignRepository.save(c4);

        Campaign c5 = new Campaign("Paybox", LocalDate.now().minusDays(15), Category.Finance.toString(), 13);
        campaignRepository.save(c5);

        Campaign c6 = new Campaign("SmartPhones", LocalDate.now(), Category.Electronics.toString(), 19);
        campaignRepository.save(c6);

        Campaign c7 = new Campaign("Lol", LocalDate.now(), Category.Gaming.toString(), 139);
        campaignRepository.save(c7);

    }

}
