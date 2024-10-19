package com.farm.smartfarm.repository;

import com.farm.smartfarm.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findAllByOrderByInquiryIdDesc();

    List<Inquiry> findTop5ByOrderByCreationDateDesc();
}
