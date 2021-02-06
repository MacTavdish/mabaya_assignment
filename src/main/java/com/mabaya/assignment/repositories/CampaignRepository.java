package com.mabaya.assignment.repositories;

import com.mabaya.assignment.entities.Campaign;
import com.mabaya.assignment.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query("SELECT c from Campaign c WHERE c.active = ?1 and c.category = ?2")
    List<Campaign> findAllCampaignsByStatusAndCategory(boolean active, Category category);

    @Modifying
    @Transactional
    @Query("UPDATE Campaign set active = false where active = true and start_date < ?1")
    void deActivateAllActiveCampaignsFromGivenStartTime(LocalDate startTime);
}
