package com.farm.smartfarm.service;

import com.farm.smartfarm.domain.Notice;
import com.farm.smartfarm.domain.User;
import com.farm.smartfarm.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserService userService;

    public NoticeService(NoticeRepository noticeRepository, UserService userService) {
        this.noticeRepository = noticeRepository;
        this.userService = userService;
    }

    public List<Notice> getRecentNotices() {
        return noticeRepository.findTop5ByOrderByCreationDateDesc();
    }
    public List<Notice> findAll() {
        return noticeRepository.findAllByOrderByNoticeIdDesc();
    }

    public Notice saveNotice(Notice notice, String writer) {
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime localDateTimeInKorea = nowInKorea.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDateTimeInKorea.format(formatter);

        User user = userService.findByName(writer);

        notice.setCreationDate(formattedDate);
        notice.setUser(user);
        notice.setViewCount(0L);
        return noticeRepository.save(notice);
    }

    public Notice findNoticeById(Long noticeId) {
        Optional<Notice> noticeOptional = noticeRepository.findById(noticeId);
        if (noticeOptional.isPresent()) {
            Notice notice = noticeOptional.get();
            notice.increaseViewCount();
            noticeRepository.save(notice);
            return notice;
        } else {
            return null;
        }
    }

    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }
}
