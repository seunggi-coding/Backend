package com.farm.smartfarm.repository;

import com.farm.smartfarm.domain.InquiryComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryCommentRepository extends JpaRepository<InquiryComment, Long> {
    List<InquiryComment> findByInquiryInquiryId(Long inquiryId);
}
