package com.farm.smartfarm.service;

import com.farm.smartfarm.domain.Inquiry;
import com.farm.smartfarm.domain.InquiryComment;
import com.farm.smartfarm.domain.User;
import com.farm.smartfarm.repository.InquiryCommentRepository;
import com.farm.smartfarm.repository.InquiryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final InquiryCommentRepository inquiryCommentRepository;
    private final UserService userService;

    public InquiryService(InquiryRepository inquiryRepository, InquiryCommentRepository inquiryCommentRepository, UserService userService) {
        this.inquiryRepository = inquiryRepository;
        this.inquiryCommentRepository = inquiryCommentRepository;
        this.userService = userService;
    }

    public List<Inquiry> getRecentInquiries() {
        return inquiryRepository.findTop5ByOrderByCreationDateDesc();
    }

    public List<Inquiry> findAll() {
        return inquiryRepository.findAllByOrderByInquiryIdDesc();
    }

    public Inquiry saveInquiry(Inquiry inquiry, String writer) {
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime localDateTimeInKorea = nowInKorea.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDateTimeInKorea.format(formatter);

        User user = userService.findByName(writer);

        inquiry.setCreationDate(formattedDate);
        inquiry.setUser(user);
        inquiry.setViewCount(0L);
        return inquiryRepository.save(inquiry);
    }

    public InquiryComment saveInquiryComment(InquiryComment inquiryComment, String writer, Long inquiryId) {
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime localDateTimeInKorea = nowInKorea.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDateTimeInKorea.format(formatter);

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 문의사항 게시글이 존재하지 않습니다. id=" + inquiryId));

        inquiryComment.setCreationDate(formattedDate);
        inquiryComment.setWriter(writer);
        inquiryComment.setInquiry(inquiry);

        return inquiryCommentRepository.save(inquiryComment);
    }

    public Inquiry findInquiryById(Long inquiryId) {
        Optional<Inquiry> inquiryOptional = inquiryRepository.findById(inquiryId);
        if (inquiryOptional.isPresent()) {
            Inquiry inquiry = inquiryOptional.get();
            inquiry.increaseViewCount();
            inquiryRepository.save(inquiry);
            return inquiry;
        } else {
            return null;
        }
    }

    public List<InquiryComment> findCommentsByInquiryId(Long inquiryId) {
        return inquiryCommentRepository.findByInquiryInquiryId(inquiryId);
    }

    public void deleteInquiry(Long inquiryId) {
        inquiryRepository.deleteById(inquiryId);
    }

    public void updateInquiry(Inquiry modifiedInquiry, String userName) {
        Optional<Inquiry> originalInquiryOpt = inquiryRepository.findById(modifiedInquiry.getInquiryId());

        if (originalInquiryOpt.isPresent()) {
            Inquiry originalInquiry = originalInquiryOpt.get();

            if (!originalInquiry.getUser().getName().equals(userName)) {
                throw new IllegalStateException("문의사항 수정 권한이 없습니다.");
            }

            originalInquiry.setTitle(modifiedInquiry.getTitle());
            originalInquiry.setContent(modifiedInquiry.getContent());

            inquiryRepository.save(originalInquiry);
        } else {
            throw new IllegalArgumentException("해당 문의사항이 존재하지 않습니다.");
        }
    }
}
